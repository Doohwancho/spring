package com.tdd.tddTest;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
    ---
    test results
    
    Benchmark                                   (threadCount)    Mode  Score      Units
    multiThreaded                                    1         thrpt  133,089    ops/ms
    multiThreaded                                    2         thrpt  144,442    ops/ms
    multiThreaded                                    4         thrpt  144,427    ops/ms
    multiThreaded                                    8         thrpt  144,192    ops/ms

    singleThreaded                                   1         thrpt  143,819    ops/ms
    singleThreaded                                   2         thrpt  132,522    ops/ms
    singleThreaded                                   4         thrpt  139,450    ops/ms
    singleThreaded                                   8         thrpt  140,651    ops/ms

    
    1. 변수+1 연산에 멀티쓰레드 방식을 쓰면, 쓰레드 2개썼을 때 스코어가 144,442로 제일 높다.
    2. 멀티쓰레드 연산에서 쓰레드 수가 4개,8개로 늘어나면 오히려 스코어가 떨어져서 비효율이다.
    3. 근데 싱글 쓰레드 1개일때 스코어랑 쓰레드 2개쓸 때 스코어 차이가 얼마 안나니까, 변수+1 연산은 쓰레드 1개만 할당하는게 좋다.
 */

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 2, time = 1)
@Fork(1)
public class ThreadPerformanceBenchmark {
    
    @Param({"1", "2", "4", "8"})
    private int threadCount;
    
    private AtomicInteger counter;
    
    @Setup
    public void setup() {
        counter = new AtomicInteger(0);
    }
    
    @Benchmark
    @Group("singleThreaded")
    @GroupThreads(1) //@GroupThreads(1) forces exactly one thread regardless of threadCount -> single-threaded in all cases
    public void singleThreadedIncrement(Blackhole blackhole) {
        blackhole.consume(counter.incrementAndGet());
    }
    
    @Benchmark
    @Group("multiThreaded") //jmh internally uses int threadCount; parameter to determine number of threads
    public void multiThreadedIncrement(Blackhole blackhole) {
        blackhole.consume(counter.incrementAndGet());
    }
}