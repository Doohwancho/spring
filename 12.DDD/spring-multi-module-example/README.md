---\
Index


- [Spring Boot Multi Module](#spring-boot-multi-module)
  - [Module](#module)
  - [Version](#version)
  - [Implementation](#implementation)
  - [Reference](#reference)
  - [takeaway](#takeaway)
    - [application.yml](#applicationyml)
  

# Version
- Java : 11
- SpringBoot: 2.4.4
- build : gradle

# Implementation
- module-core : Entity, Repository
- module-service : Service
- module-api : Controller


# Reference
- [멀티모듈에 한 방으로 적용되는 application.yml](https://devyounji.tistory.com/40)
- [goodGid/Spring-Multi-Module-Template](https://github.com/goodGid/Spring-Multi-Module-Template)



# takeaway 

## application.yml
모든 모듈은 core 를 의존하고 있기 때문에 core에 특정 도메인(A Domain)의 프로퍼티를 동일하게 가지고 있어야 합니다.

처음엔 모듈이 몇 개 되지 않아 모든 모듈의 프로퍼티에 작성해주었는데요. A Domain 에 추가되는 값들을 모든 모듈에서 관리하기란 쉽지 않았습니다.

다중 프로퍼티를 한 번만 기술하기 위해 두 가지 방법을 찾게 되었습니다.


core -> application.yml\
여기에서 profile 단위로 ---로 구분지음.

```yml
spring.profiles: local
app:
  url: localhost:8801
---
spring.profiles: dev
app:
  url: dev:8801
---
spring.profiles: stage
app:
  url: stage:8801
---
spring.profiles: prod
app:
  url: prod:8801
```

그리고 모듈마다 spring.profiles.include: core 로 명시해주면 해당 프로퍼티가 매핑이 됩니다.


---\
postProcessEnvironment

core.property -> YamlEnvironmentPostProcessor 에서 프로퍼티 매핑시켜준다.

spring.factories에 org.springframework.boot.env.EnvironmentPostProcessor=\
me.property.YamlEnvironmentPostProcessor 추가
