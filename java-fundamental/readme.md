---\
Objective

modularize concepts in java



---\
Concepts 


a. exception\
b. Collection\
c. java util class\
d. java time\
e. thread :white_check_mark:\
f. IO :white_check_mark:\
g. java socket programming :white_check_mark:\
h. generics :white_check_mark:\
i. functional programming :question:\
j. SOLID :white_check_mark:\
k. Clean Code :soon:\
x. enums\
x. annotation\
x. garbage collector\
x. debugging\
x. how jvm works



---\
Todo



e-0. thread summary in my word :white_check_mark:\
e-1. thread basic :white_check_mark:\
e-1. thread basic: wait() && notify() :white_check_mark:\
e-2. thread pool :white_check_mark:\
e-3. concurrency :white_check_mark:\
e-3. race condition :white_check_mark:\
e-4. synchronization :white_check_mark:\
e-4. synchronized block for optimization :white_check_mark:\
e-5. volatile :white_check_mark:\
e-6. atomic integer :white_check_mark:\
e-7. deadlock :white_check_mark:\
e-8. deadlock solution - hold and wait :white_check_mark:\
e-9. critical section - mutex :white_check_mark:\
e-10. critical section - semaphore :white_check_mark:\
e-11. critical section - monitor :white_check_mark:\
e-12. daemon thread :white_check_mark:\
e-13. thread dump 떠서 데드락 확인  



f-1. io overall structure :white_check_mark:\
f-2. File Input/Output Stream, read by single byte :white_check_mark:\
f-3. File Input/Output Stream, read by buffer :white_check_mark:\
f-4. DataOutputStream, decorator :white_check_mark:\
f-5. BufferedReader :white_check_mark:\
f-6. io between threads :white_check_mark:\
f-7. serialization :white_check_mark:



g-1. what is web socket? :white_check_mark:\
g-2. java socket programming code :white_check_mark:\
g-3. chatting program :white_check_mark:


h-1. what is generics? :white_check_mark:\
h-2. generic method :white_check_mark:\
h-3. T extends X && T super X :white_check_mark:\
h-4. wild card :white_check_mark:\
h-5. example) builder pattern in abstract class :white_check_mark:\
h-6. generic method advanced :white_check_mark:

i-1. 불변객체 :white_check_mark:\
i-2. 순수함수 :white_check_mark:\
i-3. 고차함수 :white_check_mark:\
i-4. embedded iteratable :white_check_mark:\
i-5. lambda :white_check_mark:\
i-6. stream :white_check_mark:\
i-7. closure :white_check_mark:\
i-8. optional :white_check_mark:\
i-9. lazy evaluation :white_check_mark:\
i-10. compose :white_check_mark:\
i-11. recursion :white_check_mark:\
i-12. currying :white_check_mark:\
i-13. functor :white_check_mark:\
i-14. functional interface - Predicate :white_check_mark:\
i-15. functional interface - Consumer :white_check_mark:\
i-16. functional interface - Supplier :white_check_mark:\
i-17. functional interface - Function :white_check_mark:\
i-x. monad

j-1. baeldung SOLID :white_check_mark:\

k-1. 이유와 솔루션으로 정리하는 객체지향 생활체조 원칙 :white_check_mark:\
k-2. rule of 6

---\
Reference

