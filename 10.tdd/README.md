---\
Goal


1. learn rules of testing in java
2. modularize best practices of testing




---\
fields


a. tdd\
b. pbt\
y. steal\
z. module



---\
todos

b-ㄱ. hello world\
b-ㄱ-1. reverse() :white_check_mark:\
b-ㄱ-2. fizzbuzz() :white_check_mark:\
b-ㄴ. syntax\
b-ㄴ-가. parameter\
b-ㄴ-가-1. wildcard :white_check_mark:\
b-ㄴ-가-2. many parameters :white_check_mark:\
b-ㄴ-가-3. custom method as parameter :white_check_mark:\
b-ㄴ-나. knowhow\
b-ㄴ-나-1. multiple conditions :white_check_mark:\
b-ㄴ-나-2. null check :white_check_mark:\
b-ㄴ-나-3. metamorphic test :white_check_mark:\
b-ㄹ. optimization\
b-ㄹ-가. map & filter :white_check_mark:\
b-ㅁ. debug\
b-ㅁ-가. random seeds :white_check_mark:\
b-ㅁ-나. logging inputs :white_check_mark:\
b-ㅂ. shrink :white_check_mark:
b-ㅅ. how to find property :white_check_mark:\
b-ㅅ-가. test complex business logic :white_check_mark:\
b-ㅅ-가-1. ex - discount 정책 테스트 :white_check_mark:\
b-ㅅ-나. inverse pattern\
b-ㅅ-나-1. reverse(List) :white_check_mark:\
b-ㅅ-나-2. 주의! encode -> decode는 원본 변환 알될수도? :white_check_mark:\
b-ㅅ-다. induction\
b-ㅅ-다-1. ex - sort list of integers :white_check_mark:\
b-ㅅ-라. stateful testing\
b-ㅅ-라-1. ex - stack testing :white_check_mark:\
b-ㅅ-마. 서순 바뀌어도 무관해야 함\
b-ㅅ-마-1. ex - sort -> filter == filter -> sort :white_check_mark:\
b-ㅅ-사. test oracle\
b-ㅅ-사-1. ex - java magazine :white_check_mark:



y-ㄱ. steal: 상품-주문 api 개발로 알아보는 TDD :white_check_mark:\
y-ㄱ-1. hexagonal architecture :white_check_mark:\
y-ㄱ-2. RestAssured test시 persistent context에 cache 지우기 + Entity의 id 초기화 코드 :white_check_mark:\
y-ㄱ-3. RestAssured 테스트를 static Class로 만들어 우려먹기 :white_check_mark:\
y-ㄱ-4. record? in jdk17? :white_check_mark:\
y-ㄱ-5. product -> order -> payment 전체 로직 flow :white_check_mark:

y-ㄴ. steal: mini-atdd-practice :white_check_mark:\
y-ㄴ-0. init :white_check_mark:\
y-ㄴ-1. atdd로 작은 요구사항 구현 - case1) domain -> service :white_check_mark:\
y-ㄴ-2. atdd로 작은 요구사항 구현 - case2) domain <- service :white_check_mark:\
y-ㄴ-3. refactoring :white_check_mark:\
y-ㄴ-4. integration test on controller using RestAssured :white_check_mark:\
y-ㄴ-5. refactoring - controller, service layer :white_check_mark:

y-ㄷ. steal: jwp-lotto\
y-ㄷ-가. pbt 적용 :white_check_mark:\
y-ㄷ-나. enum 적용 :white_check_mark:\
y-ㄷ-나-1. TODO - ?개를 어떻겍 엘레강스하게 표현하지? 를 enum으로 해결 :white_check_mark:\
y-ㄷ-다. stream 적용 :white_check_mark:\
y-ㄷ-라. clean code\
y-ㄷ-라-1. remove magic number :white_check_mark:\
y-ㄷ-라-2. sonarLint 적용 :white_check_mark:\
y-ㄷ-라-3. google code style 적용 :white_check_mark:\
y-ㄷ-라-4. checkStyle 적용 :white_check_mark:\
y-ㄷ-마. exception-handling :white_check_mark:\
y-ㄷ-바. test coverage 적용 :white_check_mark:

z-1. reset database by platform


---\
reference

b. [jqwik 창시자 blog](https://blog.johanneslink.net/2018/03/24/property-based-testing-in-java-introduction/)

y-ㄱ. [실전! 스프링부트 상품 주문 API 개발로 알아보는 TDD](https://github.com/ejoongseok/product-order-service)

y-ㄴ. [atdd-example](https://github.com/msbaek/atdd-example)

y-ㄷ. [jwp-lotto](https://github.com/byunghakjang1230/study-oop-with-lotto)

z-1. [테코블 - 인수테스트에서 테스트 격리하기](https://tecoble.techcourse.co.kr/post/2020-09-15-test-isolation/)
