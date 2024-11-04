package com.tdd.tddTest._01_helloworld;

import com.tdd.tddTest.util.Sum;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

/*
    # test options in build.gradle
    
    jmh {
        jmhVersion = '1.35'
        includeTests = true // Allows to include test sources into generate JMH jar, i.e. use it when benchmarks depend on the test classes.
        iterations = 5 // Number of measurement iterations to do.
        warmupIterations = 3 // Number of warmup iterations to do.
        fork = 2 // How many times to forks a single benchmark. Use 0 to disable forking altogether
        benchmarkMode = ['thrpt', 'avgt'] //// Benchmark mode. Available modes are: [Throughput/thrpt, AverageTime/avgt, SampleTime/sample, SingleShotTime/ss, All/all]
        timeUnit = 'ms'
    }
    
    docs: https://github.com/melix/jmh-gradle-plugin
    
    
    # test results
    
    Benchmark                        Mode  Cnt     Score     Error   Units
    tddTest.SumBenchmark.forSum     thrpt   10  3171.457 ±  75.826  ops/ms
    tddTest.SumBenchmark.streamSum  thrpt   10  3006.377 ± 109.436  ops/ms
    tddTest.SumBenchmark.forSum      avgt   10    ≈ 10⁻³             ms/op
    tddTest.SumBenchmark.streamSum   avgt   10    ≈ 10⁻³             ms/op


    throughput도 forSum이 더 높고, 에러도 forSum이 더 낮다.
    latency는 단위를 ms보다 더 정밀한 ns로 하면 차이가 보일 듯 싶으나, 둘다 엇비슷해보인다.
    
    왜 stream이 forSum보다 성능이 더 느린지,
    profiler에 flame graph 보면,
    callstack에서 추가적인 함수호출이 더 많다. 여기서 성능이 좀 깎이는 듯.
 */

@State(Scope.Benchmark)
public class SumBenchmark {
    @Benchmark
    public void forSum(Blackhole bh) {
        bh.consume(Sum.forSum(1000));
    }
    
    @Benchmark
    public void streamSum(Blackhole bh) {
        bh.consume(Sum.streamSum(1000));
    }
}
