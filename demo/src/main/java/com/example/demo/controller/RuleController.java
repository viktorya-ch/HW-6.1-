package com.example.demo.controller;


import com.example.demo.model.DynamicRuleRequest;
import com.example.demo.model.DynamicRuleResponse;
import com.example.demo.model.RuleListResponse;
import com.example.demo.service.DynamicRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(" /rule ")
public class RuleController {

    private final DynamicRuleService dynamicRuleService;

    public RuleController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
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
