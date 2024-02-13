package io.one_billion_rows_challenge.step02_jdk17_plus_ver;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;

/*
---
What

1. jdk17 ver, using record
2. Collectors' class
    - aggregate
    - reduction
    
---
Outcome

195.28s user
5.10s system
98% cpu
3:22.42 total

---
Collector

- main goal of collector
    1. collect a stream of data from each row,
    2. calculate the required values,
    3. combine results from different threads,
    4. and finally convert the accumulation into the desired output format.
    
- Collector has 4 components
    1. supplier
    2. accumulator
    3. combiner
    4. finisher

 */

class CalculateAverage {
    
    private static final String FILE = "./measurements.txt";
    
    private static record Measurement(String station, double value) {
        private Measurement(String[] parts) {
            this(parts[0], Double.parseDouble(parts[1]));
        }
    }
    
    private static record ResultRow(double min, double mean, double max) {
        public String toString() {
            return round(min) + "/" + round(mean) + "/" + round(max);
        }
        
        private double round(double value) {
            return Math.round(value * 10.0) / 10.0;
        }
    };
    
    private static class MeasurementAggregator {
        private double min = Double.POSITIVE_INFINITY;
        private double max = Double.NEGATIVE_INFINITY;
        private double sum;
        private long count;
    }
    
    public static void main(String[] args) throws IOException {
        Collector<Measurement, MeasurementAggregator, ResultRow> collector = Collector.of(
            MeasurementAggregator::new,
            (a, m) -> {
                a.min = Math.min(a.min, m.value);
                a.max = Math.max(a.max, m.value);
                a.sum += m.value;
                a.count++;
            },
            (agg1, agg2) -> {
                var res = new MeasurementAggregator();
                res.min = Math.min(agg1.min, agg2.min);
                res.max = Math.max(agg1.max, agg2.max);
                res.sum = agg1.sum + agg2.sum;
                res.count = agg1.count + agg2.count;
                
                return res;
            },
            agg -> {
                return new ResultRow(agg.min, agg.sum / agg.count, agg.max);
            });
        
        Map<String, ResultRow> measurements = new TreeMap<>(Files.lines(Paths.get(FILE))
            .map(l -> new Measurement(l.split(";")))
            .collect(groupingBy(m -> m.station(), collector)));
        
        System.out.println(measurements);
    }
}
