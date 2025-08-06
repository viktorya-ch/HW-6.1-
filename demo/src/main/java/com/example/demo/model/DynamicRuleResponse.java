package com.example.demo.model;

import com.example.demo.dynamic.RuleCondition;

import java.util.List;
import java.util.UUID;

public record DynamicRuleResponse(
        UUID id,
        String productName,
        UUID productId,
        String productText,
        List<RuleCondition> ruleConditions) {

}