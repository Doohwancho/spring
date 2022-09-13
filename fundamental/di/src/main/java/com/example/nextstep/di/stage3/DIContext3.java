package com.example.nextstep.di.stage3;

import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * 스프링의 BeanFactory, ApplicationContext에 해당되는 클래스
 *
 * 미구현 상태. 개판이다.
 */
public class DIContext3 {
    public final Set<Object> beans;

    public DIContext3(final Set<Class<?>> classes) {
        this.beans = Set.of();
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(final Class<?> aClass) {
        return null;
    }
}
