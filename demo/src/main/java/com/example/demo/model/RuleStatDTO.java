package com.example.demo.model;

import java.util.List;
import java.util.UUID;

public record RuleStatDTO(UUID rule_id, Long count) {
    public record RuleStatsResponse(List<RuleStatDTO>stats){}
}
