package com.cho.lifecycle.level2;

import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RequiredArgsConstructor
public class CacheClient2 {

    private final String url;

    @PostConstruct
    public void init() {
        this.connect();
    }

    @PreDestroy
    public void destroy() {
        this.disconnect();
    }

    // 생성자 이후 호출되어야 함
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 애플리케이션 종료 시 호출되어야 함
    public void disconnect() {
        System.out.println("close + " + url);
    }

}


/*
---
왜 이 방법을 대신 쓸까?


방법 1은
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

이 둘을 쓰는데, 스프링에 종속적임.


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

이 둘은 javax라, 스프링 말고 다른 자바 프레임워크, 컨테이너에서 작동 가능. = 확장성이 더 좋다.
또한 오버라이드 방식은 부모 메서드 고대로 가져와서 메서드 이름 명, 파라미터까지 똑같이 써야하는데,
어노테이션 방식은 구현에 있어서 더 자유롭다.


 */