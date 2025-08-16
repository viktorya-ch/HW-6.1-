package com.example.demo.checker;

import java.util.List;
import java.util.UUID;

public interface ConditionChecker {
    boolean check(UUID userId, List<String> arguments);
}
