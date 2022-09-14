package com.example.cache.version;

import com.github.jknack.handlebars.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

//handlebar는 html 대체 template으로, <script src="{{staticUrls '/js/index.js'}}"></script> 에서 {{variable}] 쓸 때 쓴다.
@HandlebarsHelper
public class VersionHandlebarsHelper {

    //private: 다른 클래스에서 가로채 사용하지 못하도록
    //static: 인스턴스당 하나만 필요하므로, 또한 직렬화를 피하기 위해. 객체 생성이 될 때마다 해당 객체를 매번 생성하지 않고 초기 클래스 로딩 시 한 번만 생성해서 사용하게 된다.
    //final: 변경될 일이 없으므로
    //얘를 쓰는 이유는 로그 찍기위해. 이제 log.info(VersionHandlebarsHelper.staticUrls); 하면 스프링에 로그가 찍힌다.
    private static final Logger log = LoggerFactory.getLogger(VersionHandlebarsHelper.class);

    private final ResourceVersion version;

    @Autowired
    public VersionHandlebarsHelper(ResourceVersion version) {
        this.version = version;
    }

    public String staticUrls(String path, Options options) { //resource-versioning.html에 <script src="{{staticUrls '/js/index.js'}}"></script> 에서 쓴다.
        log.debug("static url : {}", path); //디버그 시에만 어떤 static url path인지 보여준다.
        return String.format("/resources/%s%s", version.getVersion(), path); //src="/resources/20220911222179/js/index.js" 이게 여기서 옴. 저 날짜(초단위까지)는 version이 만들어졌을 당시 시간
    }
}
