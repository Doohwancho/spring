---\
index

e-1. why use multi-thread?
e-2. why multi-thread could be problematic?
e-3. tomcat의 thread pool config 을 변경하며 실험해보기
e-4. thread pool의 동작방식 이해하기 

---\
e-1. why use thread?


일을 병렬로 처리 가능

CPU가 1+1을 100만번 끝내는데 걸리는 시간보다,
잼민이 100만명이 1+1을 동시에 끝내는 시간이 더 빠르다!


---\
e-2. why problematic? 


1. 다중 스레드 환경에서 두 개 이상의 스레드가 변경 가능한(mutable) 공유 데이터를(static일 확률 높음) 동시에 업데이트하면 경쟁 조건(race condition)이 발생한다.
2. 경쟁 조건(race condition)이란, 철수, 영희 둘 다 냉장고에 계란 써야하는데, 둘이 냉장고 뛰어와서 먼저온 철수가 영희를 밀치고 냉장고에서 계란을 얌체같이 꺼내감. 쓰면 바로 돌려놔야 하는데, 다른 음식 끝날 때 까지 안돌려놓으니까, 영희는 철수가 계란 돌려놓을 때 까지 냉장고에서 대기타야 함. -> 성능저하 발생.
3. race condition(동시성 문제)를 해결하려면?


---\
e-3. tomcat의 thread pool config 을 변경하며 실험해보기


---\
e-4. thread pool의 동작방식 이해하기 

1. 처음엔 coreThreadPool만큼 thread 생성 '준비'를 함.
2. 요청이 오면, 그제서야 (lazy) thread를 생성. 
3. coreThreadPool 이상으로 요청이 오면, queue에 쌓음.
4. queue에 기준치 이상으로 차면, maxThreadPool까지 thread를 생성.
5. 만약 maxThreadPool까지 생성되어도 queue에 기준치 이상으로 차면, reject 처리. -> 이 때, fail이 발생하는 것
6. thread의 작업이 끝나면, thread 자원을 반환하지 않고, queue에서 하나씩 쏙쏙 빼서 작업함.

