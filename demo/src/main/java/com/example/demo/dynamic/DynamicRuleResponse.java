package com.example.demo.dynamic;

import java.util.List;
import java.util.UUID;

public record DynamicRuleResponse(
        UUID id,
        String productName,
        UUID productId,
        String productText,
        List<RuleCondition> ruleConditions) {}