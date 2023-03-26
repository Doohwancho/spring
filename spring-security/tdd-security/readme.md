---\
Goal


tdd in spring security


---\
How


takeaways에 관련된 내용은\
`TODO - ${takeaway-id}: ${내용}`\
...의 형식으로 index 되어있다.\
intellij에 TODO search 활용해서 찾자.



---\
Concepts


j-a. access test with authority :white_check_mark:\
j-b. user authority test :white_check_mark:\
j-c. bbs + jwt + refresh token :white_check_mark:




---\
Todos


j-a-1. controller에서 유저 권한에 다른 접근 제어 :white_check_mark:\
j-a-2. @WithMockUser() 사용법 :white_check_mark:\
j-a-3. status code 401 vs 403 :white_check_mark:

j-b-1. User implements UserDetails에서 필드 & override methods 쓰임세 :white_check_mark:\
j-b-2. user 정보 update할 떄마다 update time 갱신 :white_check_mark:\
j-b-3. user & admin authority test 방법론 :white_check_mark:

j-c-1. jwt token 생성 테스트 :white_check_mark:\
j-c-2. login filter :white_check_mark:\
j-c-3. JWTCheckFilter로 jwt token verify 후 SecurityContextHolder에 유저 저장 :white_check_mark:\
j-c-4. apply bbs :white_check_mark:



---\
Reference


https://github.com/jongwon/spring-security-junit5-test

