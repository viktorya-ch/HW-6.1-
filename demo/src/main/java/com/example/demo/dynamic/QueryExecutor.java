package com.example.demo.dynamic;

import com.example.demo.checker.ConditionChecker;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class QueryExecutor {
    private static final Map<String, ConditionChecker> CHECKERS = Map.of(" USER_OF ", new UserOfChecker(), " TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW ", new TransactionCompareChecker());

    public static boolean execute(String query, UUID userId, List<String> args) {
        return
                CHECKERS.get(query).check(userId, args);
    }
}
