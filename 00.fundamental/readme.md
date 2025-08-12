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



---
# A. what

## --- 1. 수신 및 스레드 할당 계층 (OS & WAS Layer) ---

### Step 1: 요청 수신 및 Worker 스레드 배정

1. 네트워크 인터페이스 카드(NIC) & OS 커널:
   - 클라이언트(브라우저, 앱 등)의 HTTP 요청은 TCP/IP 패킷 형태로 서버의 물리적 NIC에 도달합니다.
   - OS 커널의 네트워크 스택은 이 패킷을 재조립하여 TCP 소켓 스트림으로 만들고, WAS가 리스닝하는 포트(예: 8080)로 전달합니다.
2. WAS의 I/O 스레드와 Worker 스레드 풀:
   - 현대 WAS(Tomcat, Netty 기반의 Spring WebFlux 등)는 Non-blocking I/O(NIO) 모델을 사용합니다. 소수의 I/O 스레드(Selector 스레드)가 여러 클라이언트의 연결을 동시에 관리합니다.
   - I/O 스레드는 요청 데이터 읽기가 완료되면, 실제 비즈니스 로직을 처리할 Worker 스레드에게 작업을 위임합니다. 이 Worker 스레드는 WAS가 미리 생성해 둔 **스레드 풀(Thread Pool)** 에서 가져옵니다.
   - 이 시점부터 요청 하나는 Worker 스레드 하나와 1:1로 매칭됩니다. 이 스레드는 JVM에 의해 관리되는 OS 네이티브 스레드이며, OS의 스케줄러에 의해 CPU Core에 할당되어 실행 시간을 얻습니다.

#### I/O 이벤트 통지와 작업 위임 방식 (epoll과 BlockingQueue)

- OS 커널의 역할 심화: WAS의 NIO I/O 스레드가 수천 개의 커넥션을 효율적으로 관리할 수 있는 이유는 리눅스 커널의 epoll (또는 BSD의 kqueue, Windows의 IOCP) 덕분입니다. I/O 스레드는 epoll에 "이 소켓들 중 읽을 데이터가 도착하면 나에게 알려줘"라고 한 번에 등록해 둡니다. 그 후 I/O 스레드는 이벤트가 발생할 때까지 대기(block)하다가, 커널이 "3번, 5번, 100번 소켓에 데이터 도착했음!"이라고 알려주면 깨어나 해당 소켓들만 효율적으로 처리합니다. 이는 수많은 소켓을 일일이 확인하는(polling) 방식보다 훨씬 효율적입니다.
- 작업 위임의 구체적인 방법: I/O 스레드가 요청 데이터를 모두 읽은 후 Worker 스레드에게 작업을 넘기는 과정은 보통 내부적으로 **공유 큐(Shared Queue, 주로 BlockingQueue)**를 통해 일어납니다. I/O 스레드는 파싱된 요청 정보가 담긴 '작업 객체'를 큐에 넣고(offer), Worker 스레드 풀의 스레드들은 이 큐에서 작업을 꺼내(take) 갑니다. 이는 생산자-소비자 패턴의 전형적인 예시입니다.

## --- 2. 애플리케이션 실행 계층 (JVM & Application Layer) ---

### Step 2: 비즈니스 로직 실행과 JVM 메모리 활용

1. 스택(Stack) 메모리 활용:
   - 할당된 Worker 스레드는 자신만의 독립적인 스택(Stack) 메모리 영역을 가집니다.
   - 요청이 컨트롤러(@Controller) -> 서비스(@Service) -> 리포지토리(@Repository) 순으로 호출될 때마다, 각 메소드 호출 정보(매개변수, 지역 변수, 반환 주소 등)가 스택 프레임(Stack Frame) 형태로 해당 스레드의 스택에 차곡차곡 쌓입니다.
2. 힙(Heap) 메모리 활용:
   - 메소드 내부에서 new UserDto()나 new ArrayList<>()처럼 객체를 생성하면, 이 객체들은 JVM의 모든 스레드가 공유하는 힙(Heap) 메모리 영역에 생성됩니다.
   - 스택에는 이 힙에 생성된 객체를 가리키는 참조(reference) 값만 저장됩니다.
