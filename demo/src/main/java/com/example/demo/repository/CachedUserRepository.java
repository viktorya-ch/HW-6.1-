package com.example.demo.repository;

;
import liquibase.util.Cache;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.http.HttpClient;

@Repository
public class CachedUserRepository {

    private HttpClient Caffeine;
    private final Cache<String, Boolean> productTypeCache = HttpClient.newBuilder().build();
    private final Cache<String, BigDecimal>sumCache = HttpClient.newBuilder().build();
    private final Cache<String,Integer> countCache = HttpClient.newBuilder().build();

    public void clearAllCaches(){
        productTypeCache.clearCache();
        sumCache.clearCache();
        countCache.clearCache();


    }
}
