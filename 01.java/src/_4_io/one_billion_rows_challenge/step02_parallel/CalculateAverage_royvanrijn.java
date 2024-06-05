package _4_io.one_billion_rows_challenge.step02_parallel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
---
What

process lines in parallel

```java
Map<String, Measurement> resultMap = Files.lines(Path.of(FILE)).parallel()
```

---
Outcome


jdk17: 1분 44초
```
300.36s user
29.69s system
316% cpu
1:44.34 total
```.
- 병렬 프로그래밍 썼더니, user space에서 소요시간이 150% 늘었고,
- 커널모드에서 file i/o에 쓰는 시간도 600% 늘었는데,
- 총 소요시간은 1분 38초나 줄었다.

단, 316% CPU usage까지 올라가는걸 보면, 오래하다간 쓰로틀링 엄청 걸릴 듯 하다.



openjdk21: 1분 35초
```
412.60s user
29.51s system
464% cpu
1:35.21 total
```

jdk21의 .parallel()이 jdk17보다 더 최적화가 잘되있거나,
jdk21에 experimental 기능중에 하나인 경량 쓰레드인 fibers가 .parallel() 수행시 생기는 concurrency overhead 비용을 줄여줘서 성능개선이 된 듯 하다.

CPU 사용량만 봐도, 150%나 높은걸 보면, 수 많은 경량 쓰레드인 fibers가 CPU를 동시에 점유하려고 하기 때문에 저렇게 높히 올라간 것으로 보인다.

 */

class CalculateAverage_royvanrijn {
    
    private static final String FILE = "./measurements.txt";
    
    private record Measurement(double min, double max, double sum, long count) {
        
        Measurement(double initialMeasurement) {
            this(initialMeasurement, initialMeasurement, initialMeasurement, 1);
        }
        
        public static Measurement combineWith(Measurement m1, Measurement m2) {
            return new Measurement(
                m1.min < m2.min ? m1.min : m2.min,
                m1.max > m2.max ? m1.max : m2.max,
                m1.sum + m2.sum,
                m1.count + m2.count
            );
        }
        
        public String toString() {
            return round(min) + "/" + round(sum / count) + "/" + round(max);
        }
        
        private double round(double value) {
            return Math.round(value * 10.0) / 10.0;
        }
    }
    
    public static void main(String[] args) throws IOException {

//        long before = System.currentTimeMillis();
        
        Map<String, Measurement> resultMap = Files.lines(Path.of(FILE)).parallel() //병렬로 읽기
            .map(record -> {
                // Map to <String,double>
                int pivot = record.indexOf(";");
                String key = record.substring(0, pivot);
                double measured = Double.parseDouble(record.substring(pivot + 1));
                return new AbstractMap.SimpleEntry<>(key, measured);
            })
            .collect(Collectors.toConcurrentMap(
                // Combine/reduce:
                AbstractMap.SimpleEntry::getKey,
                entry -> new Measurement(entry.getValue()),
                Measurement::combineWith));
        
        System.out.print("{");
        System.out.print(
            resultMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(Object::toString).collect(Collectors.joining(", ")));
        System.out.println("}");

//        System.out.println("Took: " + (System.currentTimeMillis() - before));
    
    }
}
