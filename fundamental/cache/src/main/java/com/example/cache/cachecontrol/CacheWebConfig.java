package com.example.cache.cachecontrol;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.CacheControl;
/*

Cache-Control은 Etag와 더불어, 컨텐츠 캐싱에 주로 쓰인다(2020년 기준)

Cache-Control에서 가장 많이 쓰이는 3가지 정책

1. max-age = n: 초 단위로 캐시 신선도를 설정한다. 예를 들어 60 * 60 = 3600을 입력하면 한 시간, 3600 * 24 = 86400을 입력하면 하루동안 캐시가 유지된다. 그 이후엔 서버에 요청한 뒤 304 응답을 받을 때에만 캐시를 이용한다.
2. no-cache: 캐시가 유효한지 확인하기 위해 매번 서버에 요청한다.
3. no-store: 어떤 요청도 캐시로 저장하지 않는다.


no-cache에서 2종류 파생
1. public: 어떤 요청에 대해서든 캐시를 저장한다.
2. private: 타인과 공유되는 프록시 서버에는 캐시를 저장하지 않는다. 최종 사용자의 클라이언트에만 캐시를 저장한다.

 */

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

/*
---
what is cache?

캐시는 컴퓨터 과학에서 데이터나 값을 미리 복사해 놓는 임시 장소를 가리킨다.
캐시는 캐시의 접근 시간에 비해 원래 데이터를 접근하는 시간이 오래 걸리는 경우나 값을 다시 계산하는 시간을 절약하고 싶은 경우에 사용한다.


---
why use cache?

캐시를 사용하면 불필요한 요청을 줄이면서 서버의 부하를 줄일 수 있고,
미리 캐시에 저장해 놓은 값을 사용함으로써 빠른 응답을 할 수 있다.
추가적으로 캐싱의 동작 대상은 정적리소스에 대해 사용하고 HTTP Method는 GET에 대한 응답을 캐싱하는 것으로 제한한다.


 */

@Configuration
public class CacheWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final var interceptor = new WebContentInterceptor();
        interceptor.addCacheMapping(CacheControl.noCache().cachePrivate(), "/*"); //no-cache && private
        registry.addInterceptor(interceptor);

        //http://localhost:8080/ 이나, http://localhost:8080/resource-versioning 에서
        //http response header 까 보면, Cache-Control에 no-cache, private가 뜬다.
        //근데 CacheBustingWebConfig에 가서 static 파일 설정 봐보자
    }
}
