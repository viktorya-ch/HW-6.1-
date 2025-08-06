package com.example.demo.service;


import com.example.demo.dynamic.DynamicRule;
import com.example.demo.model.DTO;
import com.example.demo.repository.RuleStatisticRepository;
import com.example.demo.statistic.RuleStatistic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class RecommendationService {

    private final RuleStatisticRepository statisticRepository;

    public RecommendationService(RuleStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<DTO> getRecommendations(String userId) {
        List<DTO> recommendations = new ArrayList<>();

        staticRules.forEach(rule -> rule.apply(userId).ifPresent(recommendations::add));

        DynamicRuleService dynamicRuleService;
        dynamicRuleService.getAllRules().data().forEach(rule -> {
            if (ruleInterpreter.evaluate(userId, rule.rule())) {
                recommendations.add(new DTO(rule.productId().toString(), rule.productName(), rule.productText()));
                updateRuleStatistic(rule.id());
            }
        });
        return recommendations;
    }

    private void updateRuleStatistic(UUID ruleId) {
        DynamicRule rule = dynamicRuleService.getRuleById(ruleId).orElseThrow(() -> new IllegalArgumentException(" Rule not found "));
        RuleStatistic statistic = statisticRepository.findByRule(rule).orElseGet(() -> {
            RuleStatistic newStat = new RuleStatistic();
            newStat.setRule(rule);
            return newStat;
        });
        statistic.setCount(statistic.getCount() + 1);
        statisticRepository.save(statistic);

    }
}



















}
