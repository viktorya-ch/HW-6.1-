package com.example.demo.service;


import com.example.demo.model.DTO;
import com.example.demo.rule.RecommendationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    private final List<RecommendationRule> rules;

    @Autowired
    public RecommendationService (List<RecommendationRule> rules){
        this.rules = rules;
    }

    public List<DTO> getRecommendations (String userId){
        List<DTO> result = new ArrayList<>();
        for (RecommendationRule rule : rules){
            Optional<DTO> recommendation = rule.apply(userId);

            recommendation.ifPresent(result::add);
        }
        return result;
    }
}
