package com.example.demo;

import com.example.demo.model.DTO;
import com.example.demo.repository.RecommendationRule;
import com.example.demo.repository.UserProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;


@Component
public class TopSavingRule implements RecommendationRule {

    private final UserProductRepository repository;

    public TopSavingRule(UserProductRepository repository){
        this.repository = repository;
    }


    @Override
    public Optional<DTO>apply(String userId){
        boolean usesDebit = repository.isUserUsingProductType(userId, " DEBIT ");
        if (!usesDebit){
            return Optional.empty();
        }

        BigDecimal debitDeposits = repository.getTotalDepositForProductType(userId," DEBIT ");
        BigDecimal savingDeposits = repository.getTotalDepositForProductType(userId, " SAVING ");
        BigDecimal fiftyThousand = BigDecimal.valueOf(50_000);

        boolean debitCondition = debitDeposits.compareTo(fiftyThousand) >= 0;
        boolean savingCondition = savingDeposits.compareTo(fiftyThousand) >= 0;

        if (debitCondition || savingCondition) {
            return Optional.of(new DTO(" 59efc529-2fff-41af-baff-90ccd7402925 ", " Top Saving ", " Откройте свою собственную «Копилку» с нашим банком! "));
        }
        return Optional.empty();
    }

}

