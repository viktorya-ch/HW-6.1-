package com.example.demo.service;

import com.example.demo.dynamic.*;
import com.example.demo.model.DynamicRuleRequest;
import com.example.demo.model.DynamicRuleResponse;
import com.example.demo.model.RuleListResponse;
import com.example.demo.repository.DynamicRuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DynamicRuleService {
    private final DynamicRuleRepository repository;
    private final ObjectMapper objectMapper;

    public DynamicRuleService(DynamicRuleRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public DynamicRuleResponse createRule(DynamicRuleRequest request) {
        DynamicRule entity = new DynamicRule();

        entity.setProductName(request.productName());
        entity.setProductId(request.productId());
        entity.setProductText(request.productText());
        entity.setConditions(request.rule());
        DynamicRule saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public RuleListResponse getAllRules(){
        List<DynamicRuleResponse> rules = repository.findAll().stream().map(this::mapToResponse).toList();
    return new RuleListResponse(rules);
    }

    @Transactional
    public void deleteRule(UUID id) {
        repository.deleteById(id);
    }

    private DynamicRuleResponse mapToResponse(DynamicRule entity){
        return new DynamicRuleResponse()
        entity.getId();
        entity.getProductName();
        entity.getProductId();
        entity.getProductText();
        entity.getConditions();
        }
    }