3. JIT (Just-In-Time) 컴파일러:
   - 최초 실행 시, Java 바이트코드는 JVM의 인터프리터에 의해 한 줄씩 해석되며 실행됩니다.
   - 하지만 특정 서비스나 리포지토리의 메소드처럼 반복적으로 호출되어 "뜨거운(Hotspot)" 코드가 되면, JVM의 JIT 컴파일러가 작동합니다.
   - JIT 컴파일러는 이 바이트코드를 OS와 CPU에 최적화된 네이티브 머신 코드로 컴파일하여 캐싱합니다. 이후 동일한 코드 호출 시, 인터프리팅 없이 네이티브 코드가 직접 실행되어 성능이 대폭 향상됩니다.

#### JIT 컴파일러의 심화 과정 (단계별 컴파일과 역최적화)

- 단계별 컴파일(Tiered Compilation): 최신 JVM은 단순히 '인터프리터 -> JIT 컴파일러'의 2단계가 아니라, 여러 단계의 최적화를 거칩니다.
  - C1 (Client) 컴파일러: 빠른 컴파일 속도에 중점을 둬서 일단 기본적인 네이티브 코드를 생성합니다.
  - C2 (Server) 컴파일러: 코드가 계속해서 '더 뜨거워지면', C2 컴파일러가 더 많은 시간을 들여 훨씬 공격적이고 고도화된 최적화(예: 메소드 인라이닝)를 수행한 코드를 생성합니다.
- 역최적화(Deoptimization): JIT 컴파일러는 때로 "이 코드는 항상 이런 식으로만 쓰일 거야"라는 과감한 가정을 하고 최적화를 수행합니다. 그런데 만약 그 가정이 틀리는 예외적인 상황이 발생하면, JVM은 최적화된 네이티브 코드를 포기하고 다시 인터프리터 모드로 돌아가는데, 이를 역최적화라고 합니다. 이는 성능에 일시적인 출렁임을 유발할 수 있습니다.

### Step 3: 객체 생성과 Eden 영역으로의 할당

1. 객체 생성 코드 실행:
   - Worker 스레드가 스트림에서 읽은 원본 로그 데이터(byte 배열)를 바탕으로 new String(logData) 코드를 실행하여 문자열 객체를 생성합니다. 이 외에도 로그를 파싱하여 담을 DTO(Data Transfer Object) 객체 등 수많은 객체들이 new 키워드를 통해 생성됩니다.
2. 힙(Heap) 내의 할당 위치:
   - 생성된 String 객체와 DTO 객체는 JVM의 힙(Heap) 메모리에 할당됩니다.
   - 힙은 크게 Young Generation과 Old Generation으로 나뉩니다.
   - 방금 new로 생성된 "신입" 객체들은 거의 모두 Young Generation 영역, 그중에서도 Eden 공간에 할당됩니다.

### Step 4: Eden 영역 포화와 Minor GC 발생

1. Eden의 운명:
   - 초당 300개의 요청을 처리하면, 각 요청마다 생성되는 수많은 단기 객체들로 인해 Eden 영역은 매우 빠르게 가득 차게 됩니다.
2. Minor GC 트리거:
   - Eden 영역이 꽉 찬 상태에서 새로운 객체를 할당하려는 시도가 발생하면, JVM은 Minor GC를 트리거합니다.
3. Stop-the-World (첫 번째 시련):
   - Minor GC를 실행하기 위해, JVM은 GC 스레드를 제외한 모든 애플리케이션 스레드(Worker 스레드 포함)를 일시 중지시킵니다. 이것이 바로 악명 높은 "Stop-the-World" (STW) 입니다. STW가 발생하는 이유는, GC가 객체들의 참조 관계를 파악하는 동안 애플리케이션 스레드가 객체의 상태를 변경하면 안 되기 때문입니다.
4. 객체의 생사 판단 (Reachability):
   - STW 상태에서 GC는 GC Root (스레드의 스택에 있는 지역 변수, static 변수 등)에서부터 시작하여 참조 체인을 따라가며 살아있는 객체(Reachable)를 모두 마킹합니다.

#### GC의 효율을 위한 장치 (Card Table과 Write Barrier)

- 질문: Minor GC는 Young 영역만 스캔하는데, 만약 Old 영역의 객체가 Young 영역의 객체를 참조(Old -> Young)하고 있다면 이 참조를 어떻게 발견할까요? Old 영역 전체를 스캔하는 것은 비효율적입니다.
- 답 (Card Table): JVM은 Card Table이라는 메모리 영역을 사용합니다. 이는 Old Generation을 일정한 크기(예: 512바이트)의 '카드(Card)'로 나눈 비트맵입니다. 만약 Old 영역의 객체가 Young 영역의 객체를 참조하도록 참조 관계가 변경되면(예: oldObj.field = youngObj;), JVM은 Write Barrier라는 기법을 통해 해당 Old 객체가 속한 카드를 '더럽다(Dirty)'고 마킹합니다.
- 동작: Minor GC가 발생하면, GC는 전체 Old 영역을 스캔하는 대신 이 Card Table만 확인하여 'Dirty'로 표시된 카드에 해당하는 메모리 영역만 스캔해서 Old -> Young 참조를 찾아냅니다. 이는 Minor GC의 속도를 극적으로 향상시키는 핵심 기술입니다.

