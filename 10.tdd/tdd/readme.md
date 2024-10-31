
---\
objective

modularize testing methods



---\
approach

a. junit5\
b. TDD\
c. JPA test\
d. MockMvc\
e. REST-Assured\
f. Mockito\
g. Spock\
h. jmh\
z. steal



---\
todo

a-1. clone code crash course * n -> generalize :white_check_mark:\
a-2. indexing :white_check_mark:\
a-3. etc(lombok, jpa, etc...) :white_check_mark:\
a-4. dynamic test for 시나리오 테스트 :white_check_mark:

b-1. why TDD? :white_check_mark:\
b-2. BDD :white_check_mark:

c-1. create entity, dto, repository, service to play with :white_check_mark:\
c-2. clone jpa test :white_check_mark:\
c-3. indexing :white_check_mark:

d-1. create base code for MockAPI :white_check_mark:\
d-2. clone best practice test code :white_check_mark:

e-1. restTemplate base code setup :white_check_mark:\
e-2. rest Template test code immitate :white_check_mark:\
e-3. rest assured install :white_check_mark:\
e-4. rest assured basics :white_check_mark:\
e-5. rest assured 다른사람이 적용한 것 참고(?) :white_check_mark:

f-1. mockito getting-started :white_check_mark:\
f-2. mockito best practices :white_check_mark:\
f-3. test double 1: @Mock :white_check_mark:\
f-4. test double 2: @Spy :white_check_mark:\
f-5. @InjectMocks :white_check_mark:\
f-6. test double 3: Stubbing when().thenReturn() && doReturn().when() :white_check_mark:\
f-7. @Captor :white_check_mark:\
f-8. BDD style :white_check_mark:\
f-9. service단 로직 test, 나머진 Mock :white_check_mark:\
f-10. test double 4: dummy :white_check_mark:\
f-11. test double 5: fake :white_check_mark:\
f-12. exception handling in mockito :white_check_mark:

h-1. sum(for문) vs sum(stream) 성능 벤치마크 :white_check_mark:

---\
reference

a-1. [10분 테코톡 - 🌊 바다의 JUnit5 사용법](https://www.youtube.com/watch?v=EwI3E9Natcw&ab_channel=%EC%9A%B0%EC%95%84%ED%95%9CTech) \
a-1. [JUnit 5: Front To Back (FULL COURSE)](https://www.youtube.com/watch?v=-RW_hyAtujo&ab_channel=Mannodermaus) \
a-1. [Junit 5 documentation](https://junit.org/junit5/docs/current/user-guide/) \
a-4. [dynamic test 도입](https://velog.io/@bagt/Dynamic-Test)

b-1. what is TDD\
b-1. [테스트하기 좋은 코드 - 테스트하기 어려운 코드](https://jojoldu.tistory.com/674) \
b-2. what is BDD\
b-3. [what is Mock](https://happy-coding-day.tistory.com/entry/Mock-%EA%B0%9D%EC%B2%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C-%EC%99%9C-%EC%8D%A8%EC%95%BC%EB%90%A0%EA%B9%8C)

c-1. [SpringBoot로 웹서비스 개발하기 by jojoldu](https://github.com/jojoldu/springboot-webservice) \
c-2. [Springboot - junit을 이용한 DB관련 테스트 작성하는 방법, embedded mongo를 이용한 테스트](https://coding-start.tistory.com/323?category=738631)

d-1. [MockMvc 테스트를 위한 base setup](https://github.com/jojoldu/springboot-webservice) \
d-2. MockMvc 예제 코드 clone

e-1. [java-spring-boot-cars-api](https://github.dev/ro6ley/java-spring-boot-cars-api) \
e-2. [RestTemplate을 이용하여 API 호출하기](https://minkwon4.tistory.com/178) \
e-3. [rest assured library](https://github.com/rest-assured/rest-assured) \
e-4. [Test-Driven Development for Spring Boot APIs](https://stackabuse.com/test-driven-development-for-spring-boot-apis/) \
e-4. [REST Assured를 사용한 REST API 테스트](https://beenlife.tistory.com/34) \
e-5. [next-step/spring-learning-test solved by 손너잘](https://github.dev/bperhaps/spring-learning-test/tree/mvc-minsung) \
e-5. [rest-api-example](https://github.dev/sunghs/rest-api-example) \
e-5. [rest api 개발하기1 - 프로젝트 생성 및 swwagger2 연동](https://sunghs.tistory.com/117) \
e-5. [새로비 springboot rest api](https://engkimbs.tistory.com/category/Spring/Spring%20Rest%20API)

f-1. [Mockito를 이용한 unit test](https://www.crocus.co.kr/1556?category=395790) \
f-2. [mockito-best-practice](https://codechacha.com/ko/mockito-best-practice/) \
f-3..8. [mock basic concepts](https://effortguy.tistory.com/141) \
f-9..12. [goal of mockito, test double, exception handling](https://github.com/dinesh-varyani/mockito)

h-1. sum 벤치마크 (for vs stream)[https://pompitzz.github.io/blog/Java/jmh_and_asyncProfiler.html#jmh-%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC]

z. [spring-guide.git](https://github.com/cheese10yun/spring-guide/blob/master/docs/test-guide.md) \
z. [JUnit 5 Test - MockMvc, Mockito, JPA 미완성](https://theheydaze.tistory.com/218?category=935990) \
z. [스프링 부트 강의 - 어라운드 허브](https://www.youtube.com/watch?v=rHJgMRimJ4Y&list=PLlTylS8uB2fBOi6uzvMpojFrNe7sRmlzU&index=1&ab_channel=%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%ED%97%88%EB%B8%8C%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4-AroundHubStudio)
