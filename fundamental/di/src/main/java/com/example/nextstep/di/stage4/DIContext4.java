package com.example.nextstep.di.stage4;

import java.util.Set;

/**
 * 스프링의 BeanFactory, ApplicationContext에 해당되는 클래스
 *
 * 완성 안되서 null 반환하고 에러남
 */

public class DIContext4 {
    private final Set<Object> beans;

    public DIContext4(final Set<Class<?>> classes) {
        this.beans = Set.of();
    }

    public static DIContext4 createContextForPackage(final String rootPackageName) {
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(final Class<T> aClass) {
        return null;
    }
}
