package io.one_billion_rows_challenge.step01_baseline_jdk8ver;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/*
---
What

Map<K,V>로 읽은 방법, using jdk8

---
Outcome

191.55s user
5.38s system
99% cpu
3:17.40 total
*/

class CalculateAverage {
    
    private static final String FILE = "../measurements.txt";
    
    // Replacing record with a class for Measurement
    private static class Measurement {
        private String station;
        private double value;
        
        private Measurement(String[] parts) {
            this.station = parts[0];
            this.value = Double.parseDouble(parts[1]);
        }
        
        public String getStation() {
            return station;
        }
        
        public double getValue() {
            return value;
        }
    }
    
    // Replacing record with a class for ResultRow
    private static class ResultRow {
        private double min;
        private double mean;
        private double max;
        
        public ResultRow(double min, double mean, double max) {
            this.min = min;
            this.mean = mean;
            this.max = max;
        }
        
        @Override
        public String toString() {
            return round(min) + "/" + round(mean) + "/" + round(max);
        }
        
        private double round(double value) {
            return Math.round(value * 10.0) / 10.0;
        }
    }
    
    private static class MeasurementAggregator {
        private double min = Double.POSITIVE_INFINITY;
        private double max = Double.NEGATIVE_INFINITY;
        private double sum;
        private long count;
    }
    
    public static void main(String[] args) throws IOException {
        Map<String, Double> measurements1 = Files.lines(Paths.get(FILE))
            .map(l -> l.split(";"))
            .collect(groupingBy(m -> m[0], averagingDouble(m -> Double.parseDouble(m[1]))));
        
        measurements1 = new TreeMap<>(measurements1.entrySet()
            .stream()
            .collect(toMap(e -> e.getKey(), e -> Math.round(e.getValue() * 10.0) / 10.0)));
        
        System.out.println(measurements1);
    }
}

