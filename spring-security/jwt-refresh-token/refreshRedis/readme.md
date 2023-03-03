---\
Index


A. how to start?\
B. tech stack\
C. 프로젝트 구조\
D. refresh token mechanism\
E. Refresh token의 취약점\
F. Why use Redis?\
G. reissue()\
H. logout\
I. optimization - loadUserByUsername() with Redis @Cache\
I. more



tips - 코드 관련 궁금증이 드는 부분은, //TODO로 mark 해놓음. intellij - 아래  TODO 탭 활용.




---\
Q. how to start?



1. local mysql(8.0 ver)에 jwt_refresh_redis 디비를 만듬.
2. install & run redis



---\
Q. tech stack


1. jdk 11
2. springboot 2.7.3
3. spring security 5
4. jwt 관련 의존성
5. jpa
6. jdbc
7. redis
8. spring-rest-docs
9. spring-restdocs-mockmvc






---\
refresh token mechanism


![](images/2023-03-02-21-47-25.png)

1. 클라이언트가 로그인을 요청하고 성공하면, 서버는 Access Token과 Refresh Token을 함께 제공한다.
2. 이후 클라이언트는 인가가 필요한 요청에 Access Token을 실어 보낸다.
3. 시간이 조금 흘러 Access Token이 만료 되었다면, 클라이언트는 Refresh Token을 서버로 전달하여 새로운 Access Token을 발급 받는다.


---\
Refresh token의 취약점


1. Access Token을 즉시 차단할 방법의 부재

아무리 Refresh Token이 Access Token의 유효기간을 짧게 만들어 줄 수 있다고 하더라도, 탈취된 Access Token이 유효한 그 짧은 시간 동안에 악용될 수 있다는 위험성이 존재한다.


2. Refresh Token 그 자체를 탈취 당할 가능성

해커에게 Refresh Token 자체를 탈취 당하면 해커는 마음껏 Access Token을 발행할 수 있다. 서버 DB에서 Refresh Token을 저장해 직접 추적하는 방법을 사용하면 조금이나마 피해를 줄일 수 있겠지만, 피해가 확인되기 전까진 탈취 여부를 알 방법이 없다.

RTR(refresh token rotation)을 사용한다면 Refresh Token을 1회 사용하고 버리게 되어 더 안전하게 사용할 수 있지만, 사용하지 않은 Refresh Token을 탈취당하면 해커는 1회 한정으로 Access Token을 발급받을 수 있다.

즉, 이러나 저러나 Refresh Token을 탈취 당할 위험성이 존재한다. 따라서 클라이언트는 XSS, CSRF 공격으로부터 Refresh Token이 탈취되지 않도록 안전하게 보관해야한다.



---\
Why use Redis?


1. mysql db에 refresh token을 저장하면, db io 느리고 부하있는데다가, (1시간마다 jwt token 만료되는거, 어떻게 감당할거야)
2. exp_time 되면, 프로시저로 지워줘야하는게 너무 번거로운데,

1. redis는 in-memory라 db io할 필요없이 빠르고,
2. @TimeToLive(exp_time) 하면, 일정 시간 지나면 redis가 refresh_token 알아서 지워줌. 개꿀.


---\
reissue()


a. 큰 틀

/authorize (인증 여부 판별)  ->   인증 성공 시에 ...  /reissue (토큰 재발급)  ->  사용자 데이터 반환 API 호출


b. 디테일

1. login 성공시 refresh token을 생성해 redis에 보관하고, client에게 jwt token를 http response body에 담아 보내고, refresh token은 cookie에 담아 보내서 local storage에 보관하라고 시킴.
2. 시간이 지나 jwt-access-token이 만료됨
3. client가 main page 접속시, util.js에서 sendAuthorize(); 로 jwt-access-token을 보내는데, 이 때, 권한 없다고 뜸
4. 그러면 util.js.sendReissue()로 cookie로 받아 local storage에 보관하던 refresh token을 보냄
5. POST /member/reissue/ 로 요청 보냄
6. MemberServiceImpl.reissue()에서, redis에 보관하던 refresh token과 client가 보낸 refresh token을 비교 후, 같으면 jwt-access-token을 새로 만들어서 http response body에 담아 주고, refresh token오 새로 만들어서 redis에 저장 후, cookie에 담아 준다. login 때랑 똑같음.




---\
logout()



Q. jwt-access-token, refresh token 있는데, 왜 로그아웃 토큰까지 씀?


LogoutAccessToken의 경우,  로그아웃한 시점에서 accessToken의 남은 시간만큼  TTL(Time To Live)을 갖는 데이터를 Redis에 저장한 뒤  만약, 해당 액세스 토큰으로 재접속을 할 경우, 혹은 악의적인 사용자에 의해 접근이 될 때,  필터에서 redis에 해당 액세스 토큰과 동일한 값을 갖는 LogoutAccessToken이 있는지 검사를 해서 존재할 경우, 로그인을 못하게 막는다.