### Step 5: 살아남은 객체의 이동 (Eden → Survivor 0)

1. 대부분의 객체는 사망:
   - 웹 요청 처리 중에 생성된 객체들은 대부분 해당 요청 처리가 끝나면 더 이상 참조되지 않으므로, '사망한' 객체(Unreachable)로 판단됩니다. 이 객체들은 그냥 그 자리에 버려지며, Eden 영역 전체가 초기화되면서 메모리에서 사라집니다.
2. 생존자의 이주:
   - GC가 마킹한 '살아있는' 소수의 객체들은 Survivor 영역 중 하나(예: S0)로 복사됩니다. Survivor 영역은 두 개(S0, S1)가 존재합니다.
3. 작업 재개:
   - 살아남은 객체들의 복사가 끝나면 Eden 영역은 완전히 비워지고, STW가 풀리면서 멈췄던 애플리케이션 스레드들이 다시 작업을 재개합니다. Minor GC의 STW는 보통 수 밀리초(ms)로 매우 짧습니다.

### Step 6: Survivor 영역에서의 반복되는 삶과 노화

1. 두 번째 Minor GC:

   - 애플리케이션이 계속 실행되어 Eden이 다시 가득 차면, 또 다른 Minor GC와 STW가 발생합니다.

2. 생존자들의 재이주:
   - 이번에는 GC가 Eden 영역과 현재 사용 중인 Survivor 영역(S0) 모두를 스캔하여 살아있는 객체를 찾습니다.
   - 그리고 이 모든 생존자들을 **다른 비어있는 Survivor 영역(S1)**으로 복사합니다.
3. Age 증가:
   - 이때, S0에서 S1으로 성공적으로 이주한 객체들은 Age 값이 1 증가합니다. 처음 Eden에서 S0로 이주했던 객체는 이제 Age가 1이 됩니다. 이 과정이 반복될 때마다 살아남은 객체들의 Age는 계속해서 늘어납니다.

### Step 7: Old Generation으로의 승격 (Promotion)

1. 장수 객체의 탄생:

   - 객체의 Age가 미리 설정된 임계값(MaxTenuringThreshold, 보통 15)에 도달하면, JVM은 이 객체를 "단기적인 유행을 타는 객체가 아니라, 오랫동안 사용될 중요한 객체"라고 판단합니다.

2. 승격:
   - 다음 Minor GC가 발생했을 때, 이 Age 임계값을 넘은 객체는 더 이상 다른 Survivor 영역으로 가지 않고 Old Generation 영역으로 이동(승격, Promotion)됩니다. DB 커넥션 풀의 커넥션 객체, 설정 정보 캐시 등 애플리케이션 전역에서 오랫동안 사용되는 객체들이 주로 승격됩니다.

### Step 8: 최악의 시나리오: Major GC (Full GC)와 긴 Stop-the-World

1. Old Generation 포화:
   - 승격된 객체들이 계속 쌓이거나, 혹은 처음부터 매우 큰 객체(거대한 배열 등)가 Old Generation에 직접 할당되어 이 영역마저 가득 차게 되면, JVM은 Major GC 또는 Full GC를 실행합니다.
2. 두 번째, 그리고 더 긴 시련:
   - Major GC는 Young Generation뿐만 아니라 훨씬 크고 복잡한 Old Generation 전체를 정리해야 하므로, Minor GC에 비해 Stop-the-World 시간이 훨씬 깁니다. 수십 ms에서 심하면 수 초(seconds)에 이를 수 있습니다.
3. 300 RPS 서버에 미치는 치명적 영향:
   - 만약 Major GC로 인해 500ms 동안 STW가 발생했다면, 그 시간 동안 150개의 실시간 요청을 전혀 처리하지 못하고 멈춰있게 됩니다. 이는 실시간 스트리밍 시스템에서 엄청난 지연(Latency)과 데이터 유실로 이어질 수 있으며, 클라이언트 입장에서는 타임아웃을 겪게 되는 최악의 상황입니다.

