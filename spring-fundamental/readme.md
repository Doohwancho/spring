---\
Objective

modularize springboot fundamental concepts


---\
Concepts


a. mvc framework from scratch :white_check_mark:\
b. HTTP Cache(cache-control + etag for static files) :white_check_mark:\
c. Spring Boot Cache :white_check_mark:\
d. DI :white_check_mark:\
e. thread :white_check_mark:\
f. web application server :white_check_mark:\
g. tomcat :white_check_mark:\
h. servlet :white_check_mark:\
i. reflection(annotation -> {di, applicationContext, dispatcher, RequestMapping, componentScan} + DTO) :white_check_mark:\
j. AOP :white_check_mark:\
k. Filter :white_check_mark:\
l. spring-annotation :white_check_mark:\
m. Interceptor :white_check_mark:\
n. lifecycle :white_check_mark:\
o. exception-handling :white_check_mark:\
p. IOC :white_check_mark:\
q. springboot-actuator :white_check_mark:\
x. multipart\
x. logging framework\
x. scheduling\



---\
Todo


a-1. build mvc framework from scratch :white_check_mark:


b-1. 우테코 cache 예제 파악 :white_check_mark:\
b-2. 우테코 cache 예제 solution보고 개별 문제 풀기 :white_check_mark:


c-1. spring 내장 cache :white_check_mark:


d-1. 우테코 di 예제 stage-n setting :white_check_mark:\
d-2. 우테코 di 예제 stage-n 문제 해결 :white_check_mark:\
d-3. context, package bean scanner 구현 안된거 구현 :white_check_mark:\
d-4. field injection :white_check_mark:\
d-5. setter injection :white_check_mark:\
d-6. consturctor injection :white_check_mark:

e-1. why use multi-thread? :white_check_mark:\
e-2. why multi-thread could be problematic? :white_check_mark:\
e-3. tomcat의 thread pool config 을 변경하며 실험해보기 :white_check_mark:\
e-4. thread pool의 동작방식 이해하기 :white_check_mark:

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
g-9. fix: http://localhost:8080/static/nextstep.txt 했을 때, static file 반환 안되던 문제 해결 :white_check_mark:\
g-10. io stream vs buffer difference? :white_check_mark:\
g-11. catalina vs coyote :white_check_mark:\
g-12. server socket의 threadpool :white_check_mark:


h-1. 서브릿 예제 분석 :white_check_mark:


i-1. 우테코 reflection 예제 :white_check_mark:\
i-2. simple di framework using reflection :white_check_mark:\
i-3. 우테코 reflection 예제 해결 :white_check_mark:\
i-4. copy reflection by meta coding :white_check_mark:\
i-5. analyze reflection by meta coding - implement Dispatcher, componentScan + DTO :white_check_mark:\
i-6. reflection으로 DispatcherServlet extends HttpServlet 만들기 :white_check_mark:\
i-7. reflection으로 ComponentScan 만들기 :white_check_mark:


j-1. what is aop? :white_check_mark:\
j-2. 언제 필터쓰고 언제 AOP씀? :white_check_mark:\
j-3. step1 - MyExceptionHandler :white_check_mark:\
j-4. step2 - log method name & params :white_check_mark:\
j-5. step3 - monitor time of method :white_check_mark:\
j-6. step4 - retry operation :white_check_mark:\
j-7. step5 - log request URI :white_check_mark:\
j-8. step6 - @Valid check -> sentry 연동 :white_check_mark:\
j-9. what is sentry? :white_check_mark:\
j-10. sentry의 로그파일을 서버 안 파일 말고 웹으로 보기 :white_check_mark:


k-1. Custom Filter 등록하는 법 :white_check_mark:\
k-2. Filter에서 @Bean 사용 가능한가?  :white_check_mark:\
k-3. 주의! Application.java에 @ServletComponentScan를 추가하고, 또 CustomFilter에 @WebFilter를 추가하면, 스캔 2번한다! :white_check_mark:


l-1. controller: x-www-form-urlencoded -> (String id, String pw) :white_check_mark:\
l-2. controller: text/plain(raw) -> @RequestBody String data :white_check_mark:\
l-3. controller: application/json -> @RequestBody User user :white_check_mark:\
l-4. controller: @PathVariable :white_check_mark:\
l-5. controller: @CrossOrigin :white_check_mark:\
l-6. controller: @Valid :white_check_mark:\
l-7. controller: @Controller vs @RestController :white_check_mark:\
l-8. controller: @RequestParam :white_check_mark:\
l-9. controller: @RequestBody :white_check_mark:\
l-10. controller: @ModelAttribute :white_check_mark:\
l-11. context: @Bean :white_check_mark:\
l-12. context: @Configuration :white_check_mark:\
l-13. context: @Component :white_check_mark:\
l-14. cache: @Cacheable :white_check_mark:\
l-15. cache: @CachePut :white_check_mark:\
l-16. cache: @CacheEvict :white_check_mark:\
l-17. error-handling : @ExceptionHandler :white_check_mark:\
l-18. error-handling: @ControllerAdvice :white_check_mark:\
l-19. error-handling: @ResponseStatus :white_check_mark:\
l-20. domain: @Embedded :white_check_mark:


m-1. http request 진행 순서 :white_check_mark:\
m-2. interceptor vs filter :white_check_mark:\
m-3. interceptor vs AOP :white_check_mark:\
m-4. interceptor을 통한 간단한 인증 구현 :white_check_mark:\
m-5. afterCompletion()에서 예외 처리하기 :white_check_mark:


n-1. lifecycle: InitializingBean, DisposableBean :white_check_mark:\
n-2. lifecycle: @PostConstruct, @PreDestroy :white_check_mark:


o-1. modern한 방식으로 exception handling하기 :white_check_mark:\
o-2. error handling 전체 구조 :white_check_mark:\
>Q. I18constant 처리 하는 이유 :white_check_mark:\
>Q. @RestControllerAdvice 는 왜 쓰는가? :white_check_mark:\
>Q. Exception handling을 따로 처리하는 이유 :white_check_mark:\
>Q. 서버 에러 로그를 response payload에 "여러개" 첨가해서 보내는 방법 :white_check_mark:

o-3. 서버 에러 로그를 response payload에 첨가해서 보내는 방법 in application.yml :white_check_mark:\
o-4. Employee가 던지는 Exception을 SecondLeader -> Leader로 던져 Leader가 Exception 처리 :white_check_mark:\
o-5. Exception 종류 (checked, unchecked) :white_check_mark:\
o-6. exception handling tips :white_check_mark:\
o-7. exception handling in @Transactional :white_check_mark:\
o-8. spring retry on controller & service :white_check_mark:\
o-9. exception 메시지를 class -> enum으로 구현 :white_check_mark:\
o-10. business exception :white_check_mark:\
o-11. test globalExceptionHandler :white_check_mark:

p-1. bean & scope :white_check_mark:\
p-2. Environment(ex. Profile) :white_check_mark:\
p-3. messageSource :white_check_mark:\
p-4. ApplicationEventPublisher(Observer) :white_check_mark:\
p-5. Validation :white_check_mark:\
p-6. DataBinding, Converter, Formatter :white_check_mark:\
p-7. ResourceLoader :white_check_mark:


q-1. spring-actuator with prometheus + grafana :white_check_mark:


---\
reference

