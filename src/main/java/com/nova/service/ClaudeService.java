package com.nova.service;

import com.nova.model.ShoppingRequest;
import com.nova.model.ProductRecommendation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
import java.util.List;

@Service
public class ClaudeService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${claude.api.key}")
    private String apiKey;

    @Value("${claude.model}")
    private String model;

    public ClaudeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductRecommendation getRecommendation(ShoppingRequest request) {
        String prompt = buildPrompt(request);
        try {
            Map<String, Object> requestBody = Map.of(
                "model", model,
                "max_tokens", 1000,
                "messages", List.of(
                    Map.of("role", "user", "content", prompt)
                )
            );

            Map response = webClient.post()
                .uri("https://api.anthropic.com/v1/messages")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

            List<Map> content = (List<Map>) response.get("content");
            String text = (String) content.get(0).get("text");
            String json = text.substring(
                text.indexOf("{"), text.lastIndexOf("}") + 1
            );
            return objectMapper.readValue(json, ProductRecommendation.class);

        } catch (Exception e) {
            return fallbackRecommendation();
        }
    }

    private String buildPrompt(ShoppingRequest request) {
        return String.format("""
            You are NOVA's AI personal shopper. Based on customer preferences,
            recommend the perfect product from our store.

            Customer Profile:
            - Style: %s
            - Occasion: %s
            - Budget: %s
            - Mood: %s
            - Notes: %s

            Our products: Laptop Pro ($999), Air Sneakers ($79), Urban Pack ($49),
            Smart Watch ($299), Leather Boots ($149), Sunglasses ($89)

            Respond ONLY with a JSON object, no other text:
            {
              "productName": "product name here",
              "whyThisProduct": "why this matches their style",
              "styleStory": "the story behind this choice",
              "howToStyle": "how to style or use this product",
              "alternativeSuggestion": "alternative product suggestion",
              "priceRange": "budget range",
              "category": "Electronics/Footwear/Accessories",
              "emoji": "relevant emoji"
            }
            """,
            request.getStyle(),
            request.getOccasion(),
            request.getBudget(),
            request.getMood(),
            request.getAdditionalNotes()
        );
    }

    private ProductRecommendation fallbackRecommendation() {
        ProductRecommendation rec = new ProductRecommendation();
        rec.setProductName("Urban Pack");
        rec.setWhyThisProduct("A versatile choice for any style");
        rec.setStyleStory("The Urban Pack adapts to any occasion");
        rec.setHowToStyle("Pair with any outfit for instant style");
        rec.setAlternativeSuggestion("Air Sneakers");
        rec.setPriceRange("$49 - $99");
        rec.setCategory("Accessories");
        rec.setEmoji("🎒");
        return rec;
    }
}
