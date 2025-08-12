# 핵심 
핵심 병목점은 Blocking I/O (대기 시간) 이며, 
최적화의 핵심은 JIT 컴파일러를 통한 CPU 연산 최적화와 쓰레드 대기 시간 최소화(Non-Blocking, Virtual Threads)

# part1. thread pool 
## a. 동기서버 vs 비동기서버 
Java는 Non-Blocking I/O나 Virtual Threads를 사용해 느린 I/O 작업(DB조회, API 호출)이 끝날 때까지 쓰레드가 '멍때리며 노는 것'을 막는 것이 핵심

example

전통적인 Blocking 방식의 문제점:

1. 쓰레드가 커넥션 풀에서 커넥션을 빌린다.
2. UPDATE 쿼리를 DB로 보낸다.
3. DB가 트랜잭션을 처리하고, Redo Log를 쓰고, 결과를 리턴할 때까지 쓰레드는 아무것도 못 하고 그냥 기다린다.
4. 응답을 받으면 쓰레드는 풀로 돌아간다.

이 3번 과정에서 쓰레드가 CPU를 사용하지 않고 낭비됩니다. 

Blocking I/O의 한계: 전통적인 방식에서는 I/O 작업이 요청되면 끝날 때까지 해당 쓰레드가 다른 일을 못하고 멈춰있습니다. (e.g., DB에 쿼리를 날리고 응답이 올 때까지 대기). 사용자가 몰리면 쓰레드 풀이 금방 고갈되어 전체 시스템이 멈춥니다.

### example - 2core 4GiB RAM ec2에서 최대 RPS 
jdk8 기준, 
최대 스레드 수: 이론적으로는 수백 개(e.g., 700개 이상)도 가능하지만, 실질적으로는 200 ~ 400개 사이로 튜닝하여 사용합니다.
외부 I/O 작업이 많은 작업 (일반적인 웹앱): 200 ~ 400 RPS 가 현실적인 한계치가 될 가능성이 높습니다.

#### 1) JVM 스레드는 몇 개까지 만들 수 있는가?
> 총 RAM = OS 사용량 + JVM Heap + JVM Non-Heap + (스레드 스택 크기 * 스레드 개수)

- 2코어 4GiB(4096MB) 예시:
  - OS 사용량: 1024MB
  - JVM Heap: 2048MB (-Xmx2g)
  - Non-Heap/그 외: 256MB
  - 기본 스레드 스택: 1MB (64bit Linux 기준)
  - 남는 메모리: 4096 - 1024 - 2048 - 256 = **768MB**
  - 이론 최대 스레드: 768MB / 1MB = **768개**
- 실무에서는 컨텍스트 스위칭 부담 때문에 **200~400개 수준이 표준**

#### 2) i/o bound webserver에서 최대 RPS 측정 
- 예: 요청당 0.1초 소요 (이 중 10ms CPU, 90ms DB 등 I/O 대기)
- 블로킹 구조(JDK 8)에서는 대기 중 스레드가 놀게 됨
- 최대 RPS: (2코어 × 1000ms) / 10ms = **200 RPS**  
(CPU는 부하 꽉 차고, 스레드는 DB 등 대기 중인 상태가 다수)
- "리틀의 법칙" 적용: 200 RPS × 0.1s = **20개의 동시처리 스레드** 로 충분  
(하지만 여유 확보 차원에서 200~400개 추천)



### solution-1. non-blocking io(Reactive Programming)
single main thread + thread pool for cpu-intensive work 

Non-Blocking I/O (NIO): 한 개의 쓰레드가 여러 I/O 채널을 관리하며, "작업 끝났니?"라고 계속 물어보는 방식(Polling)으로 쓰레드가 멈추지 않고 다른 일을 처리할 수 있게 합니다. (Linux의 epoll이 이 방식의 핵심 기술)

