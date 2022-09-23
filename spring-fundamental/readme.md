---\
Objective

modularize springboot fundamental concepts

---\
Approach

a. overall process\
b. HTTP Cache(cache-control + etag for static files) :white_check_mark:\
c. Spring Boot Cache\
d. DI :white_check_mark:\
e. thread :white_check_mark:\
f. web application server :white_check_mark:\
g. tomcat :white_check_mark:\
h. servlet :white_check_mark:\
i. reflection(-> annotation && di) :white_check_mark:\
x. IOC\
x. AOP\
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
g-3. tomcat MVP -> MVC + 분석 :white_check_mark:\
g-4. http://localhost:8080/index.html 페이지에 접근 가능하게 하기 :white_check_mark:\
g-5. test: resource_디렉터리에_있는_파일의_경로를_상대경로로_찾는다 :white_check_mark: \
g-6. test: resource/static/nextstep.txt 파일을 File을 통해 읽기 :white_check_mark: \
g-7. 회원가입 :white_check_mark:\
g-8. 여러 궁금증 해소 :white_check_mark:\
x-9. 로그인 with session & cookie 

h-1. 서브릿 예제 분석 :white_check_mark:

i-1. 우테코 reflection 예제 :white_check_mark:\
i-2. simple di framework using reflection :white_check_mark:\
i-3. 우테코 reflection 예제 해결 :white_check_mark:

---\
reference

b. [우테코 - 만들면서 배우는 스프링 실습 코드 http cache](https://github.com/woowacourse/jwp-hands-on)

d. [우테코 - 만들면서 배우는 스프링 실습 코드 di](https://github.com/woowacourse/jwp-hands-on)

e. [우테코 - 만들면서 배우는 스프링 실습 코드 thread](https://github.com/woowacourse/jwp-hands-on)

f-1. 책: 자바 웹 프로그래밍 next step \
f-2. [build MVP WAS from scratch by 깃헙 뜯어보기](https://github.dev/BryceYangS/web-application-server) \
f-3. [register using GET, POST](https://github.dev/BryceYangS/web-application-server) \
f-4. [login with cookie](https://github.dev/BryceYangS/web-application-server) \
f-5. [list](https://github.dev/BryceYangS/web-application-server) \
f-6. [session](https://github.dev/minseokism/NextStep/tree/master/Chapter06) \
x.7. [to mvc format](https://github.dev/woowacourse/jwp-dashboard-http/tree/sihyung92)

g-1. [우테코 - tomcat MVP setup](https://github.dev/woowacourse/jwp-dashboard-http) \
g-2. tomcat MVP 분석 && 요구사항 파악\
g-3. [tomcat MVP -> MVC + 분석](https://github.dev/woowacourse/jwp-dashboard-http/tree/2yujeong) \
g-4. [http://localhost:8080/index.html 페이지에 접근 가능하게 하기](https://github.dev/woowacourse/jwp-dashboard-http/tree/gyuchool) \
g-5. [test: resource_디렉터리에_있는_파일의_경로를_'상대경로'로 찾는다](https://github.dev/woowacourse/jwp-dashboard-http/tree/gyuchool) \
g-6. [test: resource/static/nextstep.txt 파일을 File을 통해 읽기](https://github.dev/woowacourse/jwp-dashboard-http/tree/gyuchool) \
g-7. [register](https://github.dev/woowacourse/jwp-dashboard-http/tree/2yujeong) \
g-8. [login with session & cookie](https://github.dev/woowacourse/jwp-dashboard-http/tree/2yujeong)

h-1. [우테코 - servlet solution](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection)

i-1. [우테코 - reflection 예제](https://github.dev/woowacourse/jwp-hands-on) \
i-2. [10분 테코톡 - 파랑, 아키의 리플렉션 -> mvp di framework 분석](https://www.youtube.com/watch?v=67YdHbPZJn4&t=469s) \
i-3. [우테코 - reflexion 예제 문제 해결](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection) \
x-4. [reflection by meta coding](https://www.youtube.com/watch?v=P5fPc2tjOko&list=PL93mKxaRDidFGJu8IWsAAe0O7y6Yw9f5x&index=1)



---


x-1. [우테코 - mvc 기본형](https://github.dev/minseokism/NextStep/tree/master/Chapter06) \
x-1. [우테코 - 프레임워크 구현](https://github.com/woowacourse/jwp-mvc) \
x-1. [우테코 -mvc 단계별 구현](https://github.com/jeonye/jwp-basic) \
x-1. [우테코 - mvc 구현하기 start](https://github.dev/woowacourse/jwp-dashboard-mvc) \
x-2. [우테코 - mvc 타인이 구현한 것1](https://github.dev/joelonsw/jwp-dashboard-mvc) \
x-2. [우테코 - mvc 타인이 구현한 것2](https://github.dev/NewWisdom/jwp-dashboard-http/tree/step1) 

x-1. [우테코 - jdbc 구현하기](https://github.com/woowacourse/jwp-dashboard-jdbc) \
x-2. [우테코 - jdbc 구현하기 by joelonsw](https://github.com/joelonsw/jwp-dashboard-jdbc)

x-1. [우테코 - jpa1](https://github.dev/jeonye/jwp-basic/tree/step5-qna-with-ajax-practice) \
x-2. [우테코 - jpa2](https://github.dev/joelonsw/jwp-qna) \
x-2. [우테코 - jpa3](https://github.dev/hyewoncc/jwp-qna)

x-1. [우테코 - DI 프레임워크 구현](https://github.com/woowacourse/jwp-di)

x-1. [우테코 - 레거시 코드 리펙토링](https://github.com/woowacourse/jwp-refactoring)

x-1. [우테코- 패킷 구현](https://github.dev/woowacourse/jwp-network)
