package com.example.demo.dynamic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DynamicRuleRepository extends JpaRepository<DynamicRule, UUID> {

    @Modifying
    @Query(" DELETE FROM DynamicRule dr WHERE dr.productId = :productId ")
    void deleteByProductId(@Param(" productId ") UUID productId);

    Optional<DynamicRule> findByProductId(UUID productId);
}
