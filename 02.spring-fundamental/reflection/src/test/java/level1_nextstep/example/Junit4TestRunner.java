package level1_nextstep.example;


import level1_nextstep.annotation.MyTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

class Junit4TestRunner {

    @DisplayName("TODO Junit4Test에서 @MyTest 애노테이션이 있는 메소드 실행")
    @Test
    void run() throws Exception {
        final var clazz = Junit4Test.class;
        final var instance = clazz.getDeclaredConstructor().newInstance();
        Stream.of(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(MyTest.class)) //"MyTest"라는 어노테이션이 달린 메서드 뽑아서
                .forEach(method -> invoke(instance, method)); //실행
    }

    private static void invoke(final Junit4Test instance, final Method method) {
        try {
            method.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
