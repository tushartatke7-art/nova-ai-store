package com.nova.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "app", "NOVA AI Store",
            "message", "Your AI shopping assistant is ready 🛍️",
            "version", "1.0.0",
            "status", "UP"
        );
    }
}
