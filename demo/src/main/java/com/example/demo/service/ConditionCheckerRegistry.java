package com.example.demo.service;

import com.example.demo.checker.ConditionChecker;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConditionCheckerRegistry {
    private final Map<String, ConditionChecker>checkers = new HashMap<>();

    public void register(String queryType, ConditionChecker checker){
        checkers.put(queryType, checker);
    }

    public ConditionChecker getChecker(String queryType){
        return checkers.get(queryType);
    }
}
