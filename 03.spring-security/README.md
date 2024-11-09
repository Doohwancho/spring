---\
Objective


modularize concepts in spring-security



---\
Concepts


a. spring security basics :white_check_mark:\
b. cookie\
c. session :white_check_mark:\
d. OAuth :white_check_mark:\
e. jwt :white_check_mark:\
f. authentication :white_check_mark:\
g. jwt-refresh-token :white_check_mark:\
h. react+springboot+jwt :white_check_mark:\
i. jwt+refresh-token+redis :white_check_mark:\
j. tdd-security :white_check_mark:\
k. email-verification :white_check_mark:\
x. CORS



---\
Todos


a-1. springboot security project setup :white_check_mark:\
a-2. basic register :white_check_mark:\
a-3. password encryptor :white_check_mark:\
a-4. basic login :white_check_mark:\
a-5. 페이지 접근 권한 설정 :white_check_mark:\
a-x. remember-me
a-x. spring security overall structure

c-1. build session manager in tomcat from scratch :white_check_mark:\
c-x. 로그인에 성공하면 HTTP Reponse의 헤더에 Set-Cookie가 존재한다.\
c-x. 서버에 세션을 관리하는 클래스가 있고, 쿠키로부터 전달 받은 JSESSIONID 값이 저장된다.\
c-x. Executors로 Thread Pool 적용\
c-x. 동시성 컬렉션 사용하기

d-1. 일반 로그인 UserDetails 처리 :white_check_mark:\
d-2. google oauth 설정 :white_check_mark:\
d-3. UserDetails와 OAuth2를 하나의 객체로 관리 :white_check_mark:\
d-4. 구글 OAuth로 로그인하면 자동 회원가입 :white_check_mark:

e-2. jwt project clone + curiousities list up :white_check_mark:\
e-3. what is cookie? :white_check_mark:\
e-4. what is session? how it works? when does it end? weakness? :white_check_mark:\
e-5. what is cia? :white_check_mark:\
e-6. what is RSA? :white_check_mark:\
e-7. how does 전자 문서 서명 work from RSA? :white_check_mark:\
e-8. what is RFC document? :white_check_mark:\
e-9. what is jwt? :white_check_mark:\
e-10. structure of jwt :white_check_mark:\
e-11. how RSA in jwt works? :white_check_mark:\
e-12. how jwt solved problem of session? :white_check_mark:\
e-13. what is bearer? :white_check_mark:\
e-14. jwtAuthenticationFilte :white_check_mark:\
e-15. jwtAuthorizationFilter :white_check_mark:\
e-x. springboot(jwt+Oauth2)\
e-x. springboot+react(jwt+Oauth2)

f-1. spring security authentication process :white_check_mark:\
f-2. token 기반 authentication :white_check_mark:

g-1. jwt token + refresh token :white_check_mark:

h-1. 로그인 성공시 서버로부터 jwt토큰 받은걸 localstorage에 저장한 후, 두고두고 꺼내 씀 (ApiService.js) :white_check_mark:\
h-2. 로그아웃 시, localstorage에 jwt토큰 null 입력 (ApiService.js) :white_check_mark:

i-0. init jwt+refresh token+redis :white_check_mark:\
i-1. refresh token mechanism :white_check_mark:\
i-2. Refresh token의 취약점 :white_check_mark:\
i-3. Why use Redis? :white_check_mark:\
i-4. reissue() :white_check_mark:\
i-5. logout :white_check_mark:\
i-6. optimize - loadByUserName()에 Redis @Cache 걸어서 db io 최소화 하기 :white_check_mark:\
i-7. Member.java를 CustomUserDetails로 분리 :white_check_mark:\
i-8. apply Authority("ROLE_USER" to user, "ROLE_ADMIN" to admin) :white_check_mark:\
i-9. unit test :white_check_mark:\
i-10. integration test - register, login, access token, refresh token :white_check_mark:\
i-11. integration test - logout :white_check_mark:\
i-12. content-type: application/json :white_check_mark:\
i-13. mock test - reissue refresh token :white_check_mark:

k-1. google email verification module :white_check_mark:\
k-2. confidential 정보를 application-secret.yml에 저장하고, .gitignore에서 제외한 후, application.yml에서 import해서 사용 :white_check_mark:


---\
reference


a-1~5. [spring boot security project init by meta coding](https://github.dev/codingspecialist/Sringboot-Security-Basic-V1) \
a-x. [초보자가 이해하는 스프링 시큐리티](https://okky.kr/articles/382738) \
a-x. [서버 유저 패스워드 관리하는 법.youtube](https://www.youtube.com/watch?v=7gWgpRYobtQ&list=PLDV-cCQnUlIbH2r12z_ZE2xAChDw3nASv&index=8) \
a-x. [spring security overall structure](https://jeong-pro.tistory.com/205)

b-1. [세션, 쿠키로 로그인을 어떻게 유지시키는가?.youtube](https://www.youtube.com/watch?v=cWUtMHTKdj0)

c-1. [build session management in tomcat from scratch](https://github.dev/yeon-06/jwp-dashboard-http/tree/step4)

d-1~4. [spring boot security oauth v2 by meta coding](https://github.dev/codingspecialist/Springboot-Security-OAuth2.0-V2)

e-1. [jwt, token based login.youtube](https://www.youtube.com/watch?v=zC5dLbZMAW0) \
e-2~15. [springboot security jwt easy by meta coding](https://github.dev/codingspecialist/Springboot-Security-JWT-Easy)\
e-x. [springboot(jwt+Oauth2)](https://github.com/codingspecialist/Springboot-Oauth2.0-Facebook-Google-Login-JWT)\
e-x. [springboot+react(jwt+Oauth2)](https://github.com/codingspecialist/Springboot-JWT-React-OAuth2.0-Eazy)

f-1. [spring security authentication process](https://mangkyu.tistory.com/77?category=761302) \
f-2. [token 기반 authentication](https://mangkyu.tistory.com/57)

g-1. [jwt token + refresh token](https://blog.naver.com/sosow0212/222747372730) \
g-2. [jwt + refresh token race condition problem](https://dev.to/supertokens/the-best-way-to-securely-manage-user-sessions-2ja6)

i. [jwt login practice](https://github.com/JmKanmo/jwt_login_practice) \
i-6. [optimize loadByUserName() by using Redis Cache](https://kobumddaring.tistory.com/61) \
i-7,8. [jwt refresh token 예제](https://github.dev/kobeomseok95/playground)

j. [spring security를 유닛테스트 하라!](https://github.com/jongwon/spring-security-junit5-test)