#### 현대적인 GC의 동작 방식 (G1GC를 중심으로)

- 기존의 GC(Parallel GC 등)가 Young/Old 영역을 물리적으로 나누는 반면, **G1GC (Garbage-First GC)**는 전체 힙을 Region이라는 작은 단위(보통 1~32MB)로 나눕니다. 각 Region은 동적으로 Eden, Survivor, Old의 역할을 맡습니다.
- G1GC의 목표: 긴 STW를 유발하는 Full GC를 피하는 것입니다. G1GC는 백그라운드에서 전체 힙을 꾸준히 스캔(Concurrent Marking)하여 '쓰레기가 가장 많이 쌓인 Region'들을 파악하고, 짧은 STW를 통해 이들 Region의 살아있는 객체만 다른 Region으로 옮기는(Evacuation) 방식으로 점진적으로 메모리를 회수합니다. 이 방식 덕분에 G1GC는 예측 가능한 짧은 STW 시간을 목표로 동작할 수 있어, 실시간성이 중요한 서버에 매우 적합합니다.

### Step 9: 커넥션 풀(Connection Pool)에서 커넥션 획득

1. 커넥션 획득 요청:

- 리포지토리 계층(@Repository)의 코드가 DB 작업을 위해 dataSource.getConnection()을 호출합니다. 이는 미리 설정된 DB 커넥션 풀(주로 HikariCP)에 연결을 요청하는 것입니다.
- 왜 커넥션 풀을 사용하는가? TCP 연결 수립, MySQL 인증 및 세션 설정은 비용이 매우 높은 작업입니다. 매번 이 과정을 반복하지 않고, 미리 생성된 커넥션을 재사용하여 오버헤드를 최소화하고 응답 시간을 단축하기 위함입니다.

2. 커넥션 풀의 동작:
   - Worker 스레드는 커넥션 풀에 유휴(idle) 상태의 커넥션이 있는지 확인합니다.
   - 가용한 커넥션이 있을 경우: 즉시 커넥션을 받아 다음 단계로 진행합니다. 이 커넥션 객체는 사실 실제 물리적 커넥션을 감싼 프록시(Proxy) 객체입니다.
   - 가용한 커넥션이 없을 경우: Worker 스레드는 WAITING 또는 TIMED_WAITING 상태에 들어가 다른 스레드가 커넥션을 반납할 때까지 대기합니다. (이때 CPU 자원을 소모하지 않습니다.) 만약 설정된 타임아웃(예: connectionTimeout)을 초과하면 예외가 발생합니다.

#### 커넥션 풀 프록시 객체의 역할과 상태 관리

- connection.close()의 비밀: 애플리케이션 개발자가 connection.close()를 호출했을 때 실제 물리적 TCP 연결이 끊기지 않는 이유는, 커넥션 풀이 반환한 객체가 실제 Connection이 아닌 프록시(Proxy) 객체이기 때문입니다. 이 프록시는 close() 메소드를 가로채서(intercept), 실제 연결을 끊는 대신 커넥션을 다시 풀에 반납하는 로직을 수행합니다.
- 커넥션 상태 검사(Health Check): 풀에 오랫동안 머물던 커넥션은 MySQL 서버에 의해 타임아웃으로 끊어졌을 수 있습니다. HikariCP와 같은 현대적인 커넥션 풀은 풀에서 커넥션을 꺼내 애플리케이션에 제공하기 직전, isValid() 메소드나 가벼운 validationQuery(예: SELECT 1)를 실행하여 해당 커넥션이 여전히 유효한지 매우 빠르게 검사합니다.

## --- 3. JDBC 및 네트워크 전송 계층 (Driver & OS Layer) ---

### Step 10: JDBC API 호출 및 드라이버의 역할

1. PreparedStatement 생성 및 파라미터 바인딩:
   - 애플리케이션은 connection.prepareStatement("SELECT \* FROM users WHERE id = ?")와 같이 PreparedStatement를 생성합니다.
   - 성능 및 보안적 이점: Statement와 달리 PreparedStatement를 사용하면, SQL 템플릿(Query Plan)이 MySQL 서버에 의해 캐싱될 수 있습니다. ?에 들어가는 파라미터 값만 바꿔서 여러 번 실행하면, MySQL은 파싱과 최적화(MySQL 메모의 Step 3, 4) 단계를 건너뛰고 바로 실행 계획을 재사용할 수 있어 훨씬 효율적입니다. 또한, 파라미터가 데이터로 명확히 구분되어 전달되므로 SQL Injection 공격을 원천적으로 방어합니다.
   - ps.setLong(1, 12345L)처럼 파라미터를 설정하면, 이 값들은 JDBC 드라이버 내부에 임시로 저장됩니다.
