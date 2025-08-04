package com.example.demo.dynamic;

import java.util.List;
import java.util.UUID;

public record DynamicRuleRequest (
        String productName,
        UUID productId,
        String productText,
        List<RuleCondition> rule
){
}
