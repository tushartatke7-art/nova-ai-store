package com.nova.controller;

import com.nova.model.ShoppingRequest;
import com.nova.model.ProductRecommendation;
import com.nova.service.ClaudeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final ClaudeService claudeService;

    public RecommendationController(ClaudeService claudeService) {
        this.claudeService = claudeService;
    }

    @PostMapping("/recommend")
    public ProductRecommendation recommend(
            @RequestBody ShoppingRequest request) {
        return claudeService.getRecommendation(request);
    }
}
