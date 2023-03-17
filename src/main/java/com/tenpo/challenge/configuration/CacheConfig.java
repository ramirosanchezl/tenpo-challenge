package com.tenpo.challenge.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("percentage");
        cacheManager.setCaffeine(cacheBuilder());
        return cacheManager;
    }


    Caffeine<Object, Object> cacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(1)
                .maximumSize(1)
                .expireAfterWrite(30,TimeUnit.MINUTES)
                .recordStats();
    }
}
