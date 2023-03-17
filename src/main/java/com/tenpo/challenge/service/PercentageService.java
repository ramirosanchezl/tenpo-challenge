package com.tenpo.challenge.service;


import com.tenpo.challenge.exception.PercentageClientException;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
@CacheConfig(cacheNames = "percentage")
@EnableRetry
public class PercentageService {

    @Cacheable
    @Retryable(value = PercentageClientException.class)
    public BigDecimal getPercentage() throws PercentageClientException {
        System.out.println("Random percentage service called");
        int randomInt = ThreadLocalRandom.current().nextInt(1, 100);
        if(randomInt <= 70){
            return BigDecimal.valueOf(randomInt);
        }
        throw new PercentageClientException();
    }
}
