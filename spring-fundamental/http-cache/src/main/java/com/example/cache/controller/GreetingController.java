package com.example.cache.controller;

import com.example.cache.version.ResourceVersion;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 인터셉터를 쓰지 않고 response에 직접 헤더값을 지정할 수도 있다.
     */
    @GetMapping("/cache-control")
    public String cacheControl(final HttpServletResponse response) {
        final String cacheControl = CacheControl
                .noCache()
                .cachePrivate()
                .getHeaderValue();
        response.addHeader(HttpHeaders.CACHE_CONTROL, cacheControl); //여기서 http response header에 cacheControl 넣어줌
        return "index";
    }

    @GetMapping("/etag")
    public String etag() {
        return "index";
    }

    @GetMapping("/resource-versioning")
    public String resourceVersioning() {
        return "resource-versioning";
    }
    /*
        맨 처음 static file에 etag+cache가 적용되는 순서

        1. localhost:8080/resource-versioning
        2. CacheWebConfig.java에서 /* 모든 url에 대하여 CacheControl.noCache().cachePrivate() 적용
        3. resource-versioning.html은 handlebar 템플릿이라, {{variable}}이 존재. ex. <script src="{{staticUrls '/js/index.js'}}"></script>
        4. VersionHandlebarsHelper에 있는 staticUrl() 메서드 실행.
        5. ResourceVersion.java에 빈이 생성되면서, ResourceVersion.getVersion()에 서버 시간 시간(빈이 생성된 시간(초단위))이 문자열 형태로 입력됨
        6. VersionHandlebarsHelper에 있는 staticUrl() 메서드에서 "/resources/%s%s" + version.getVersion() + static_path 이 반환됨
        7. CacheBustingWebConfig에서 PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**" 로 요청이 들어온 것에 대해, classpath:/static/ 여기로 redirect해주면서, .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic()); 해줌.
        8. EtagFilterConfiguration에서 /resources/* 에 대한 etag 부여
        9. resource-versioning.html에 js태그를 보면, <script src="/resources/20220912192024/js/index.js"></script> 이라고 적혀있음
        10. chrome dev tool에 console에 보면, /resource-versioning에 resources/static/js/index.js 파일인 console.log('hello world!'); 적용된거 확인 가능


        Q. 그 이후 동일 자원 요청하면?

        1. 캐시 끝날 때 까지 쓰다가, 기간 끝나면, client->server 로, 동일 static 자원 요청
        2. server에서 etag 비교해서, 너가 캐시한 거랑 같은 놈이야(http status code: 304) 반환


        Q. curiousity
        <script src="/resources/20220912192024/js/index.js"></script> 에서 저 시간, 서버 껐다키면 바뀌나?
        http://localhost:8080/resources/20220912202263/js/index.js

        2022 0912 19:20:24
        2022 0912 20:22:63
        바뀐다. 서버 시작 시간 기준, ResourceVersion Bean이 생성된 시간 기준으로 세팅된다.

        아마 서버 껐다 킬 때마다 바뀌는 이유는, 저 cache-bursting 목적 uri 세팅이, 서버가 재시작 되면, static 파일에 변경이 있음을 가정하고 만든 것 같다.

     */
}
