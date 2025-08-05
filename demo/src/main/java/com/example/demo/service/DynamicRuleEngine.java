package com.example.demo.service;

import com.example.demo.dynamic.DynamicRule;
import com.example.demo.dynamic.QueryExecutor;
import com.example.demo.model.RuleCondition;

import java.util.UUID;

public class DynamicRuleEngine {
    public boolean checkRule (UUID userId, DynamicRule rule){
        for (RuleCondition condition : rule.getConditions()){
            boolean result = QueryExecutor.execute(condition.getQuery(), userId, condition.getArguments());
            if (condition.isNegate()) result = !result;
            if (!result) return false;
        }
        return true;
    }
}
