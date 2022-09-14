package com.example.cache.etag;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import static com.example.cache.version.CacheBustingWebConfig.PREFIX_STATIC_RESOURCES; //캐시랑 이어진다

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
public class EtagFilterConfiguration { //서버 오기 직전 서브릿 단에서 필터해주는 것

    //http response header에 etag 담아서 보낸다. 어떻게?
    /*

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    이렇게만 해주면,  해당 스프링부트 앱에서 제공하는 모든 응답에 etag 가 붙게 된다

    요청 url에 따라 다른 etag가 붙는다.

    http://localhost:8080/resources/20220912202263/js/index.js
    로 요청하면, http response header에 etag는
    0adf06cf637aff7c06810711225d7eec6
    이건데,

    http://localhost:8080/etag 이렇게 요청하면, etag는
    06eccc9eb4256540f6d1f272ce2274316 이다

     */

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        final FilterRegistrationBean<ShallowEtagHeaderFilter> registration = new FilterRegistrationBean<>(); //필터에
        registration.setFilter(new ShallowEtagHeaderFilter()); //etag 필터 담는다.
        registration.addUrlPatterns( //해당 url 패턴이 날아올 떄에만, etag를 붙인다.
                "/etag",  // /etag 이 때 붙이고,
                PREFIX_STATIC_RESOURCES + "/*" // js, css 같은 정적 파일에 요청 날아올 때에 요 2케이스때 만 etag 붙인다.
        );
        return registration;
    }
}