---\
Objective

modularize concepts in java



---\
Concepts


a. IO :white_check_mark:\
b. java socket programming :white_check_mark:\
c. jdk verion별 문법 :white_check_mark:\
d. oop :white_check_mark:\
e. generics :white_check_mark:\
f. access modifier :white_check_mark:\
g. SOLID :white_check_mark:\
h. mvc :white_check_mark:\
i. clean code\
j. functional programming :question:\
k. how jvm works :question:\
x. secure code\
x. exception\
x. java time\
x. enums\
x. annotation\
x. garbage collector\
x. debugging




---\
Todo


a-1. io overall structure :white_check_mark:\
a-2. File Input/Output Stream, read by single byte :white_check_mark:\
a-3. File Input/Output Stream, read by buffer :white_check_mark:\
a-4. DataOutputStream, decorator :white_check_mark:\
a-5. BufferedReader :white_check_mark:\
a-6. io between threads :white_check_mark:\
a-7. serialization :white_check_mark:


b-1. what is web socket? :white_check_mark:\
b-2. java socket programming code :white_check_mark:\
b-3. chatting program :white_check_mark:

c-ㄱ. jdk8\
c-ㄱ-가-1. default method(interface) :white_check_mark:\
c-ㄱ-가-2. diamond problem for interface :white_check_mark:\
c-ㄱ-나-1. stream - 1. 스트림 만들기 :white_check_mark:\
c-ㄱ-나-2. stream - 2. 가공하기 :white_check_mark:\
c-ㄱ-나-3. stream - 3. 결과 만들기 :white_check_mark:\
c-ㄱ-나-4. stream - 4. 고급(동작 순서, 성능향상, 재사용, 지연처리, null-safe 스트림 생성, shortcuts) :white_check_mark:\
c-ㄱ-나-5. stream - 5. parallel stream performance test :white_check_mark:
c-ㄱ-다-1. lambda - why lambda is created :white_check_mark:\
c-ㄱ-다-2. lambda - 참조 지역변수는 final로 선언하라 :white_check_mark:\
c-ㄱ-다-3. lambda - functional interface :white_check_mark:\
c-ㄱ-다-4. lambda - :: :white_check_mark:\
c-ㄱ-다-5. lambda - ::'s example - factory method :white_check_mark:\
c-ㄱ-다-6. lambda - usecase - lazy evaluation  :white_check_mark:\
c-ㄱ-다-7. lambda - usecase - execute around pattern :white_check_mark:\
c-ㄱ-다-8. lambda - usecase - builder pattern :white_check_mark:\
c-ㄱ-다-9. lambda - usecase - decorator pattern :white_check_mark:\
c-ㄱ-라-10. future - what :white_check_mark:\
c-ㄱ-마-1. optional - why :white_check_mark:\
c-ㄱ-마-2. optional - what :white_check_mark:\
c-ㄱ-마-3. optional - build from scratch :white_check_mark:\
c-ㄱ-마-4. optional - best practices :white_check_mark:\
c-ㄱ-마-5. optional - how to refactor into optional :white_check_mark:

c-ㄴ. jdk11


d-ㄱ. pop\
d-ㄱ-가. calculator from pop to oop :white_check_mark:\
d-ㄱ-나. pop -> oop 도형 예제 :white_check_mark:\
d-ㄴ. state\
d-ㄴ-가. immutable object\
d-ㄴ-가-1. why :white_check_mark:\
d-ㄴ-가-2. 일급 컬렉션 :white_check_mark:\
d-ㄴ-가-3. ImmutableReminder example :white_check_mark:\
d-ㄴ-나. synchronization\
d-ㄴ-나-1. synchronized :white_check_mark:\
d-ㄴ-나-2. volatile :white_check_mark:\
d-ㄴ-나-3. atomic class :white_check_mark:\
d-ㄴ-다. enum\
d-ㄴ-다-1. what - enum은 클래스다 :white_check_mark:\
d-ㄴ-다-2. what - calculate :white_check_mark:\
d-ㄴ-다-3. what - planet :white_check_mark:\
d-ㄴ-다-4. how-1. 같은 의미끼리 묶기 - table status :white_check_mark:\
d-ㄴ-다-5. how-1. 같은 의미끼리 묶기 - limit day type :white_check_mark:\
d-ㄴ-다-6. how-2. 상태와 행위를 한곳에서 관리 - calculate :white_check_mark:\
d-ㄴ-다-7. how-2. 상태와 행위를 한곳에서 관리 - sales amount type :white_check_mark:\
d-ㄴ-다-8. how-3. 데이터 그룹 관리 - affiliate :white_check_mark:\
d-ㄴ-다-9. how-3. 데이터 그룹 관리 - paygroup :white_check_mark:\
d-ㄴ-다-10. how-4. enumMapper :white_check_mark:

d-ㄷ. 동적 late binding\
d-ㄷ-가. composition\
d-ㄷ-가-1. computer example :white_check_mark:\
d-ㄷ-나. di\
d-ㄷ-나-1. email service example :white_check_mark:\
d-ㄷ.다. ioc
d-ㄹ. message\
d-ㄹ-가. dont ask just tell\
d-ㄹ-가-1. distance example :white_check_mark:\
d-ㄹ-가-2. bank example :white_check_mark:\
d-ㅁ. polymorphism\
d-ㅁ-가-1. 인터페이스로 제약건 메서드만 실행 가능한게 디버깅/코드분석시 장점 :white_check_mark:


e-1. what is generics? :white_check_mark:\
e-2. generic method :white_check_mark:\
e-3. T extends X && T super X :white_check_mark:\
e-4. wild card :white_check_mark:\
e-5. example) builder pattern in abstract class :white_check_mark:\
e-6. generic method advanced :white_check_mark:\
e-7. example) 제대로 이해했는지 확인해보기 :white_check_mark:


f-ㄱ-1. public - anti pattern :white_check_mark:\
f-ㄴ-1. private - what :white_check_mark:\
f-ㄴ-2. private - ex1. singleton :white_check_mark:\
f-ㄴ-3. private - ex2. util class :white_check_mark:\
f-ㄷ-1. private package(default) - what :white_check_mark:\
f-ㄹ-1. protected - what :white_check_mark:\
f-ㄹ-2. protected - ex1. Bird :white_check_mark:\
f-ㄹ-3. protected - ex2. factory method :white_check_mark:

g-1. baeldung SOLID :white_check_mark:


h-1. build mvc from scratch :white_check_mark:\
h-2. build event listener from scratch using observer pattern :white_check_mark:\
h-3. build calculator in mvc + observer pattern :white_check_mark:\
h-4. build search in mvc pattern using event listener :white_check_mark:


i-ㄱ. principle\
i-ㄱ-가-1. 객체지향 생활체조 원칙 :white_check_mark:\
i-ㄱ-나-1. rule of 6 :white_check_mark:\

i-ㄴ. comment

i-ㄷ. variables
i-ㄷ-1. 원시값 & 문자열 포장한 클래스 :white_check_mark:\
i-ㄷ-2. 축약어 풀어써라 :white_check_mark:

i-ㄹ. function\
i-ㄹ-가. if\
i-ㄹ-가-1. if - 왜 if-else문을 비추? :white_check_mark:\
i-ㄹ-가-2. if - if-else -> switch :white_check_mark:\
i-ㄹ-가-3. if - if else -> 역조건 :white_check_mark:\
i-ㄹ-나. getter\
i-ㄹ-나-1. don't ask, just tell :white_check_mark:\
i-ㄹ-다. setter\
i-ㄹ-다-1. don't ask, just tell :white_check_mark:\
i-ㄹ-다-2. 의도치 않은 필드(ex. id)의 setter() 방어 :white_check_mark:\
i-ㄹ-다-3. custom setter()는 validation check 로직 추가 가능 :white_check_mark:


i-ㅁ. class\
i-ㅁ-가. immutable class\
i-ㅁ-가-1. why :white_check_mark:\
i-ㅁ-가-2. 일급 컬렉션 :white_check_mark:

i-ㅂ. query


j-ㄱ-1. 불변객체 :white_check_mark:\
j-ㄴ-1. 순수함수 :white_check_mark:\
j-ㄷ-1. 고차함수 :white_check_mark:\
j-ㄹ-1. embedded iteratable :white_check_mark:\
j-ㅁ-1. lambda :white_check_mark:\
j-ㅂ-1. stream :white_check_mark:\
j-ㅅ-1. closure :white_check_mark:\
j-ㅇ-1. optional :white_check_mark:\
j-ㅈ-1. lazy evaluation :white_check_mark:\
j-ㅊ-1. compose :white_check_mark:\
j-ㅋ-1. recursion :white_check_mark:\
j-ㅌ-1. currying :white_check_mark:\
j-ㅍ-1. functor :white_check_mark:\
j-x. monad


k-ㄱ. call by value vs call by reference :white_check_mark:\
k-ㄴ. javacode in method area, heap, stack :white_check_mark:\
k-ㄷ. reference\
k-ㄷ-가-1. strong reference :white_check_mark:\
k-ㄷ-나-1. weak reference :white_check_mark:\
k-ㄷ-나-2. weak hashmap :white_check_mark:\
k-ㄷ-다-1. soft reference :white_check_mark:\
k-ㄷ-라-1. phantom reference :white_check_mark:



---\
Reference


