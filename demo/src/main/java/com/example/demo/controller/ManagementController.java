package com.example.demo.controller;

import com.example.demo.repository.CachedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class ManagementController {


    private final CacheManager cacheManager;
    private final CachedUserRepository cachedUserRepository;

    public ManagementController(CacheManager cacheManager, CachedUserRepository cachedUserRepository) {
        this.cacheManager = cacheManager;
        this.cachedUserRepository = cachedUserRepository;
    }


    @PostMapping("/clear-caches")



    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCaches(){
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
        cachedUserRepository.clearAllCaches();
    }
}
