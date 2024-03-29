package com.cos.person.controller;


import com.cos.person.aop.step2logMethodNameAndParams.LogMethodName;
import com.cos.person.aop.step3monitorTime.MonitorTime;
import com.cos.person.aop.step4retryOperation.RetryOperation;
import com.cos.person.service.FibonacciService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class WebController {

    public WebController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    private final FibonacciService fibonacciService;

    @Cacheable("Fibonacci")
    @LogMethodName
    @MonitorTime
    @GetMapping(path = "/api/fibonacci/{number}")
    public Long fibonacci(@PathVariable(value = "number") Long number) {
        return fibonacciService.nthFibonacciTerm(number);
    }

    @RetryOperation
    @LogMethodName
    @PostMapping(path = "/api/storeData")
    public void storeData(@RequestParam(value = "data") String data) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException();
        } else {
            System.out.println("Pretend everything went fine");
        }
    }
}