2. 쿼리 실행 (executeQuery) 및 프로토콜 변환:
   - ps.executeQuery() 메소드가 호출되는 순간, MySQL Connector/J와 같은 JDBC 드라이버가 본격적으로 일을 시작합니다.
   - 드라이버는 PreparedStatement의 SQL 템플릿과 바인딩된 파라미터 값들을 조합하여 MySQL 클라이언트/서버 프로토콜 형식의 바이너리 데이터 패킷으로 직렬화(Serialization)합니다.

### Step 11: 소켓 쓰기(Socket Write)와 OS 커널을 통한 전송

1. 시스템 콜(System Call) 발생:
   - JDBC 드라이버는 커넥션 객체가 내부적으로 유지하고 있는 java.net.Socket 객체의 출력 스트림(OutputStream)에 이 바이너리 패킷을 씁니다.
   - 이 write() 작업은 JVM 레벨에서 끝나는 것이 아니라, OS 커널에 작업을 위임하는 **시스템 콜(System Call)** 을 발생시킵니다. 이 순간, 스레드의 실행 모드는 사용자 모드(User Mode)에서 커널 모드(Kernel Mode)로 전환됩니다.
2. OS 네트워크 스택의 역할:
   - 커널 모드로 전환된 스레드를 대신해, OS 커널은 전달받은 데이터(MySQL 프로토콜 패킷)를 TCP/IP 스택을 통해 하위 계층으로 내려보냅니다.
   - TCP 계층: 데이터를 신뢰성 있게 전달하기 위해 세그먼트(Segment) 단위로 나누고, 출발지/목적지 포트 번호, 시퀀스 번호 등의 정보를 담은 TCP 헤더를 붙입니다.
   - IP 계층: 각 TCP 세그먼트에 출발지/목적지 IP 주소 정보를 담은 IP 헤더를 붙여 IP 패킷(Packet)을 만듭니다.
   - Data Link 계층: IP 패킷에 MAC 주소 등 물리적 주소 정보를 담은 이더넷 프레임(Frame) 헤더를 붙입니다.
3. 물리적 전송:
   - 최종적으로 만들어진 이더넷 프레임은 NIC(네트워크 인터페이스 카드)의 버퍼로 전송됩니다.
   - NIC는 이 프레임 데이터를 전기 신호 또는 빛 신호로 변환하여 물리적인 네트워크 케이블(LAN선, 광케이블)을 통해 MySQL 서버로 발사합니다.

#### 소켓 쓰기와 커널 버퍼의 역할

커널 버퍼(Kernel Buffer): write() 시스템 콜이 발생했을 때, 데이터는 즉시 NIC를 통해 전송되는 것이 아니라 일단 커널 메모리 공간에 있는 **소켓의 송신 버퍼(Socket Send Buffer)** 로 복사됩니다. 애플리케이션 스레드는 이 복사가 완료되면 즉시 자신의 일(커널 모드에서 사용자 모드로 복귀)을 계속할 수 있습니다. 데이터의 실제 물리적 전송은 OS 커널의 네트워크 스택이 이 버퍼의 내용을 비동기적으로 처리하여 이루어집니다. 이는 애플리케이션이 네트워크 I/O를 기다리며 멈춰있는 시간을 최소화해 줍니다.

# B 비동기 파이프라인 가이드라인

이 아키텍처는 '수집'과 '적재'를 완전히 분리한 비동기 파이프라인을 전제로 합니다.

## 1단계: 네트워크 계층 - 데이터 수신 (Network Layer)

- 상황: 수만 개의 클라이언트가 초당 수천 건의 로그 데이터를 동시에 전송합니다.
- 기술: Netty 기반의 자체 개발 TCP 소켓 서버. (e-2)

1. Connection 수립: Netty의 **Boss EventLoopGroup** 이 클라이언트의 TCP 연결 요청을 받습니다. 이 스레드는 연결 수립 외에는 아무 일도 하지 않고 즉시 다음 연결을 받으러 갑니다.
2. I/O 작업 위임: 수립된 연결(Channel)은 **Worker EventLoopGroup** 에 위임됩니다. Worker 스레드들은 Non-blocking I/O 방식으로 여러 채널로부터 데이터가 들어오는지 감시합니다.
3. 데이터 수신: 특정 채널에 데이터가 들어오면, Worker 스레드는 커널 버퍼에 있는 데이터를 애플리케이션의 **ByteBuf** 로 읽어들입니다. 여기까지는 순수 byte 데이터입니다.