e-1. [thread basic](https://github.com/woowacourse/jwp-hands-on) \
e-2. [thread pool](https://github.com/woowacourse/jwp-hands-on) \
e-3. [race condition](https://hudi.blog/race-condition-critical-section-mutual-exclusion/) \
e-4. [synchronization](https://github.com/woowacourse/jwp-hands-on) \
e-5. [Volatile 이란?](https://ttl-blog.tistory.com/238) \
e-6. [Atomic Class에서 CAS란?](https://javaplant.tistory.com/23#:~:text=AtomicInteger%EB%9E%80%20%EC%9B%90%EC%9E%90%EC%84%B1%EC%9D%84,%ED%95%98%EA%B8%B0%20%EC%9C%84%ED%95%B4%EC%84%9C%20%EA%B3%A0%EC%95%88%EB%90%9C%20%EB%B0%A9%EB%B2%95%EC%9D%B4%EB%8B%A4.) \
e-7. [자바 쓰레드 교착상태](https://math-coding.tistory.com/175) \
e-9. [크리티컬 섹션, 뮤텍스, 세마포어 설명](https://do-rang.tistory.com/90) \
e-10. [세마포어 설명](https://javaplant.tistory.com/30?category=789385) \
e-11. [Monitor 설명](https://velog.io/@hosunghan0821/Java-Monitor) \
e-12. [thread dump 떠서 데드락 확인](https://syundev.tistory.com/284?category=870166) 



f-1. [io overall structure](https://www.youtube.com/watch?v=FqqzbRPSAks&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=15) \
f-2. [File Input/Output Stream](https://www.youtube.com/watch?v=fpOGpBywvR4&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=16) \
f-3. [File Input/Output Stream with buffer size](https://www.youtube.com/watch?v=4DtJ1QcZZkI&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=17) \
f-4. [DataOutputStream, decorator](https://www.youtube.com/watch?v=ewZhpmriRN8&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=19) \
f-5. [BufferedReader](https://www.youtube.com/watch?v=vaOjTx5pPhY&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=20) \
f-6. io between threads - 자바의 정석 \
f-7. serialization - 자바의 정석 



g-1. [what is web socket?](https://www.youtube.com/watch?v=yXPCg5eupGM) \
g-2. [자바 소켓 통신(Socket)을 사용하는 이유와 동작 원리 및 코드](https://wildeveloperetrain.tistory.com/122) \
g-3. [TCP socket programming - chatting](https://lktprogrammer.tistory.com/64?category=672211)



h-1~4. [제네릭 기본, generic method, T extends X && T super X, wild card](https://www.youtube.com/watch?v=Vv0PGUxOzq0) \
h-5. example) builder pattern in abstract class - effective java\
h-6. [generic method advanced](https://devlog-wjdrbs96.tistory.com/201)


i-1~4. [자바 코드로 보는 함수형 프로그래밍](https://warpgate3.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%BD%94%EB%93%9C%EB%A1%9C-%EB%B3%B4%EB%8A%94-%ED%95%A8%EC%88%98%ED%98%95-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-Functional-Programming-in-Java) \
i-6. [자바 스트림 예제](https://madplay.github.io/post/java-streams-examples) \
i-9. [lazy evaluation](https://sthwin.tistory.com/21#:~:text=%EC%9E%90%EB%B0%94%EC%97%90%EC%84%9C%20%EC%9D%BC%EA%B8%89%ED%95%A8%EC%88%98%EC%97%90%20%EA%B0%80%EC%9E%A5%20%EA%B0%80%EA%B9%8C%EC%9A%B4%20%EA%B2%83%EC%9D%B4%20%EB%9E%8C%EB%8B%A4%ED%91%9C%ED%98%84%20%28Lambda,expressions%29%EC%9D%B4%EB%8B%A4.Function%2CConsumer%2CPredicate%2CSupplier%EC%99%80%20%EA%B0%99%EC%9D%80%20%ED%95%A8%EC%88%98%ED%98%95%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EB%93%A4%EC%9D%B4%20%EC%A4%80%EB%B9%84%EB%90%98%EC%96%B4%20%EC%9E%88%EC%9C%BC%EB%A9%B0java.util.function%ED%8C%A8%ED%82%A4%EC%A7%80%20%EB%82%B4%EC%97%90%20%EC%A1%B4%EC%9E%AC%ED%95%9C%EB%8B%A4.) \
i-14~17. [functional interface most frequently used](https://yhmane.tistory.com/203) \
i-x. [monad](https://www.youtube.com/channel/UCrlZnbV0o2cnUNWdEORTxsw/videos) \
i-x. [3분 모나드](https://overcurried.com/3%EB%B6%84%20%EB%AA%A8%EB%82%98%EB%93%9C/)
 
j-1. [A Solid Guide to SOLID Principles](https://www.baeldung.com/solid-principles) \

k-1. [이유와 솔루션으로 정리하는 객체지향 생활체조 원칙](https://hudi.blog/thoughtworks-anthology-object-calisthenics/) \
k-2. [rule of 6](https://davidamos.dev/the-rule-of-six/) \
k-x. Code Complete 2 \
k-x. Refactoring 2 \
k-x. Working Effectively with Legacy Code