- 작동 원리: "쿼리 날려놓고 내 할 일 할 테니, 끝나면 알려줘!" 방식입니다.
    1. 쓰레드 A가 UPDATE 쿼리를 DB로 보낸다.
    2. 쓰레드 A는 결과를 기다리지 않고 즉시 다른 요청을 처리하러 간다.
    3. 별도의 이벤트 루프(Event Loop)가 "DB 작업 끝났나?"를 계속 확인(Selector & epoll)한다.
    4. DB 작업이 끝나면, 이벤트 루프는 쓰레드 풀에서 다른 쓰레드 B를 가져와 후속 처리를 맡긴다.
- 장점: 적은 수의 쓰레드로 매우 높은 동시성 처리가 가능하다. (쓰레드가 놀지 않으므로)
- 단점: 콜백(Callback) 기반의 코드가 많아져 코드가 복잡하고 디버깅이 어렵다. (e.g., Spring WebFlux, Netty)

### solution-2. 동기 + virtual thread(jdk21)
Virtual Threads (Project Loom, JDK 21+): Non-Blocking 방식의 복잡한 코드를 없애고, 기존의 간단한 Blocking 코드 스타일을 그대로 유지하면서도 내부적으로는 쓰레드가 놀지 않도록 JVM이 알아서 처리해주는 혁신적인 기술입니다. I/O 대기가 발생하면 쓰레드를 잠시 내려놓고, 작업이 끝나면 다시 깨워서 이어 실행합니다.

- 작동 원리:
    1. 가상 쓰레드 A가 UPDATE 쿼리를 DB로 보낸다.
    2. JVM은 가상 쓰레드 A가 I/O 대기 상태에 들어간 것을 감지하고, 이 쓰레드가 점유하던 실제 OS 쓰레드를 반납시킨다. 가상 쓰레드 A는 잠시 잠든다.
    3. 반납된 OS 쓰레드는 다른 가상 쓰레드의 작업을 처리하며 계속 일한다.
    4. DB 작업이 끝나면, JVM은 잠들어 있던 가상 쓰레드 A를 깨우고, 놀고 있는 OS 쓰레드에 다시 할당하여 나머지 작업을 처리하게 한다.
- 장점: 개발자는 복잡한 Non-Blocking 코드를 짤 필요 없이, 기존의 간단하고 직관적인 Blocking 스타일로 코딩하면 된다. 성능은 Non-Blocking 수준으로 나온다.
- 단점: JDK 21 이상에서만 사용 가능하다.

## Step 1. Request Arrival & Thread Assignment
- 클라이언트의 HTTP 요청은 OS의 네트워크 스택을 거쳐 웹 서버(e.g., Tomcat, Netty)가 리스닝하는 포트에 도착합니다.
- Tomcat과 같은 웹 서버는 내부에 **쓰레드 풀(Thread Pool)** 을 가지고 있습니다. 요청이 들어오면 놀고 있던 쓰레드 하나를 꺼내 이 요청 처리를 맡깁니다.
    - 전통적인 Thread-Per-Request 모델: 1 요청 = 1 쓰레드. 간단하지만 OS 쓰레드는 비싸고 생성 개수에 한계가 있습니다. I/O 작업이 많은 서비스에서는 쓰레드들이 DB 응답을 기다리느라 금방 고갈됩니다.
    - Virtual Threads (JDK 21+): 1 요청 = 1 가상 쓰레드. 가상 쓰레드는 매우 가벼워서 수백만 개를 생성할 수 있습니다. I/O 대기 시 OS 쓰레드를 점유하지 않고 양보하므로, 적은 수의 OS 쓰레드로 엄청난 동시성 처리가 가능합니다.


# part2. compiler 

## a. runtime compiler, jit compiler 
반복적으로 실행되는 Java 바이트코드를 실시간으로 기계어(Native Code)로 컴파일하여, 인터프리터 방식의 느린 실행 속도를 C/C++ 수준으로 끌어올립니다 