최적화 포인트: HTTP/JSON이 아닌 경량 바이너리 프로토콜을 사용하여 네트워크 대역폭(e-1)과 CPU 파싱 비용을 최소화합니다.

## 2단계: 애플리케이션 계층 - 최소 처리 (Application Layer)

- 상황: Worker 스레드가 ByteBuf 형태의 원시 데이터를 받았습니다.
- 기술: Object Pooling 및 최소한의 데이터 처리 로직. (a-2)

1. 객체 재사용: new LogData()를 호출하는 대신, **미리 생성해 둔 객체 풀(Object Pool)** 에서 LogData 인스턴스를 하나 빌려옵니다. 이는 GC 발생을 억제하는 핵심적인 최적화입니다.
2. 데이터 파싱: ByteBuf를 파싱하여 재사용 객체의 필드를 채웁니다. 이 과정은 CPU를 소모하므로 최대한 가볍게 구현되어야 합니다.
3. 최소 검증: 데이터 포맷이 올바른지, 필수값이 누락되지 않았는지 등 최소한의 검증만 수행합니다. 복잡한 비즈니스 로직은 이 단계에서 절대 수행하지 않습니다.

## 3단계: 비동기 파이프라인 - 책임 전가 (Asynchronous Pipeline)

- 상황: 최소한의 처리가 끝난 LogData 객체가 준비되었습니다.
- 기술: 메시지 큐 (Kafka 등) 또는 메모리 기반의 Disruptor 같은 고성능 큐. (b-3, b-4)

1. 데이터 직렬화: LogData 객체를 다시 효율적인 바이너리 포맷(예: Protobuf, Avro)으로 직렬화합니다.
2. 큐에 적재: 직렬화된 데이터를 메시지 큐에 Producer로서 전송(Publish)합니다.
3. 객체 반납: 메시지 큐 전송이 완료되면, 2단계에서 빌렸던 LogData 객체를 다시 객체 풀에 반납하여 다음 요청 처리에 재사용되도록 합니다.

여기까지가 '데이터를 받는 쪽'의 역할입니다. 이 모든 과정은 밀리초(ms) 단위로 매우 빠르게 끝나며, 수집 서버는 DB의 상태와 관계없이 안정적으로 최대 성능을 낼 수 있습니다.

## 4단계: 배치 처리 계층 - 데이터 인출 및 그룹핑 (Batch Layer)

- 상황: 메시지 큐에는 처리되지 않은 데이터가 쌓여 있습니다. 별도의 서버에서 동작하는 **배치 애플리케이션(Consumer)** 이 이 데이터를 처리합니다.
- 기술: Spring Batch의 Partitioning, Multi-threaded Step 등. (b-2)

1. 데이터 인출: 컨슈머 스레드가 메시지 큐에서 정해진 개수(예: 10,000건)의 데이터를 한 번에 가져옵니다(Polling).
2. 데이터 역직렬화 및 그룹핑: 가져온 바이너리 데이터를 다시 LogData 객체로 변환하고(이때도 객체 풀 사용), `List<LogData>`와 같은 컬렉션에 담습니다. 이 리스트가 DB에 한 번에 적재될 하나의 '배치' 또는 '청크(Chunk)'가 됩니다.

## 5단계: JDBC 계층 - DB 적재 (JDBC Layer)

- 상황: 10,000건의 LogData 객체가 담긴 리스트가 준비되었습니다.
- 기술: rewriteBatchedStatements=true 옵션이 적용된 JDBC 배치 처리. (d-1)

1. 커넥션 획득: HikariCP와 같은 커넥션 풀에서 DB 커넥션을 하나 빌려옵니다.
2. 배치 준비: JdbcTemplate.batchUpdate()나 JpaRepository.saveAll() 같은 메서드가 호출됩니다. 내부적으로는 루프를 돌며 리스트에 있는 10,000개의 LogData를 PreparedStatement의 배치(batch)에 추가합니다 (addBatch()).
3. 쿼리 전송: executeBatch()가 호출되면, JDBC 드라이버는 rewriteBatchedStatements=true 설정에 따라 10,000개의 INSERT 문을 하나의 거대한 Multi-value INSERT 문으로 재작성합니다.
4. DB 실행: 재작성된 단 하나의 SQL 문이 네트워크를 통해 MySQL로 전송되고 실행됩니다.
5. 커밋 및 커넥션 반납: 트랜잭션이 커밋되고, 사용이 끝난 DB 커넥션은 다시 커넥션 풀로 반납됩니다.

