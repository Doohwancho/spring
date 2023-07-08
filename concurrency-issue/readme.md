---\
Goal


learn ways to fix concurrency issue



---\
Topics


a. 재고시스템으로 알아보는 동시성이슈 해결방법\
b. thread\
c. lock




---\
Todos


a-0. init: 100개의 쓰레드가 동시에 공유자원을 update 할 때 race condition 발생! :white_check_mark:\
a-1. java - synchronized :white_check_mark:\
a-2. database - pessimistic lock :white_check_mark:\
a-3. database - optimistic lock :white_check_mark:\
a-4. database - named lock :white_check_mark:\
a-5. redis - Lettuce :white_check_mark:\
a-6. redis - Redisson :white_check_mark:\
a-7. database - update query :white_check_mark:\
a-8. performance test of all lock methods :white_check_mark:


b-1. thread :white_check_mark:\
b-2. thread pool :white_check_mark:\
b-3. daemon therad :white_check_mark:\
b-4. race condition :white_check_mark:\
b-5. deadlock :white_check_mark:\
b-6. os-solution1 - hold and wait :white_check_mark:\
b-7. os-solution2 - mutex :white_check_mark:\
b-8. os-solution3 - semaphore :white_check_mark:\
b-9. os-solution4 - monitor :white_check_mark:\
b-10. java-solution1 - synchronized :white_check_mark:\
b-11. java-solution2 - volatile :white_check_mark:\
b-12. java-solution3 - atomic class :white_check_mark:\
b-13. simple lock - class level lock vs block level lock :white_check_mark:\
b-14. simple lock - Object lock :white_check_mark:\
b-15. producer/consumer queue :white_check_mark:\
b-16. producer/consumer from scratch using wait() & notify() :white_check_mark:\
b-17. callable & future :white_check_mark:\
b-18. thread interrupted :white_check_mark:


---\
Reference


a. [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/lecture?courseSlug=%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C&unitId=125484&tab=curriculum)

b. java multi-threading, udemy course by John Purcell
b-1. [thread basic](https://github.com/woowacourse/jwp-hands-on) \
b-2. [thread pool](https://github.com/woowacourse/jwp-hands-on) \
b-3. [race condition](https://hudi.blog/race-condition-critical-section-mutual-exclusion/) \
b-4. [synchronization](https://github.com/woowacourse/jwp-hands-on) \
b-5. [Volatile 이란?](https://ttl-blog.tistory.com/238) \
b-6. [Atomic Class에서 CAS란?](https://javaplant.tistory.com/23#:~:text=AtomicInteger%EB%9E%80%20%EC%9B%90%EC%9E%90%EC%84%B1%EC%9D%84,%ED%95%98%EA%B8%B0%20%EC%9C%84%ED%95%B4%EC%84%9C%20%EA%B3%A0%EC%95%88%EB%90%9C%20%EB%B0%A9%EB%B2%95%EC%9D%B4%EB%8B%A4.) \
b-7. [자바 쓰레드 교착상태](https://math-coding.tistory.com/175) \
b-9. [크리티컬 섹션, 뮤텍스, 세마포어 설명](https://do-rang.tistory.com/90) \
b-10. [세마포어 설명](https://javaplant.tistory.com/30?category=789385) \
b-11. [Monitor 설명](https://velog.io/@hosunghan0821/Java-Monitor) \
b-12. [thread dump 떠서 데드락 확인](https://syundev.tistory.com/284?category=870166)