LogoutAccessTokenService.existsLogoutAccessTokenById()를 통해서.



---\
I. optimization - loadUserByUsername() with Redis @Cache\


http request 올 떄마다, jwt token authenticate할 때, memberService.loadUserByUsername(); 해서 db io하는데, 부하되니까, 이 UserDetails 부분을 Cache하자.


1. JwtAuthenticationFilter.doFilterInternal()
2. this.tokenManager.getAuthenticate();
3. MemberServiceImpl.loadUserByUsername();
	- 원래대로라면, db io 발생한 후, UserDetails를 반환했다면,
	- @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
	- 이걸 걸어서, db io 거치지 않고, cache단에서 반환할 수 있도록 한다.

이걸 하기 위해, 먼저, RedisConfig.java에서 캐시 설정 부분을 추가해주고,

```java
@Bean(name = "cacheManager")
public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
	RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig()
			.disableCachingNullValues() //TODO - MemberService.loadUserByName()에 @Cache 처리 위해 적용
			.entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_EXPIRE_SEC)) //TODO - MemberService.loadUserByName()에 @Cache 처리 위해 적용
			.computePrefixWith(CacheKeyPrefix.simple()) //TODO - MemberService.loadUserByName()에 @Cache 처리 위해 적용
			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

	return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory)
			.cacheDefaults(conf)
			.build();
}
```



CacheKey class 정의해야 한다.

```java
@Getter
public class CacheKey {
    public static final String USER = "user";
    public static final int DEFAULT_EXPIRE_SEC = 60*30;
}

```
여기서 expire_second도 정하네.

4. 또한, logout() 시에, Redis에 보관하던 cache를 없애야 하기 때문에,
	- @CacheEvict(value = CacheKey.USER, key = "#username") 처리를 해준다.



redis로 진짜 들어갔는지 확인해보자.
```redis
redis-cli
FLUSHALL
```

1. run app
2. http:localhost:8080/main
3. 회원가입
4.redis에서 cache된 UserDetails객체를 확인해보자.
    ```redis
    redis-cli
    keys *
    ```
4. pk8294라는 아이디로 가입했더니, redis에 user::pk8294 라고 뜬다.
5. CacheKey에서 exp_time을 60초로 설정했으니, 1분 기다려보고, 다시 keys * 해보자.
   1. user:pk8294가 사라진걸 확인할 수 있다.
6. 다시 무역거래 탭으로 들어가면, cache에 user::pk8294가 들어간걸 확인할 수 있다.


Q. 응? 근데 refresh token도 redis에 있어야 하지 않나?
A. 로그아웃하고 로그인 다시 하면, redis cache에 생김

```
127.0.0.1:6379> keys *
1) "user::pk8294"
2) "refreshToken"
3) "refreshToken:pk8294"
4) "logoutAccessToken:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwazgyOTQiLCJ1c2VybmFtZSI6InBrODI5NCIsImlhdCI6MTY3NzgzOTI1NCwiZXhwIjoxNjc3ODQyODU0fQ.OPFScYgzxy2OzEfc5tBmzo5NWzXw2_l83ulVwKR_R7PLEkHmkGGDpOrVVAcaOmSb0oqgXqxwqzaCsvTlSDxgJw"
5) "logoutAccessToken"
```

60초 후면, user::pk8294는 없어지고,\
6시간 후면 refresh token이 사라지겠지.

로그아웃도 햇으니까, logoutAccessToken도 생긴걸 확인 가능.\
근데, 얘는 hash값으로 저장되네?

```java
@CacheEvict(value = CacheKey.USER, key = "#username")
public void logout(String accessToken) {
	String username = jwtTokenUtil.parseToken(jwtTokenUtil.resolveToken(accessToken));
	accessToken = jwtTokenUtil.resolveToken(accessToken);
	long remainTime = jwtTokenUtil.getRemainTime(accessToken); //logout 시, jwt-access-token의 남은 시간만큼 까서 저장함.
	refreshTokenService.deleteRefreshTokenById(username);
	logoutAccessTokenService.saveLogoutAccessToken(LogoutAccessToken.from(username, accessToken, remainTime));
}

public String parseToken(String token) {
	return parseClaims(token).getSubject();
}
```

여기서 parseClaims할 떄, 얘가 hash 함수라 그런듯?




---\
more


1. logout 되었을 때, 해커가 jwt token들고 authorize()나 reissue() 하려고 하면,
LogoutAccessTokenService.existsLogoutAccessTokenById()를 통해서 방어하는 코드 미구현.(both front and back)
2. MemberServiceImpl.loadUserByUsername()을 @Cacheable로 만들어서, db io 줄이게끔 하는 것 미구현.
3. Member -> CustomUserDetails.java 분리






---\
Reference


https://github.com/JmKanmo/jwt_login_practice
