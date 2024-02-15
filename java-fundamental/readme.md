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
k. how jvm works\
l. defensive programming\
m. effective java\
n. 1 brc\
o. unsafe\
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
c-ㄱ-나-5. stream - 5. parallel stream performance test :white_check_mark:\
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
d-ㄴ-가-4. 복소수 example :white_check_mark:\
d-ㄴ-나. synchronization\
d-ㄴ-나-1. synchronized :white_check_mark:\
d-ㄴ-나-2. volatile :white_check_mark:\
d-ㄴ-나-3. atomic class :white_check_mark:\
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
d-ㅁ-가-1. 인터페이스로 제약건 메서드만 실행 가능한게 디버깅/코드분석시 장점 :white_check_mark:\
d-ㅂ. 상태 데이터의 캡슐화\
d-ㅂ-가. enum\
d-ㅂ-가-1. what - enum은 클래스다 :white_check_mark:\
d-ㅂ-가-2. what - calculate :white_check_mark:\
d-ㅂ-가-3. what - planet :white_check_mark:\
d-ㅂ-가-4. how-1. 같은 의미끼리 묶기 - table status :white_check_mark:\
d-ㅂ-가-5. how-1. 같은 의미끼리 묶기 - limit day type :white_check_mark:\
d-ㅂ-가-6. how-2. 상태와 행위를 한곳에서 관리 - calculate :white_check_mark:\
d-ㅂ-가-7. how-2. 상태와 행위를 한곳에서 관리 - sales amount type :white_check_mark:\
d-ㅂ-가-8. how-3. 데이터 그룹 관리 - affiliate :white_check_mark:\
d-ㅂ-가-9. how-3. 데이터 그룹 관리 - paygroup :white_check_mark:\
d-ㅂ-가-10. how-4. enumMapper :white_check_mark:
d-ㅂ-나. nested class\
d-ㅂ-나-1. 멤버 클래스 - custom list with iterator :white_check_mark:\
d-ㅂ-나-2. 멤버 클래스 - :white_check_mark:\
d-ㅂ-나-3. 정적 멤버 클래스 - builder pattern :white_check_mark:\
d-ㅂ-나-4. 정적 멤버 클래스 - utility class :white_check_mark:\
d-ㅂ-나-5. 익명 클래스 - Arrays.sort()에 들어가는 new Comparator :white_check_mark:\
d-ㅂ-나-6. 익명 클래스 - event listener :white_check_mark:\
d-ㅂ-나-7. 익명 클래스 - calculator :white_check_mark:\
d-ㅂ-나-8. 지역 클래스 - registration form :white_check_mark:




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
i-ㄱ-가. 이해하기 쉬운 코드\
i-ㄱ-가-1. 객체지향 생활체조 원칙 :white_check_mark:\
i-ㄱ-가-2. rule of 6 :white_check_mark:\
i-ㄱ-가-3. 표준 - checkStyle (intellij plugin) :white_check_mark:\
i-ㄴ-나. 변형하기 쉬운 코드\
i-ㄴ-다. 확장성 있는 코드

i-ㄴ. comment

i-ㄷ. variables\
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

l-ㄱ. null-handling\
l-ㄱ-가. Optional :white_check_mark:\
l-ㄱ-나. module - nullify :white_check_mark:\
l-ㄱ-나-1. Integer null test :white_check_mark:\
l-ㄱ-나-2. string null test :white_check_mark:\
l-ㄱ-나-3. List null test :white_check_mark:\
l-ㄱ-다. how\
l-ㄱ-다-1. 모든 메서드의 인자가 object일 때마다 null validation check하지 말라 :white_check_mark:\
l-ㄴ. exception\
l-ㄴ-가. error\
l-ㄴ-가-1. let it crash :white_check_mark:\
l-ㄴ-나. checked Exception\
l-ㄴ-나-1. try~catch :white_check_mark:\
l-ㄴ-나-2. throws로 책임 넘기기 :white_check_mark:\
l-ㄴ-나-3. IOException handling :white_check_mark:\
l-ㄴ-나-4. SQLExceptionHandling :white_check_mark:\
l-ㄴ-나-5. ClassNotFoundException :white_check_mark:\
l-ㄴ-나-6. InterruptedException :white_check_mark:\
l-ㄴ-나-7. exception chaining :white_check_mark:\
l-ㄴ-나-8. wrap low level exception with high level exception :white_check_mark:\
l-ㄴ-다. unchecked Exception\
l-ㄴ-다-1. enum으로 Exception Error code 묶어 관리 :white_check_mark:\
l-ㄴ-라. 예외처리 3가지 방법\
l-ㄴ-라-1. 1. 예외 복구:white_check_mark:\
l-ㄴ-라-2. 2. 예외 전환 :white_check_mark:\
l-ㄴ-라-3. 3. 예외 처리 회피 :white_check_mark:\
l-ㄴ-마. 구현방법론\
l-ㄴ-마-1. 실패원자적으로 만들기 - 불변객체 :white_check_mark:\
l-ㄴ-마-2. 실패원자적으로 만들기 - 가변객체 안에서 validation check :white_check_mark:\
l-ㄴ-마-3. 실패원자적으로 만들기 - 임시 복사본 객체에서 처리 후 실패하면 throw Exception :white_check_mark:\
l-ㄴ-바. 정적분석툴\
l-ㄴ-바-1. 개발자 실수할 수 있는 부분도 exception으로 범벅하지 말고 linter를 쓰자 :white_check_mark:\
l-ㄷ. assert\
l-ㄷ-가-1. assert vs exception :white_check_mark:
l-ㄹ. enum over string\
l-ㄹ-가-1. 동서남북 예제 :white_check_mark:\
l-ㅁ. magic number :white_check_mark:\
l-ㅂ. early exit\
l-ㅂ-가-1. nested if-else -> early exit 예제 :white_check_mark:\
l-ㅂ-가-2. wrong advice: oop style :white_check_mark:

2장 객체 생성과 파괴\
아이템 1. 생성자 대신 정적 팩터리 메서드를 고려하라 :white_check_mark:\
아이템 2. 생성자에 매개변수가 많다면 빌더를 고려하라 :white_check_mark:\
아이템 3. private 생성자나 열거 타입으로 싱글턴임을 보증하라 :white_check_mark:\
아이템 4. 인스턴스화를 막으려거든 private 생성자를 사용하라 :white_check_mark:\
아이템 5. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라 :white_check_mark:\
아이템 6. 불필요한 객체 생성을 피하라 :white_check_mark:\
아이템 7. 다 쓴 객체 참조를 해제하라 :white_check_mark:\
아이템 8. finalizer와 cleaner 사용을 피하라 :white_check_mark:\
아이템 9. try-finally보다는 try-with-resources를 사용하라 :white_check_mark:


3장 모든 객체의 공통 메서드\
아이템 10. equals는 일반 규약을 지켜 재정의하라 :white_check_mark:\
아이템 11. equals를 재정의하려거든 hashCode도 재정의하라 :white_check_mark:\
아이템 12. toString을 항상 재정의하라 :white_check_mark:\
아이템 13. clone 재정의는 주의해서 진행하라 :white_check_mark:\
아이템 14. Comparable을 구현할지 고려하라


4장 클래스와 인터페이스\
아이템 15. 클래스와 멤버의 접근 권한을 최소화하라 :white_check_mark:\
아이템 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라 :white_check_mark:\
아이템 17. 변경 가능성을 최소화하라 :white_check_mark:\
아이템 18. 상속보다는 컴포지션을 사용하라 :white_check_mark:\
아이템 19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라 :white_check_mark:\
아이템 20. 추상 클래스보다는 인터페이스를 우선하라 :white_check_mark:\
아이템 21. 인터페이스는 구현하는 쪽을 생각해 설계하라 :white_check_mark:\
아이템 22. 인터페이스는 타입을 정의하는 용도로만 사용하라 :white_check_mark:\
아이템 23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라 :white_check_mark:\
아이템 24. 멤버 클래스는 되도록 static으로 만들라 :white_check_mark:\
아이템 25. 톱레벨 클래스는 한 파일에 하나만 담으라 :white_check_mark:


5장 제네릭\
아이템 26. 로 타입은 사용하지 말라 :white_check_mark:\
아이템 27. 비검사 경고를 제거하라\
아이템 28. 배열보다는 리스트를 사용하라\
아이템 29. 이왕이면 제네릭 타입으로 만들라\
아이템 30. 이왕이면 제네릭 메서드로 만들라\
아이템 31. 한정적 와일드카드를 사용해 API 유연성을 높이라\
아이템 32. 제네릭과 가변인수를 함께 쓸 때는 신중하라\
아이템 33. 타입 안전 이종 컨테이너를 고려하라


6장 열거 타입과 애너테이션\
아이템 34. int 상수 대신 열거 타입을 사용하라\
아이템 35. ordinal 메서드 대신 인스턴스 필드를 사용하라\
아이템 36. 비트 필드 대신 EnumSet을 사용하라\
아이템 37. ordinal 인덱싱 대신 EnumMap을 사용하라\
아이템 38. 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라\
아이템 39. 명명 패턴보다 애너테이션을 사용하라\
아이템 40. @Override 애너테이션을 일관되게 사용하라\
아이템 41. 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라


