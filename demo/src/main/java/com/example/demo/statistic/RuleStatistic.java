package com.example.demo.statistic;

import com.example.demo.dynamic.DynamicRule;
import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Entity
@Table(name = "rule_statistics")
public class RuleStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private DynamicRule rule;

    @Column
    private Long count = 0L;

    public RuleStatistic(UUID id, DynamicRule rule, Long count) {
        this.id = id;
        this.rule = rule;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public DynamicRule getRule() {
        return rule;
    }

    public Long getCount() {
        return count;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRule(DynamicRule rule) {
        this.rule = rule;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