a-1. [build mvc framework from scratch](https://github.dev/yeon-06/jwp-dashboard-mvc/tree/step3)

b. [우테코 - 만들면서 배우는 스프링 실습 코드 http cache](https://github.com/woowacourse/jwp-hands-on)

c. [spring 내장 cache](https://github.dev/namusik/TIL-SampleProject/tree/main/Cache/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20Cache%20%EC%98%88%EC%A0%9C)

d. [우테코 - 만들면서 배우는 스프링 실습 코드 di](https://github.com/woowacourse/jwp-hands-on) \
d. [field, setter, constructor injections comparison](https://yaboong.github.io/spring/2019/08/29/why-field-injection-is-bad/)

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
g-8. [login with session & cookie](https://github.dev/woowacourse/jwp-dashboard-http/tree/2yujeong) \
g-10. [io stream vs buffer difference](https://yeonyeon.tistory.com/267) \
g-11. [Query String 파싱](https://yeonyeon.tistory.com/276?category=1004438)

h-1. [우테코 - servlet solution](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection)

i-1. [우테코 - reflection 예제](https://github.dev/woowacourse/jwp-hands-on) \
i-2. [10분 테코톡 - 파랑, 아키의 리플렉션 -> mvp di framework 분석](https://www.youtube.com/watch?v=67YdHbPZJn4&t=469s) \
i-3. [우테코 - reflexion 예제 문제 해결](https://github.dev/woowacourse/jwp-hands-on/tree/solution-3-servlet-reflection) \
i-4. [copy reflection by meta coding](https://github.dev/codingspecialist/Reflection-Controller) \
i-5. [analyze reflection by meta coding - implement Dispatcher, componentScan + DTO](https://www.youtube.com/watch?v=P5fPc2tjOko&list=PL93mKxaRDidFGJu8IWsAAe0O7y6Yw9f5x&index=1) \
i-7. [reflection으로 ComponentScan 만들기 by meta coding](https://blog.naver.com/PostView.naver?blogId=getinthere&logNo=221841916039&categoryNo=42&parentCategoryNo=0&viewDate=&currentPage=6&postListTopCurrentPage=&from=postList&userTopListOpen=true&userTopListCount=10&userTopListManageOpen=false&userTopListCurrentPage=6)

k-1. [filter init](https://blog.naver.com/getinthere/222094919059) \
k-2. [DelegatingFilterProxy로 Filter를 빈에 등록](https://mangkyu.tistory.com/221?category=761302)

j-1,2,3,4,5,6,7. [project init - meta coding aop](https://github.dev/codingspecialist/Springboot-Special-Lecture)

l-7. [@Controller vs @RestController](https://mangkyu.tistory.com/49?category=761302) \
l-8,9,10. [@RequestBody, @ModelAttribute, @RequestParam의 차이](https://mangkyu.tistory.com/72?category=761302) \
l-11,12,13. [@Bean, @Configuration, @Component](https://mangkyu.tistory.com/75?category=761302) \
l-14,15,16. [@Cacheable, @CachePut, @CacheEvict](https://mangkyu.tistory.com/179?category=761302)

m-1. [interceptor init](https://blog.naver.com/PostView.naver?blogId=getinthere&logNo=221718319587&categoryNo=42&parentCategoryNo=0&viewDate=&currentPage=8&postListTopCurrentPage=&from=postList&userTopListOpen=true&userTopListCount=10&userTopListManageOpen=false&userTopListCurrentPage=8) \
m-2. [interceptor vs filter vs aop](https://mangkyu.tistory.com/173)

n-1. [lifecycle: InitializingBean, DisposableBean](https://mangkyu.tistory.com/126?category=761302) \
n-2. [lifecycle: @PostConstruct, @PreDestroy](https://mangkyu.tistory.com/126?category=761302)

o-1. [exception handling project init](https://github.dev/thombergs/code-examples/tree/master/spring-boot/exception-handling) \
o-2. [exception handling 부가 설명](https://mangkyu.tistory.com/204?category=761302)

p-1~7. [Spring IoC Container 기능](https://galid1.tistory.com/513) \
p-6. [Ioc data binding](https://yadon079.github.io/2021/spring/data-binding-abstraction-01)

q-1. [devops/monitoring/actuator+prometheus+grafana](https://github.com/Doohwancho/devops/tree/main/monitoring/actuator%2Bprometheus%2Bgrafana)

---

x-1. [우테코 - aop](https://github.dev/woowacourse/jwp-hands-on/tree/WIP)

x-1. [우테코- connection pool](https://github.dev/woowacourse/jwp-hands-on/tree/WIP)

x-1. [우테코 - transaction](https://github.dev/woowacourse/jwp-hands-on/tree/WIP)

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

x-1. logging - sentry

x-1. dispatcher\
    ..가 하는 일\
    1. 주소 매핑\
    2. reflection으로 IoC컨테이너에 @Controller 등 어노테이션 붙은 빈들 집어넣음.\
    3. 해당 빈의 메서드들이 요구하는 parameter랑 HttpRequest의 값을 비교해서 DI해줌


x-x. [springboot source code 분석하기](https://mangkyu.tistory.com/210?category=761302) \
x-x. [source code 기반 spring ioc analysis](https://www.fatalerrors.org/a/source-code-analysis-of-spring-ioc-container.html)

x-x. [springboot actuator crashcourse](https://www.youtube.com/watch?v=LQlypTjmgZM) \
x-x. [springboot actuator github code](https://reflectoring.io/spring-boot-info-endpoint/) \
x-x. [healthcheck using actuator && prometheus](https://reflectoring.io/spring-boot-health-check/)
