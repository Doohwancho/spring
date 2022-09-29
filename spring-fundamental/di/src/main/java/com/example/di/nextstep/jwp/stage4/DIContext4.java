package com.example.di.nextstep.jwp.stage4;

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

        //beans.addAll(classes); 필요
    }

    public static DIContext4 createContextForPackage(final String rootPackageName) {

        //이 메서드를 실행시킨 주체 메서드를 root로 삼아서 new DIContext4(); 를 실행시켜 beans를 초기화시킨다.

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(final Class<T> aClass) {

        //입력받은 파라미터를 저 beans에서 찾아 있으면 반환하고 없으면 throw runtime error

        //근데 자바에서 Set은 get()메서드가 없는데 우쨔냐 map으로 해야할거 같은디

        return null;
    }
}
