package com.example.demo.repository;

import com.example.demo.dynamic.DynamicRule;
import com.example.demo.statistic.RuleStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RuleStatisticRepository extends JpaRepository<RuleStatistic, UUID> {
    Optional<RuleStatistic> findByRule(DynamicRule rule);
}
