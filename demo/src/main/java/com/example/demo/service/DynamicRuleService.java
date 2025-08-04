package com.example.demo.service;

import com.example.demo.dynamic.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DynamicRuleService {
    private final DynamicRuleRepository repository;
    private final RuleValidator validator;



    public DynamicRuleResponse createRule(DynamicRuleRequest request) {
        validator.validate(request.rule());

//        if (repository.findByProductId(request.productId()).isPresent()){
//            throw  new IllegalArgumentException(" Rule for this product already exists ");
//        }
        DynamicRule entity = new DynamicRule();

        entity.setProductName(request.productName());
        entity.getProductId(request.productId());
        entity.setProductText(request.productText);
        entity.setRule(request.rule());
        DynamicRule saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public RuleListResponse getAllRules(){
        List<DynamicRuleResponse> rules = repository.findAll().stream().map(this::mapToResponse).toList();
    return new RuleListResponse(rules);
    }

    public void deleteRule(UUID id) {
        repository.deleteById(id);
    }

    private DynamicRuleResponse mapToResponse(DynamicRule entity){
        return new DynamicRuleResponse()
                entity.getId();
        entity.getProductName();
        entity.getProductId();
        entity.getProductText();
        entity.getRule();
        }
    }

