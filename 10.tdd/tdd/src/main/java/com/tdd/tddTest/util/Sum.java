package com.tdd.tddTest.util;

import java.util.stream.IntStream;

public class Sum {
    public static int forSum(int maxNum) {
        int sum = 0;
        for (int i = 1; i <= maxNum; i++) {
            sum += i;
        }
        return sum;
    }
    
    public static int streamSum(int maxNum) {
        return IntStream.rangeClosed(0, maxNum).sum();
    }
}
