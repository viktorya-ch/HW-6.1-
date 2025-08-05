package com.example.demo.repository;

import jakarta.transaction.Transaction;
import liquibase.util.Cache;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.http.HttpClient;

@Repository
public class CachedUserRepository {
    private final JdbcTemplate jdbc;
    private HttpClient Caffeine;
    private final Cache<String, Boolean> productTypeCache = HttpClient.newBuilder().build();
    private final Cache<String, BigDecimal>sumCache = HttpClient.newBuilder().build();
    private final Cache<String,Integer> countCache = HttpClient.newBuilder().build();

    public boolean hasProductType(String userId, ProductType type) {
        String key = userId + " : " + type;
        return productTypeCache.get(key, k->{
            String sql = " SELECT EXISTS(SELECT 1 FROM transaction t " + " JOIN product p ON t.product_id = p.id " + " WHERE t.user_id = ? AND p.type = ?";
            return jdbc.queryForObject(sql,Boolean.class,userId,type.name());
        });
    }
    public BigDecimal getSumByProductTypeAndOperation(String userId, ProductType productType, TransactionType operation){
        String key = userId + " : " + productType + " : " + operation;
        return sumCache.get(key, k->{
            String sql = " SELECT COALESCE (SUM(t.amount), 0) " + " FROM transaction t " + " JOIN product p ON t.product_id = p.id " + " WHERE t.user_id = ? AND p.type = ? AND t.type = ? ";
            return jdbc.queryForObject(sql,BigDecimal.class, userId, productType.name(), operation.name());
        });
    }
    public int getTransactionCount (String userId, ProductType type) {
        String key = userId + " : " + type;
        return countCache.get(key, k ->{
            String sql = " SELECT COUNT(*) FROM transaction t " + " JOIN product p ON t.product_id = p.id " + " WHERE t.user_id = ? AND p.type = ? ";
            return jdbc.queryForObject(sql, Integer.class, userId, type.name());
        });
    }
}
