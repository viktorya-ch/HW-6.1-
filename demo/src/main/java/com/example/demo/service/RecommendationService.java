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
    private final  DynamicRuleService dynamicRuleService;
    private final  RuleInterpreter ruleInterpreter;


    public List<DTO> getRecommendations(String userId) {
        List<DTO>recommendations = new ArrayList<>();

        staticRules.forEach(rule -> rule.apply(userId).ifPresent(recommendations::add));

        dynamicRuleService.getAllRules().data().forEach(rule->{
            if(ruleInterpreter.evaluate(userId,rule.rule())){
                recommendations.add(new DTO(rule.productId().toString(), rule.productName(),rule.productText()));
            }
        });
        return recommendations;
    }

    @Autowired
    public RecommendationService (List<RecommendationRule> rules){
        this.rules = rules;
    }
}
