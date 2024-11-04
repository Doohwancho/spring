package com.tdd.tddTest._03_redis;

import com.tdd.tddTest._03_redis.benchmark.ProductRankingBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ProductRankingBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(1)
            .measurementIterations(1)
            .threads(2)
            .build();
        
        new Runner(opt).run();
    }
}
