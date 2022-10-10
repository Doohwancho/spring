
---\
objective

modularize junit5


---\
standard

1. simple
2. minimal
3. to the core
4. classification


---\
approach

A. get-used-to junit5 fundamental syntax\
B. TDD brief overview\
C. JPA test\
D. MockMvc\
E. 3rd party library: REST-Assured\
F. 3rd party library: Mockito\
G. 3rd party library: swagger\
H. steal(?) from best practices | boiler plates



---\
todo

A-1. clone code crash course * n -> generalize\
A-2. indexing\
A-3. etc(lombok, jpa, etc...)


B-1. why TDD?\
B-2. BDD

C-1. create entity, dto, repository, service to play with\
C-2. clone jpa test\
C-3. indexing

D-1. create base code for MockAPI\
D-2. clone best practice test code 

E-1. restTemplate base code setup\
E-2. rest Template test code immitate\
E-3. rest assured install\
E-4. rest assured basics\
E-5. rest assured 다른사람이 적용한 것 참고(?)

F-1. mockito getting-started
F-2. mockito best practices


---\
credit

A-1. [10분 테코톡 - 🌊 바다의 JUnit5 사용법](https://www.youtube.com/watch?v=EwI3E9Natcw&ab_channel=%EC%9A%B0%EC%95%84%ED%95%9CTech) \
A-1. [JUnit 5: Front To Back (FULL COURSE)](https://www.youtube.com/watch?v=-RW_hyAtujo&ab_channel=Mannodermaus) \
A-1. [Junit 5 documentation](https://junit.org/junit5/docs/current/user-guide/)  

B-1. what is TDD\
B-1. [테스트하기 좋은 코드 - 테스트하기 어려운 코드](https://jojoldu.tistory.com/674) \
B-2. what is BDD\
B-3. [what is Mock](https://happy-coding-day.tistory.com/entry/Mock-%EA%B0%9D%EC%B2%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C-%EC%99%9C-%EC%8D%A8%EC%95%BC%EB%90%A0%EA%B9%8C)

C-1. [SpringBoot로 웹서비스 개발하기 by jojoldu](https://github.com/jojoldu/springboot-webservice) \
C-2. [Springboot - junit을 이용한 DB관련 테스트 작성하는 방법, embedded mongo를 이용한 테스트](https://coding-start.tistory.com/323?category=738631)

D-1. [MockMvc 테스트를 위한 base setup](https://github.com/jojoldu/springboot-webservice) \
D-2. MockMvc 예제 코드 clone

E-1. [java-spring-boot-cars-api](https://github.dev/ro6ley/java-spring-boot-cars-api) \
E-2. [RestTemplate을 이용하여 API 호출하기](https://minkwon4.tistory.com/178) \
E-3. [rest assured library](https://github.com/rest-assured/rest-assured) \
E-4. [Test-Driven Development for Spring Boot APIs](https://stackabuse.com/test-driven-development-for-spring-boot-apis/) \
E-4. [REST Assured를 사용한 REST API 테스트](https://beenlife.tistory.com/34) \
E-5. [next-step/spring-learning-test solved by 손너잘](https://github.dev/bperhaps/spring-learning-test/tree/mvc-minsung) \
E-5. [rest-api-example](https://github.dev/sunghs/rest-api-example) \
E-5. [rest api 개발하기1 - 프로젝트 생성 및 swwagger2 연동](https://sunghs.tistory.com/117) \
E-5. [새로비 springboot rest api](https://engkimbs.tistory.com/category/Spring/Spring%20Rest%20API)

F-1. [Mockito를 이용한 unit test](https://www.crocus.co.kr/1556?category=395790) \
F-2. [mockito-best-practice](https://codechacha.com/ko/mockito-best-practice/)

H. [spring-guide.git](https://github.com/cheese10yun/spring-guide/blob/master/docs/test-guide.md) \
H. [JUnit 5 Test - MockMvc, Mockito, JPA 미완성](https://theheydaze.tistory.com/218?category=935990) \
H. [스프링 부트 강의 - 어라운드 허브](https://www.youtube.com/watch?v=rHJgMRimJ4Y&list=PLlTylS8uB2fBOi6uzvMpojFrNe7sRmlzU&index=1&ab_channel=%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%ED%97%88%EB%B8%8C%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4-AroundHubStudio)