# C. 최적화 기법

## a. --- java layer ---

### a-1. out of memory 피하기1 - 데이터 스트리밍 (Streaming Reads)

3천만 건의 데이터를 애플리케이션 메모리에 한 번에 올리는 것은 OutOfMemoryError를 유발합니다.

DB나 파일에서 원본 데이터를 읽을 때, 모든 결과를 메모리에 올리는 대신 데이터를 물 흐르듯이 한 건씩(row-by-row) 처리해야 합니다.

- Spring Batch: JdbcCursorItemReader를 사용하면 내부적으로 statement.setFetchSize(Integer.MIN_VALUE)를 설정하여 MySQL에서 스트리밍 방식으로 데이터를 가져옵니다.
- 파일 처리: 대용량 CSV나 JSON 파일을 읽을 때는 전체를 파싱하는 라이브러리 대신, 라인별/이벤트별로 처리하는 스트리밍 방식의 파서를 사용해야 합니다.

### a-2. OOM 피하기2: 1 row = 1 object creation 피하고, 1개 객체 돌려쓰기

"1로그당 1객체 생성도 너무 크다"는 말은 **가비지 컬렉터(Garbage Collector, GC)** 의 부담을 의미합니다. 분당 70만 건의 로그가 들어올 때마다 객체를 생성하면, 메모리에 작은 객체들이 계속 쌓이고(Minor GC), 이들이 오래 살아남아 Old 영역으로 넘어가면 결국 시스템 전체를 멈추는 **'Stop-the-World' (Major GC)** 를 유발합니다.

구현 방식: 로그 파싱에 필요한 객체나 데이터 전송용 객체(DTO)를 미리 정해진 개수만큼 만들어 둔 **객체 풀(Object Pool)** 을 사용했을 것입니다. (예: Apache Commons Pool). 필요할 때마다 new로 생성하는 대신 풀에서 빌려 쓰고, 사용이 끝나면 반납하여 객체 생성을 최소화하고 GC 발생을 억제합니다.

### a-3. TLAB (Thread-Local Allocation Buffer)

300 RPS 환경에서는 수많은 스레드가 동시에 객체 생성을 시도합니다. 만약 모든 스레드가 단 하나의 Eden 영역에 락(Lock)을 걸고 메모리를 할당받으려 한다면 엄청난 병목 현상이 발생할 것입니다. 이를 막기 위해 JVM은 각 스레드에게 Eden 영역의 일부를 **개인 전용 할당 공간(TLAB)**으로 미리 할당해 줍니다. 따라서 스레드는 다른 스레드와의 경합 없이 자신의 TLAB에 매우 빠른 속도로 객체를 할당할 수 있습니다.

## b. --- thread layer ---

### b-1. 메모리 && thread pool 수 직접 조절

단순히 application.yml을 수정하는 수준이 아닙니다.

JVM의 Heap 메모리(-Xmx), GC 알고리즘(G1GC, ZGC 등) 설정, 그리고 ThreadPoolTaskExecutor 같은 Spring의 스레드 풀을 프로파일링 결과에 기반하여 최적의 값으로 튜닝했을 것입니다.

로그 수집(I/O-bound) 작업과 전처리(CPU-bound) 작업의 특성에 맞춰 스레드 풀을 분리하고, 코어 수와 작업 큐 크기를 정밀하게 조절했을 가능성이 큽니다.

### b-2. parallel processing

1. Multi-threaded Step: 하나의 배치 작업을 여러 스레드가 나누어 병렬로 처리합니다. 3천만 건을 4개의 스레드가 약 750만 건씩 동시에 처리하여 전체 시간을 단축시킵니다.
2. Partitioning: 여기서 한 단계 더 나아가, 작업을 여러 개의 독립적인 '파티션'으로 분할하고, 각 파티션을 별도의 스레드나 심지어 별도의 서버(Worker)에서 실행합니다. 이는 애플리케이션 레벨에서 수평적으로 확장하는 가장 강력한 방법입니다.

### b-3. 비동기 파이프라인 구축

사용한 이유: 로그 수집, 전처리, DB 적재를 하나의 흐름으로 묶으면, 가장 느린 DB 적재 과정이 전체 시스템의 발목을 잡습니다.

