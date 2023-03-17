package com.tenpo.challenge.controller;

import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.exception.RateLimitException;
import com.tenpo.challenge.model.HistoryResponse;
import com.tenpo.challenge.service.CalculateService;
import com.tenpo.challenge.service.HistoryService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;

@RequestMapping(value = "/api")
@RestController
public class TenpoController {
    @Autowired
    private CalculateService calulateService;
    @Autowired
    private HistoryService historyService;

    private final int maxRequestPerMinute = 3;
    private final Bucket bucket;

    public TenpoController() {
        Bandwidth limit = Bandwidth.classic(maxRequestPerMinute, Refill.greedy(maxRequestPerMinute, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }



    @GetMapping("/history")
    public HistoryResponse getHistory(@RequestParam(required = false) Integer page) throws RateLimitException {
        if(bucket.tryConsume(1)){
            return historyService.getHistory(page);
        }
        throw new RateLimitException();
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(required = true) BigDecimal valueA, @RequestParam(required = true) BigDecimal valueB) throws RateLimitException, PercentageNotFoundException {
        if(bucket.tryConsume(1)){
            return calulateService.calculate(valueA, valueB);
        }
        throw new RateLimitException();
    }
}