7장 람다와 스트림\
아이템 42. 익명 클래스보다는 람다를 사용하라\
아이템 43. 람다보다는 메서드 참조를 사용하라\
아이템 44. 표준 함수형 인터페이스를 사용하라\
아이템 45. 스트림은 주의해서 사용하라\
아이템 46. 스트림에서는 부작용 없는 함수를 사용하라\
아이템 47. 반환 타입으로는 스트림보다 컬렉션이 낫다\
아이템 48. 스트림 병렬화는 주의해서 적용하라


8장 메서드\
아이템 49. 매개변수가 유효한지 검사하라\
아이템 50. 적시에 방어적 복사본을 만들라\
아이템 51. 메서드 시그니처를 신중히 설계하라\
아이템 52. 다중정의는 신중히 사용하라\
아이템 53. 가변인수는 신중히 사용하라\
아이템 54. null이 아닌, 빈 컬렉션이나 배열을 반환하라\
아이템 55. 옵셔널 반환은 신중히 하라\
아이템 56. 공개된 API 요소에는 항상 문서화 주석을 작성하라


9장 일반적인 프로그래밍 원칙\
아이템 57. 지역변수의 범위를 최소화하라\
아이템 58. 전통적인 for 문보다는 for-each 문을 사용하라\
아이템 59. 라이브러리를 익히고 사용하라\
아이템 60. 정확한 답이 필요하다면 float와 double은 피하라\
아이템 61. 박싱된 기본 타입보다는 기본 타입을 사용하라\
아이템 62. 다른 타입이 적절하다면 문자열 사용을 피하라\
아이템 63. 문자열 연결은 느리니 주의하라\
아이템 64. 객체는 인터페이스를 사용해 참조하라\
아이템 65. 리플렉션보다는 인터페이스를 사용하라\
아이템 66. 네이티브 메서드는 신중히 사용하라\
아이템 67. 최적화는 신중히 하라\
아이템 68. 일반적으로 통용되는 명명 규칙을 따르라


10장 예외\
아이템 69. 예외는 진짜 예외 상황에만 사용하라 :white_check_mark:\
아이템 70. 복구할 수 있는 상황에는 검사 예외를, 프로그래밍 오류에는 런타임 예외를 사용하라 :white_check_mark:\
아이템 71. 필요 없는 검사 예외 사용은 피하라 :white_check_mark:\
아이템 72. 표준 예외를 사용하라 :white_check_mark:\
아이템 73. 추상화 수준에 맞는 예외를 던지라 :white_check_mark:\
아이템 74. 메서드가 던지는 모든 예외를 문서화하라 :white_check_mark:\
아이템 75. 예외의 상세 메시지에 실패 관련 정보를 담으라 :white_check_mark:\
아이템 76. 가능한 한 실패 원자적으로 만들라 :white_check_mark:\
아이템 77. 예외를 무시하지 말라 :white_check_mark:


11장 동시성\
아이템 78. 공유 중인 가변 데이터는 동기화해 사용하라\
아이템 79. 과도한 동기화는 피하라\
아이템 80. 스레드보다는 실행자, 태스크, 스트림을 애용하라\
아이템 81. wait와 notify보다는 동시성 유틸리티를 애용하라\
아이템 82. 스레드 안전성 수준을 문서화하라\
아이템 83. 지연 초기화는 신중히 사용하라\
아이템 84. 프로그램의 동작을 스레드 스케줄러에 기대지 말라


12장 직렬화\
아이템 85. 자바 직렬화의 대안을 찾으라\
아이템 86. Serializable을 구현할지는 신중히 결정하라\
아이템 87. 커스텀 직렬화 형태를 고려해보라\
아이템 88. readObject 메서드는 방어적으로 작성하라\
아이템 89. 인스턴스 수를 통제해야 한다면 readResolve보다는 열거 타입을 사용하라\
아이템 90. 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라


n-0. create 1 billion rows text file :white_check_mark:\
n-1. baseline code :white_check_mark:\
n-2. parallel :white_check_mark:\
n-3. parallel in 10MB chunk :white_check_mark:\
n-4. parallel in 1MB chunk :white_check_mark:\
n-5. SWAR :white_check_mark:

o-1. unsafe로 객체생성 없이 메모리 접근하기 :white_check_mark:

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



l-ㄱ. null-handling\
l-ㄱ-나. [module - nullify](https://github.com/silentsoft/nullify)
