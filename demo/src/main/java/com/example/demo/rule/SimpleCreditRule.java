package com.example.demo.rule;

import com.example.demo.model.DTO;
import com.example.demo.repository.UserProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class SimpleCreditRule implements RecommendationRule {
    private final UserProductRepository repository;

    public SimpleCreditRule(UserProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DTO> apply(String userId) {
        boolean notUsesCredit = !repository.isUserUsingProductType(userId, " CREDIT ");
        if (!notUsesCredit) {
            return Optional.empty();
        }
        BigDecimal debitDeposits = repository.getTotalDepositForProductType(userId, " DEBIT ");
        BigDecimal debitWithdrawals = repository.getTotalWithdrawalForProductType(userId, " DEBIT ");
        boolean depositsGreaterThanWithdrawals = debitDeposits.compareTo(debitWithdrawals) > 0;


        boolean withdrawalsOver100k = debitWithdrawals.compareTo(BigDecimal.valueOf(100_000)) > 0;

        if (depositsGreaterThanWithdrawals && withdrawalsOver100k) {
            return Optional.of(new DTO(" ab138afb-f3ba-4a93-b74f-0fcee86d447f ", " Простой кредит ", " Откройте мир выгодных кредитов с нами! "));
        }
        return Optional.empty();
    }
}
