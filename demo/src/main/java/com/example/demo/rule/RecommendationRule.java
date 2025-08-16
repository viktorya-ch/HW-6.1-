package com.example.demo.rule;

import com.example.demo.model.DTO;

import java.util.Optional;

public interface RecommendationRule {

    Optional<DTO> apply(String userId);


}