a-1. [io overall structure](https://www.youtube.com/watch?v=FqqzbRPSAks&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=15) \
a-2. [File Input/Output Stream](https://www.youtube.com/watch?v=fpOGpBywvR4&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=16) \
a-3. [File Input/Output Stream with buffer size](https://www.youtube.com/watch?v=4DtJ1QcZZkI&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=17) \
a-4. [DataOutputStream, decorator](https://www.youtube.com/watch?v=ewZhpmriRN8&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=19) \
a-5. [BufferedReader](https://www.youtube.com/watch?v=vaOjTx5pPhY&list=PLz4XWo74AOafFAkhYJK3SDBIrXjsaIu66&index=20) \
a-6. io between threads - 자바의 정석 \
a-7. serialization - 자바의 정석


b-1. [what is web socket?](https://www.youtube.com/watch?v=yXPCg5eupGM) \
b-2. [자바 소켓 통신(Socket)을 사용하는 이유와 동작 원리 및 코드](https://wildeveloperetrain.tistory.com/122) \
b-3. [TCP socket programming - chatting](https://lktprogrammer.tistory.com/64?category=672211)

c-a. [stream 기초, 고급](https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/) \
c-a. [java Optional 바르게 쓰기 - 기계인간](https://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/)

d-가. [calculator from pop to oop](https://github.com/serverwizard/oop-practice) \
d-ㄴ-다. [enum 활용사례 3가지](https://github.com/jojoldu/blog-code/tree/master/enum-uses) \
d-ㄴ-다. [enumMapper 활용](https://github.com/jojoldu/blog-code/blob/master/java/enum-mapper/README.md)


e-1..4. [제네릭 기본, generic method, T extends X && T super X, wild card](https://www.youtube.com/watch?v=Vv0PGUxOzq0) \
e-5. example) builder pattern in abstract class - effective java\
e-6. [generic method advanced](https://devlog-wjdrbs96.tistory.com/201)

g-1. [A Solid Guide to SOLID Principles](https://www.baeldung.com/solid-principles)


h-1,2. build mvc from scratch - udemy "java design pattern and architecture" by John Purcell\
h-3. [calculator using observer pattern](https://dev4-me.tistory.com/entry/MVC-%ED%8C%A8%ED%84%B4%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Java-SWING%EC%9C%BC%EB%A1%9C-%EA%B3%84%EC%82%B0%EA%B8%B0-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EB%A7%8C%EB%93%A4%EA%B8%B0) \
h-4. [build search in mvc + observer pattern](https://link-intersystems.com/blog/2013/07/20/the-mvc-pattern-implemented-with-java-swing/)



i-1. [이유와 솔루션으로 정리하는 객체지향 생활체조 원칙](https://hudi.blog/thoughtworks-anthology-object-calisthenics/) \
i-2. [rule of 6](https://davidamos.dev/the-rule-of-six/) \
i-3. [is the term "clean code" subjective?](https://www.youtube.com/watch?v=ou6x2qcLOLI) \
i-x. Code Complete 2 \
i-x. Refactoring 2 \
i-x. Working Effectively with Legacy Code

j-1..4. [자바 코드로 보는 함수형 프로그래밍](https://warpgate3.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%BD%94%EB%93%9C%EB%A1%9C-%EB%B3%B4%EB%8A%94-%ED%95%A8%EC%88%98%ED%98%95-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-Functional-Programming-in-Java) \
j-6. [자바 스트림 예제](https://madplay.github.io/post/java-streams-examples) \
j-9. [lazy evaluation](https://sthwin.tistory.com/21#:~:text=%EC%9E%90%EB%B0%94%EC%97%90%EC%84%9C%20%EC%9D%BC%EA%B8%89%ED%95%A8%EC%88%98%EC%97%90%20%EA%B0%80%EC%9E%A5%20%EA%B0%80%EA%B9%8C%EC%9A%B4%20%EA%B2%83%EC%9D%B4%20%EB%9E%8C%EB%8B%A4%ED%91%9C%ED%98%84%20%28Lambda,expressions%29%EC%9D%B4%EB%8B%A4.Function%2CConsumer%2CPredicate%2CSupplier%EC%99%80%20%EA%B0%99%EC%9D%80%20%ED%95%A8%EC%88%98%ED%98%95%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EB%93%A4%EC%9D%B4%20%EC%A4%80%EB%B9%84%EB%90%98%EC%96%B4%20%EC%9E%88%EC%9C%BC%EB%A9%B0java.util.function%ED%8C%A8%ED%82%A4%EC%A7%80%20%EB%82%B4%EC%97%90%20%EC%A1%B4%EC%9E%AC%ED%95%9C%EB%8B%A4.) \
j-14..17. [functional interface most frequently used](https://yhmane.tistory.com/203) \
j-x. [monad](https://www.youtube.com/channel/UCrlZnbV0o2cnUNWdEORTxsw/videos) \
j-x. [3분 모나드](https://overcurried.com/3%EB%B6%84%20%EB%AA%A8%EB%82%98%EB%93%9C/)



k-1. [call by value, call by reference](https://gyoogle.dev/blog/computer-language/Java/Call%20by%20value%20&%20Call%20by%20reference.html) \
k-2. [method, stack, heap area](https://www.youtube.com/watch?v=Vd1C3-wHc4Y&ab_channel=%EC%BD%94%EB%93%9C%EB%9D%BC%EB%96%BC)



