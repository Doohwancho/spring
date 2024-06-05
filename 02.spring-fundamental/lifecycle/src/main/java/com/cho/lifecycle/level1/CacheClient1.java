package com.cho.lifecycle.level1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@RequiredArgsConstructor
public class CacheClient1 implements InitializingBean, DisposableBean {

    private final String url;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.connect();
    }

    @Override
    public void destroy() throws Exception {
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
방법


implements InitializingBean, DisposableBean
를 이용해서,

afterPropertiesSet(), destroy()를 오버라이드해서 쓰는 방법.



---
Q. 왜 이 방법 더 이상 안씀?


1. 자바 표준 인터페이스가 아닌 스프링 전용 인터페이스로써 스프링에 종속적이다.
2. 초기화, 소멸 메소드의 이름을 변경할 수 없다.
3. 캐시 등 직접 제어를 해주어야 하는 외부 라이브러리에 적용할 수 없다.


 */