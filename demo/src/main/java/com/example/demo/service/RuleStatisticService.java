package com.example.demo.service;

import com.example.demo.model.RuleStatDTO;
import com.example.demo.repository.RuleStatisticRepository;
import com.example.demo.statistic.RuleStatistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleStatisticService {

    private final RuleStatisticRepository repository;
    private final DynamicRuleService dynamicRuleService;

    public RuleStatisticService(RuleStatisticRepository repository, DynamicRuleService dynamicRuleService) {
        this.repository = repository;
        this.dynamicRuleService = dynamicRuleService;
    }

    public RuleStatDTO.RuleStatsResponse getStatistics() {
        List<RuleStatistic> stats = repository.findAll();
        List<RuleStatDTO> statDTOS = stats.stream().map(stats -> new RuleStatDTO(stats.getRule().getId(), stats.getCount())).toList();
        List<RuleStatDTO> allStats = dynamicRuleService.getAllRules().data().stream().map(rule -> new RuleStatDTO(rule.id(), stats.stream().filter(s -> s.setRule().getId().equals(rule.id())).findFirst()
                .map(RuleStatistic::getCount).orElse(0L))).toList();
        return new RuleStatDTO.RuleStatsResponse(allStats);
    }
}
