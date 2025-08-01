package com.example.demo.controller;

import com.example.demo.model.DTO;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class RecommendationController {
    private final RecommendationService service;

    public RecommendationController(RecommendationService service){
        this.service = service;
    }

    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<Map<String, Object>>getRecommendations(@PathVariable String userId){
        List<DTO>recommendations = service.getRecommendations(userId);
        Map<String,Object>response = new HashMap<>();
        response.put(" user_id ", userId);
        response.put(" recommendations ", recommendations);
        return ResponseEntity.ok(response);
    }

}
