---\
Objective 

thread modularize


---\
why use thread?

일을 병렬로 처리 가능

CPU가 1+1을 100만번 끝내는데 걸리는 시간보다,
잼민이 100만명이 1+1을 동시에 끝내는 시간이 더 빠르다!


---\
why problematic? 

1. 다중 스레드 환경에서 두 개 이상의 스레드가 변경 가능한(mutable) 공유 데이터를(static일 확률 높음) 동시에 업데이트하면 경쟁 조건(race condition)이 발생한다.
2. 경쟁 조건(race condition)이란, 철수, 영희 둘 다 냉장고에 계란 써야하는데, 둘이 냉장고 뛰어와서 먼저온 철수가 영희를 밀치고 냉장고에서 계란을 얌체같이 꺼내감. 쓰면 바로 돌려놔야 하는데, 다른 음식 끝날 때 까지 안돌려놓으니까, 영희는 철수가 계란 돌려놓을 때 까지 냉장고에서 대기타야 함. -> 성능저하 발생.
3. race condition(동시성 문제)를 해결하려면?

---\
how to solve race condition(concurrecy problem)?

1. synchronized
2. volatile
3. use java Atomic Class
