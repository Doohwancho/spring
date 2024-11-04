package com.tdd.tddTest._03_redis.benchmark;


import com.tdd.tddTest._03_redis.config.TestRedisConfig;
import com.tdd.tddTest._03_redis.domain.MockProduct;
import com.tdd.tddTest._03_redis.service.ProductRankingService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*

A. result of writeTest()


Benchmark                                                                        (threadCount)    Mode     Cnt   Score    Error   Units
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest                                2   thrpt       2  57.869           ops/ms
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest                                2    avgt       2   0.036            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest                                2  sample  565815   0.035 ±  0.001   ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.00                2  sample           0.019            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.50                2  sample           0.034            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.90                2  sample           0.041            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.95                2  sample           0.047            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.99                2  sample           0.065            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.999               2  sample           0.159            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p0.9999              2  sample           0.617            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest:writeTest·p1.00                2  sample          10.617            ms/op
tddTest._03_redis.benchmark.ProductRankingBenchmark.writeTest                                2      ss       2   0.216            ms/op

해석

2 쓰레드일 때,
1. system can handle 57,869 operations of viewcount+1 of per second
2. 1 operation 당 평균 0.036ms가 걸린다.
3. cache-warm 없이는 1 operation당 평균 0.216ms가 걸린다.

 */

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
//@Threads(Threads.MAX) // Use maximum available threads
public class ProductRankingBenchmark {
    
    @Param({"2"}) // Test with different core counts
    private int threadCount;
    
    private ProductRankingService rankingService;
    private List<MockProduct> testProducts;
    private static final int PRODUCT_COUNT = 1000; // 100k products
    private static final int INITIAL_VIEWS = 1000; // Initial views per product
    private AnnotationConfigApplicationContext context;
    
    @Setup
    public void setup() {
        // Initialize minimal context with just the required beans
        context = new AnnotationConfigApplicationContext();
        context.register(TestRedisConfig.class);
        context.refresh();
        
        // Get the service directly
        rankingService = context.getBean(ProductRankingService.class);
        
        // Reset any existing data
        rankingService.resetAllRankings();
        
        // Create test products
        testProducts = new ArrayList<>();
        for (int i = 0; i < PRODUCT_COUNT; i++) {
            MockProduct product = new MockProduct((long) i, "Product-" + i);
            testProducts.add(product);
        }
        
        // Initialize with random view counts
        for (MockProduct product : testProducts) {
            int viewCount = (int) (Math.random() * INITIAL_VIEWS);
            rankingService.incrementView(product, viewCount);
        }
    }
    
    @TearDown
    public void tearDown() {
        if (rankingService != null) {
            rankingService.resetAllRankings();
        }
        if (context != null) {
            context.close();
        }
        testProducts = null; // Help GC
    }
    
    // Simulates high-frequency write operations (product views)
    @Benchmark
//    @Group("write")
    @Threads(2)
//    @GroupThreads(16) // Simulate multiple concurrent users viewing products
    public void writeTest() {
        // Random product selection to simulate real user behavior
        int randomIndex = (int) (Math.random() * testProducts.size());
        MockProduct randomProduct = testProducts.get(randomIndex);
        rankingService.incrementView(randomProduct, 1);
    }
    
    // Simulates frequent read operations for top products
//    @Benchmark
//    @Group("read")
//    @GroupThreads(8) // Simulate multiple concurrent read requests
//    public void readTest(Blackhole blackhole) {
//        List<String> topProducts = rankingService.getTopViewedProducts();
//        blackhole.consume(topProducts);
//    }
    
    // Simulates real-world mixed workload
//    @Benchmark
//    @Group("mixed")
//    @GroupThreads(12) // More writes than reads
//    public void mixedWriteTest() {
//        writeTest();
//    }

//    @Benchmark
//    @Group("mixed")
//    @GroupThreads(4) // Fewer reads
//    public void mixedReadTest(Blackhole blackhole) {
//        readTest(blackhole);
//    }
    
    // Simulates burst write scenarios
//    @Benchmark
//    @Group("burst")
//    public void burstWriteTest() {
//        // Simulate burst of 100 writes to randomly selected products
//        for (int i = 0; i < 100; i++) {
//            int randomIndex = (int) (Math.random() * testProducts.size());
//            Product randomProduct = testProducts.get(randomIndex);
//            rankingService.incrementView(randomProduct);
//        }
//    }
    
    // Simulates heavy read load
//    @Benchmark
//    @Group("heavyRead")
//    @GroupThreads(20) // High number of concurrent reads
//    public void heavyReadTest(Blackhole blackhole) {
//        for (int i = 0; i < 10; i++) { // Multiple reads per thread
//            List<String> topProducts = rankingService.getTopViewedProducts();
//            blackhole.consume(topProducts);
//        }
//    }
}
