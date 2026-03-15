package com.nova.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClientConfig — Configures the HTTP client for Claude API calls
 *
 * @Configuration = this class provides Spring Beans (reusable objects)
 * @Bean = this method creates an object Spring manages (like module.exports)
 *
 * Think of this like creating an axios instance with baseURL + headers:
 *   const apiClient = axios.create({
 *     baseURL: 'https://api.anthropic.com',
 *     headers: { 'x-api-key': process.env.CLAUDE_API_KEY }
 *   });
 */
@Configuration
public class WebClientConfig {

    @Value("${claude.api.key}")
    private String claudeApiKey;

    @Bean
    public WebClient claudeWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.anthropic.com")
                .defaultHeader("x-api-key", claudeApiKey)
                .defaultHeader("anthropic-version", "2023-06-01")
                .defaultHeader("Content-Type", "application/json")
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(2 * 1024 * 1024)) // 2MB buffer
                .build();
    }
}

