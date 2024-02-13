
# A. What

![](1brc.png)

measurements.txt 파일이 10억개의 rows가 있다.

최대한 빠른 시간 안에 전부 읽자


```
head measurements.txt

Boise;2.5
Barcelona;18.3
Ouahigouya;47.1
Parakou;29.7
Vilnius;-0.2
Monaco;15.4
Dikson;-17.2
Cape Town;12.4
Jerusalem;18.4
Palm Springs;20.0
```


# B. 실험 환경

1. 1brc's 실험환경
    - eight cores of a Hetzner AX161 dedicated server (32 core AMD EPYC™ 7502P (Zen2), 128 GB RAM).
2. my pc
    - arm64, 1 CPU, 8 core, 16GiB RAM


# C. How

## a. how to change jdk using asdf?

```
asdf current
asdf list java
asdf local java ${java-version}
```

## b. how to download 1 billion rows in .txt?

```
cd step00-create-txt-with-massive-rows/
javac CreateMeasurements.java
java CreateMeasurements
```

## c. how to run?
```
javac CalculateAverage.java
time java CalculateAverage
```

## d. how to interpret 'time' command's outcome?

```
ex) java CalculateAverage  191.55s user 5.38s system 99% cpu 3:17.40 total
```
1. CalculateAverage:
    - 실행한 자바 클래스 이름
2. 191.55s user:
    - amount of CPU time spent in user-mode.
    - This does not include time spent doing system-level tasks or waiting for I/O operations
    - A higher value here means your program spent a lot of time performing calculations or processing data.
3. 5.38s system:
    - This is the amount of CPU time spent in the kernel (system-mode) within the context of this process
    - It indicates the time the operating system kernel spent performing tasks on behalf of the program, such as handling file I/O, network communications, or other system calls
    - A lower value here typically means your program did not require the system to do a lot of work on its behalf
4. 99% cpu:
    - CPU usage
5. 3:17.40 total
    - elapsed real time from start to finish of the command you ran



# D. Outcome


## step01 - Map<key, value>

### 1. jdk8: 3분 17초
```
191.55s user
5.38s system
99% cpu
3:17.40 total
```


-----------------------------------------------
## step02 - treemap

### 0. idea
1. Map<K,V>로 읽지 말고 TreeMap<K,V>로 읽어보자
2. jdk8 말고 17버전을 쓰면 빨라지지 않을까?

#### 0-1. collector
The collector has four components.
1. supplier
2. accumulator
3. combiner 
4. finisher

The main purpose of the collector is to 
1. collect a stream of data from each row, 
2. calculate the required values, 
3. combine results from different threads, 
4. and finally convert the accumulation into the desired output format.



```java
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
```


### 1. jdk17: 3분 22초

```
195.28s user
5.10s system
98% cpu
3:22.42 total
```

- 오히려 더 느려졌다.
- jdk8 -> 17로 바꾼다고 성능개선이 일어나지는 않는 듯 하다.
- Map으로 읽나 트리맵으로 읽나 큰 성능개선은 없었다.

-----------------------------------------------
## step03 - process lines in parallel

### 0. idea

파일 읽는걸 병렬처리 해보자

step2 code는 single core에서만 도는데,\
이 방식은 여러 코어에서 병렬로 돔

```java
Map<String, Measurement> resultMap = Files.lines(Path.of(FILE)).parallel()
```
...을 사용한 방법

#### 0-1. .parallel().groupBy(groupingByConcurrent());
```java
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
```


### 1. jdk17: 1분 44초
```
300.36s user
29.69s system
316% cpu
1:44.34 total
```

- 병렬 프로그래밍 썼더니, user space에서 소요시간이 150% 늘었고,
- 커널모드에서 file i/o에 쓰는 시간도 600% 늘었는데,
- 총 소요시간은 1분 38초나 줄었다.

단, 316% CPU usage까지 올라가는걸 보면, 오래하다간 쓰로틀링 엄청 걸릴 듯 하다.


### 2. openjdk21: 1분 35초
```
412.60s user
29.51s system
464% cpu
1:35.21 total
```
jdk21의 .parallel()이 jdk17보다 더 최적화가 잘되있거나,
jdk21에 experimental 기능중에 하나인 경량 쓰레드인 fibers가 .parallel() 수행시 생기는 concurrency overhead 비용을 줄여줘서 성능개선이 된 듯 하다.

CPU 사용량만 봐도, 150%나 높은걸 보면, 수 많은 경량 쓰레드인 fibers가 CPU를 동시에 점유하려고 하기 때문에 저렇게 높히 올라간 것으로 보인다.


-----------------------------------------------
## step04 - parallel, but read files in 1MB chunks

### 0. idea

1. The ChunkReader Class read the file using chunck size of 1MB as ByteBuffer object. 
2. Then, the RowReader class takes these chunks as input and split it to individual rows containing City names and temperatures. 
3. Finally using the measurement class the operations on these rows are performed and results are aggregated in a concurrent map.

---
- 전체 파일을 한번에 parallel로 읽는게 아니라, manageable chunks로 나눠서 읽으면, memory overload를 피할 수 있다. 
- parallel processing techniques 중 하나라고 한다.

#### 0-1. main concepts

1. Parallel Process Chunks [160.5 -> 18] [twobiers]
   - Rather than reading data top to bottom and attempting to parallelize processing with batches of the parsed data, 
   - we read chunks of data (about 1 MB) and parrallelize processing per chunk.
2. Share Byte Array when Deserializing [18 -> 6.5] [Various]
    - When deserializing names after going through the effort of processing one byte at a time when processing a chunk of data we can re-use a single byte array to store the characters that make up the name. This removes the need to allocate and de-allocate memory for the buffer.
    - We can then use the new String(byte[], 0, length) constructor to create the String without worrying about clearing the underlying byte array as we provide a length.
3. Store ints Compute Doubles at End [6.5 -> 6.2] [None]
4. Use oracle GraalVM [6.2 -> 5.3] [None]
5. Process ByteBuffer for Name then Value [5.3 -> 4.7] [None]
    - Rather than processing the ByteBuffer in a single while (current != '\n') with a condition
    - to switch from getting the name to calculating the integer value on (current == ';') the
    - logic was split into 2 separate loops.
    - The first, while (current != ';') and a second, while (current != '\n').


### 1. jdk17: 30초
```
80.37s user
13.37s system
308% cpu
30.356 total
```

### 2. openjdk21: 27초
```
73.73s user
14.26s system
319% cpu
27.560 total
```

### 3. jdk21 GraalVM: 23초
```
53.33s user
11.77s system
272% cpu
23.853 total
```


-----------------------------------------------
# F. Resources

1. https://github.com/gunnarmorling/1brc?tab=readme-ov-file
2. https://tivrfoa.github.io/java/benchmark/performance/2024/02/05/1BRC-Timeline.html
