package com.example.demo.rule;

import com.example.demo.model.DTO;
import com.example.demo.repository.UserProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class Invest500Rule implements RecommendationRule {

    private final UserProductRepository repository;

    public Invest500Rule(UserProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DTO> apply(String userId) {
        boolean usesDebit = repository.isUserUsingProductType(userId, " DEBIT ");
        boolean usesInvest = repository.isUserUsingProductType(userId, " INVEST ");
        BigDecimal totalSavingDeposit = repository.getTotalDepositForProductType(userId, " SAVING ");

        if (usesDebit && !usesInvest && totalSavingDeposit.compareTo(BigDecimal.valueOf(1000)) > 0) {
            DTO dto = new DTO(" 147f6a0f-3b91-413b-ab99-87f081d60d5a ", " Invest500 ", " Открой свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! ");
            return Optional.of(dto);
        }
        return Optional.empty();
    }




}
