package com.cho.lifecycle;

import com.cho.lifecycle.level2.CacheClient2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy") //생성 메서드, 소멸 메서드 지정.
    public CacheClient2 cacheClient() {
        return new CacheClient2("http://localhost:1234");
    }

}

/*

---
상황


Service 계층에서 Arcus 캐시로 접근해야 하는 상황
개발을 진행하면서 애플리케이션을 구동할 때 Service 계층에서 Arcus 캐시로 접근해야 하는 상황이 발생하였다.
Arcus 문서에는 서버 종료 시에 shutdown() 함수를 반드시 호출하라고 명시되어 있었다.
이러한 상황에서 어떻게 Bean의 소멸을 처리할 것인지 문제가 발생


---
해결책


가상의 CacheClient를 통해 Bean의 라이프사이클을 제어


 */