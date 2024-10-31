package com.tdd.tddTest;

import com.tdd.tddTest.util.SortArray;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class SortBenchmark {
    private int[] arr;
    
    private static final int N = 10;
    private static final int SIZE = 1_000_000;
    
    @Setup
    public void setup() {
        arr = new Random().ints(SIZE).toArray();
    }
    
    @Benchmark
    public int[] testSort() {
        return SortArray.findTopN_Sort(arr.clone(), N);
    }
    
    @Benchmark
    public int[] testHeap() {
        return SortArray.findTopN_MinHeap(arr.clone(), N);
    }
    
    @Benchmark
    public int[] testQuickSelect() {
        return SortArray.findTopN_QuickSelect(arr.clone(), N);
    }
}
