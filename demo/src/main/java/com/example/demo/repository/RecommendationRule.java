package com.example.demo.repository;

import com.example.demo.model.DTO;

import java.util.Optional;

public interface RecommendationRule {

    Optional<DTO>apply(String userId);


}

