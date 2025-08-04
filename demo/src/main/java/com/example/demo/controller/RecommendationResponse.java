package com.example.demo.controller;

import com.example.demo.model.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecommendationResponse {
    @JsonProperty(" user_id ")
    private String userId;
            @JsonProperty (" recommendations ")
    private List<DTO> recommendations;

            public RecommendationResponse (String userId, List<DTO>recommendations){
                this.userId = userId;
                this.recommendations = recommendations;
            }

    public String getUserId() {
        return userId;
    }

    public List<DTO> getRecommendations() {
        return recommendations;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRecommendations(List<DTO> recommendations) {
        this.recommendations = recommendations;
    }
}
