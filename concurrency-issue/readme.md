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
b-15. producer/consumer queue :white_check_mark:



---\
Reference


a. [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/lecture?courseSlug=%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C&unitId=125484&tab=curriculum)
