---\
Objective

modularize springboot fundamental concepts

---\
Approach

f. web application server :white_check_mark:\
g. tomcat\
h. servlet

b. HTTP Cache(cache-control + etag for static files) :white_check_mark:\
c. Spring Boot Cache\
e. thread :white_check_mark:\
i. servlet\
x. reflection\
d. DI :white_check_mark:

a. overall process\
x. JPA\
x. IOC\
x. AOP\
x. annotations\
x. Filter, Interceptor\
x. Exception\
x. multipart\
x. logging\
x. cors\
x. WebFlux


---\
todo

b-1. 우테코 cache 예제 파악 :white_check_mark:\
b-2. 우테코 cache 예제 solution보고 개별 문제 풀기 :white_check_mark:

d-1. 우테코 di 예제 stage-n setting :white_check_mark:\
d-2. 우테코 di 예제 stage-n 문제 해결 :white_check_mark:\
d-3. context, package bean scanner 구현 안된거 구현 :white_check_mark:

e-1. 우테코 thread 예제 stage-n setting :white_check_mark:\
e-2. 우테코 thread 예제 stage-n 분석 :white_check_mark:

f-1. 깡통 WAS setup & 파악 :white_check_mark:\
f-2. http://localhost:8080/index.html 로 접속했을 때, webapp/index.html 출력 :white_check_mark: \
f-3. register using GET, POST :white_check_mark: \
f-4. login with cookie :white_check_mark: \
f-5. list(사용자 목록 출력) by verifying cookie :white_check_mark: \
f-6. cookie -> session 구현 :white_check_mark: 

g-1. [우테코 - tomcat MVP setup](https://github.dev/woowacourse/jwp-dashboard-http) :white_check_mark:\
g-2. tomcat MVP 분석 && 요구사항 파악 :white_check_mark:\
g-3. tomcat MVP -> MVC + 분석 :white_check_mark:


---\
reference

f-1. 책: 자바 웹 프로그래밍 next step \
f-2. [build MVP WAS from scratch by 깃헙 뜯어보기](https://github.dev/BryceYangS/web-application-server) \
f-3. [register using GET, POST](https://github.dev/BryceYangS/web-application-server) \
f-4. [login with cookie](https://github.dev/BryceYangS/web-application-server) \
f-5. [list](https://github.dev/BryceYangS/web-application-server) \
f-6. [session](https://github.dev/minseokism/NextStep/tree/master/Chapter06) \
x.7. [to mvc format](https://github.dev/woowacourse/jwp-dashboard-http/tree/sihyung92)

g-1. [우테코 - tomcat MVP setup](https://github.dev/woowacourse/jwp-dashboard-http) \
g-2. tomcat MVP 분석 && 요구사항 파악\
g-3. [tomcat MVP -> MVC + 분석](https://github.dev/woowacourse/jwp-dashboard-http/tree/2yujeong)

b. [우테코 - 만들면서 배우는 스프링 실습 코드 http cache](https://github.com/woowacourse/jwp-hands-on)

d. [우테코 - 만들면서 배우는 스프링 실습 코드 di](https://github.com/woowacourse/jwp-hands-on)

e. [우테코 - 만들면서 배우는 스프링 실습 코드 thread](https://github.com/woowacourse/jwp-hands-on)


---
x-1. [우테코 - servlet](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection) 


x-1. [우테코 - reflexion](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection) \
x-2. [reflection 이란?](https://papimon.tistory.com/82) \
x-3. [reflection by meta coding](https://www.youtube.com/watch?v=P5fPc2tjOko&list=PL93mKxaRDidFGJu8IWsAAe0O7y6Yw9f5x&index=1)


 
x-1. [우테코 -mvc 단계별 구현](https://github.com/jeonye/jwp-basic) \
x-1. [우테코 - mvc 구현하기 start](https://github.dev/woowacourse/jwp-dashboard-mvc) \
x-2. [우테코 - mvc 타인이 구현한 것1](https://github.dev/joelonsw/jwp-dashboard-mvc) \
x-2. [우테코 - mvc 타인이 구현한 것2](https://github.dev/NewWisdom/jwp-dashboard-http/tree/step1) 

x-1. [우테코 - jdbc 구현하기](https://github.com/woowacourse/jwp-dashboard-jdbc) \
x-2. [우테코 - jdbc 구현하기 by joelonsw](https://github.com/joelonsw/jwp-dashboard-jdbc)

x-1. [우테코 - jpa1](https://github.dev/jeonye/jwp-basic/tree/step5-qna-with-ajax-practice) \
x-2. [우테코 - jpa2](https://github.dev/joelonsw/jwp-qna) \
x-2. [우테코 - jpa3](https://github.dev/hyewoncc/jwp-qna)

x-1. [우테코 - mvc 기본형](https://github.dev/minseokism/NextStep/tree/master/Chapter06) \
x-1. [우테코 - 프레임워크 구현](https://github.com/woowacourse/jwp-mvc)

x-1. [우테코 - DI 프레임워크 구현](https://github.com/woowacourse/jwp-di)

x-1. [우테코 - 레거시 코드 리펙토링](https://github.com/woowacourse/jwp-refactoring)

x-1. [우테코- 패킷 구현](https://github.dev/woowacourse/jwp-network)
