package com.example.cache.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/*

what is cache bursting?

캐시 쓰면, static file 클라이언트단에서 한번 저장하면, CacheControl.maxAge(Duration.ofDays(365)) 했으니까, 365일동안 쓸거아냐.
근데 만약에 서버에 static file이 변경되었으면?
교체해야하잖아
데이터 변경되었는지 어떻게 알아?


Cache busting is the process of uploading a new file to replace an existing file that is already cached
This is useful because the cache will often take a long time to expire from all of its various locations and cache busting properly ensures that any changes to a file be visible to end users sooner, rather than later.

서버 킨 시점부터 정적파일 쭉 캐싱해놨다가,
서버 껐다키거나, static 파일 변경하면

 */

@Configuration
public class CacheBustingWebConfig implements WebMvcConfigurer {

    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    private final ResourceVersion version;

    @Autowired
    public CacheBustingWebConfig(ResourceVersion version) {
        this.version = version;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**")
                .addResourceLocations("classpath:/static/")
                .setUseLastModified(true)
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic());
        // 일반 /이나, /resource-versioning 에서 http response header 까 보면,
        //Cache-Control에 no-cache, private인데,
        //static파일에 대한 cache-control 설정 여기서 달리해서,
        //http://localhost:8080/resources/20220912202263/js/index.js 로 요청하면, http response header에 cache-control은
        //max-age=31536000, public 이렇게 뜬다.
        //1년동안 이 index.js 파일을 클라이언트 단, 브라우저에 캐시해놓으라는 것.



        //여기서 static파일만 cache-control의 cache 보관일, public인지 private인지 결정한다.
        //365일동안 캐시 보관하는 이유는, 정적파일이 업데이트 될 때 까지 최대한 긴 시간동안 보관하는 것.(365일이 최대일. Cache-Control: max-age=31536000와 같다)
        //원래대로라면, CacheWebConfig에서 /* 모든 url에 대해 CacheControl.noCache().cachePrivate() 처리를 했었는데,
        //그림같은 static 파일 만, 따로 더 장시간 캐시 처리를 한다. public처리 포함.
        //캐시 identifier은 시간(초단위)를 path에 넣어서 기록한다. ex. <script src="/resources/20220911222179/js/index.js"></script>
        //여기서 저 초단위 시간은 ResourceVersion.java이며, 해당 빈은 생성될 때, 초단위 시간이 버전으로 갱신된다.
    }
}