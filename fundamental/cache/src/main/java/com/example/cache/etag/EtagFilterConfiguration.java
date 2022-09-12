package com.example.cache.etag;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

/*

what is Etag?

ETag는 저희가 사용하는 캐시가 유효한지 검증하기 위해 사용한다.

서버의 리소스가 변경된다면 어떨까?
그러면 저희가 저장해 놓은 캐시의 데이터와 서버의 리소스 데이터는 다른 값이겠지?
그때 캐시가 서버에게 리소스가 변경되었는지 안 되었는지 물어보는 것을 캐시 유효성 검사라고 한다.
우리는 ETag를 사용하여 캐시 유효성 검사를 하는 것이다.

http request 소스가 달라지면, etag도 달라진다.

 */

@Configuration
public class EtagFilterConfiguration {

//    @Bean
//    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
//        return null;
//    }
}