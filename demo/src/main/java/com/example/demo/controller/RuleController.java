package com.example.demo.controller;


import com.example.demo.model.DynamicRuleRequest;
import com.example.demo.model.DynamicRuleResponse;
import com.example.demo.model.RuleListResponse;
import com.example.demo.model.RuleStatDTO;
import com.example.demo.service.DynamicRuleService;
import com.example.demo.service.RuleStatisticService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(" /rule ")
public class RuleController {

    private final RuleStatisticService statisticService;
    private final DynamicRuleService dynamicRuleService;

    public RuleController(RuleStatisticService statisticService, DynamicRuleService dynamicRuleService) {
        this.statisticService = statisticService;
        this.dynamicRuleService = dynamicRuleService;

    }
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public RuleStatDTO.RuleStatsResponse getStats() {
        return statisticService.getStatistics();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DynamicRuleResponse createRule(@RequestBody @Valid DynamicRuleRequest request) {
        return dynamicRuleService.createRule(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RuleListResponse getAllRules() {
        return dynamicRuleService.getAllRules();
    }

    @DeleteMapping(" /{id} ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRule (@PathVariable UUID id) {
        dynamicRuleService.deleteRule(id);
    }

}