구현 방식:

1. 수집 계층: 자체 개발한 TCP 서버는 데이터를 받아 메시지 큐(Kafka 등)나 자체 제작한 파일 DB에 던져 넣고 즉시 응답합니다.
2. 처리/적재 계층: 별도의 배치(Batch) 애플리케이션이 메시지 큐나 파일 DB에서 데이터를 안전하게 가져와, 포맷을 변경하고 MySQL에 Bulk Insert 합니다.

이 구조는 각 계층을 독립적으로 확장하고, DB 장애가 발생해도 로그 수집에는 영향이 없도록 만드는 핵심적인 설계입니다.

### b-4. 데이터 받는 쪽 thread갯수 미리 할당하고 디비 쓰는 쪽 thread 갯수 미리 할당하기

데이터를 받는 부분과 DB에 쓰는 부분을 완전히 분리하는 아키텍처입니다.

1. 수집(Producer): 외부로부터 들어오는 3천만 건의 데이터는 복잡한 처리 없이 즉시 Kafka, RabbitMQ 같은 메시지 큐에 던져 넣기만 합니다. 이 작업은 매우 빠릅니다.
2. 적재(Consumer): 별도의 컨슈머 애플리케이션(들)이 메시지 큐에서 데이터를 원하는 만큼 가져와, 위에서 설명한 JDBC 최적화, 배치 처리를 통해 DB에 여유롭게 적재합니다.

이 방식은 DB에 부하가 발생하더라도 데이터 수집 시스템은 영향을 받지 않고, 컨슈머의 개수를 늘려 쓰기 처리량을 유연하게 조절할 수 있는 매우 탄력적이고 안정적인 구조입니다.

## c. --- file i/o layer ---

### c-1. Bulk Insert를 위한 직접적인 접근 (LOAD DATA)

ORM의 편리함을 포기하고 성능을 극한으로 끌어올리는 방법입니다.

애플리케이션이 3천만 건의 데이터를 DB에 직접 INSERT 하는 대신, 빠른 로컬 디스크에 CSV 파일로 먼저 씁니다.

그 후, MySQL에 LOAD DATA LOCAL INFILE 명령어를 실행합니다.

이 명령어는 MySQL 클라이언트가 직접 CSV 파일을 읽어 서버로 전송하고, 서버는 SQL 처리 계층의 많은 부분을 건너뛰고 스토리지 엔진에 바로 데이터를 쓰기 때문에, JDBC를 통한 INSERT와는 비교할 수 없을 정도로 빠릅니다.

### c-2. sequantial file i/o instead of random access file i/o

파일 데이터베이스: 수신한 로그를 DB에 바로 INSERT하는 것은 디스크 I/O 병목을 유발합니다.

대신, 수신한 데이터를 **빠른 로컬 디스크에 순차적으로(sequentially) 기록하는 로그 파일(Write-Ahead Log, WAL과 유사한 형태)** 을 자체 제작했을 가능성이 높습니다.
디스크에 순차적으로 쓰는 작업은 랜덤하게 쓰는 것보다 훨씬 빠릅니다.

## d. --- jdbc layer ---

### d-1. batch로 1000개씩 묶어서 보내기

`rewriteBatchedStatements=true`
`url: jdbc:mysql://localhost:3306/mydatabase?rewriteBatchedStatements=true`

```
-- BEFORE (1000번의 네트워크 통신)
INSERT INTO sales (...) VALUES (...);
INSERT INTO sales (...) VALUES (...);
... (x1000)

-- AFTER (단 1번의 네트워크 통신)
INSERT INTO sales (...) VALUES (...), (...), ..., (...);
```

## e. --- network layer ---

### e-1. 대역폭 충분히 넓어야 한다.

### e-2. TCP 소켓 자체개발

일반적인 Spring MVC의 HTTP + JSON 방식은 대용량 로그 수신에 너무 무겁습니다. HTTP 프로토콜 파싱, JSON 직렬화/역직렬화 과정에서 발생하는 CPU 및 메모리 오버헤드를 감당할 수 없기 때문입니다.

TCP 소켓: Netty 같은 저수준 네트워크 프레임워크를 사용하거나 직접 구현하여, 정해진 프로토콜에 따라 최소한의 데이터(주로 byte[])만 주고받는 경량화된 수집 서버를 만들었을 것입니다. 이는 CPU 사용량을 극적으로 줄입니다.