1. JIT 컴파일러가 뜨거운 코드(Hot Code)를 네이티브 코드로 바꿔 CPU 연산을 극적으로 가속하고, 
2. JVM Heap 메모리에 객체를 효과적으로 캐싱하여 반복적인 작업을 피하는 것이 핵심

### Step 2. Bytecode to Native Code (JIT Compilation)
요청을 받은 쓰레드는 컨트롤러, 서비스, 리포지토리 등의 Java 코드를 실행합니다. 이때 JVM 내부에서는 놀라운 최적화가 일어납니다.

- Interpreter: JVM은 처음에 .class 파일의 바이트코드를 한 줄씩 해석(Interpret)하며 실행합니다. 이건 좀 느립니다.
- JIT (Just-In-Time) Compiler: JVM은 코드가 실행되는 것을 지켜보다가, 특정 메서드가 자주 호출되면("뜨겁다" or "Hot Spot") 이를 감지합니다. 그리고 이 '핫스팟'을 C1, C2와 같은 고성능 컴파일러를 사용해 기계어(Native Code)로 컴파일하여 캐싱해 둡니다. 다음부터 이 메서드가 호출될 땐 인터프리터 대신 최적화된 기계어가 바로 실행되어 속도가 비약적으로 향상됩니다.


## jdk별 compiler 및 특징/장단점 

# part3. jvm part 

## step3. stack && heap 
### 3-1. 전역공간 
### 3-2. stack 
각 쓰레드는 자신만의 스택(Stack)을 가집니다. 

메서드가 호출될 때마다 스택 프레임(메서드 정보, 지역 변수 등)이 쌓이고(push), 메서드가 끝나면 제거(pop)됩니다. 매우 빠르고, 쓰레드 간에 격리되어 안전합니다.

### 3-3. heap 
new User()처럼 객체를 생성하면 힙(Heap) 영역에 저장됩니다. 

힙은 모든 쓰레드가 공유하는 거대한 메모리 공간입니다. 

애플리케이션의 모든 상태(State)와 데이터는 사실상 여기에 존재합니다. 

MySQL의 Buffer Pool처럼, 애플리케이션 성능의 핵심이 되는 공간입니다.

## step4. garbage collecting 
### jdk별 GC 비교 
1. CMS/Parallel GC (JDK 8 이전): 처리량은 높지만 'Stop-the-World' 시간이 길었습니다.
2. G1 GC (JDK 8+ Default): 힙을 여러 구역(Region)으로 나눠, 짧은 'Stop-the-World' 시간을 목표로 예측 가능하게 GC를 수행합니다.
3. ZGC / Shenandoah (JDK 15+): 'Stop-the-World' 시간을 수 밀리초(ms) 단위로 줄여, 거의 멈춤 없는(Pauseless) GC를 목표로 하는 최신 기술입니다.



# part4. file i/o
### in linux, everything is file 
... even socket 

### socket 통신할 때 중요한 개념 
epoll?


## disk sequential i/o vs random i/o 


# part5. jdbc thread pool

## Step 5. Database I/O (JDBC)
서비스 로직이 DB에서 데이터를 가져와야 할 때, JDBC(Java Database Connectivity) API를 사용합니다.

- Connection Pool: DB 커넥션을 맺는 것은 매우 비싼 작업입니다. 따라서 애플리케이션은 시작할 때 미리 여러 개의 커넥션을 만들어 커넥션 풀(e.g., HikariCP) 에 저장해 둡니다.
    - DB 조회가 필요하면 풀에서 커넥션 하나를 빌려 쓰고, 작업이 끝나면 다시 반납합니다. MySQL의 쓰레드 풀과 완전히 동일한 개념입니다.
- Blocking & Waiting: 쓰레드는 커넥션을 통해 SELECT * FROM users WHERE id = 123 쿼리를 DB로 보냅니다. 이때부터 DB가 결과를 돌려줄 때까지 쓰레드는 Blocking 상태, 즉 '대기' 상태가 됩니다. 이것이 Java 애플리케이션 성능 저하의 주범입니다.

