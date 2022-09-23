package example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

class Junit3TestRunner {

    @DisplayName("TODO Junit3Test에서 메서드 이름이 'test'로 시작하는 메소드 실행")
    @Test
    void run() throws Exception {
        final var clazz = Junit3Test.class;
        final var instance = clazz.getDeclaredConstructor().newInstance(); //객체 생성
        Stream.of(clazz.getDeclaredMethods())
                .filter(x -> x.getName().startsWith("test")) //메서드 이름이 "test"로 시작하는 놈들 뽑아서
                .forEach(method -> invoke(instance, method)); //해당 객체에 메서드를 실행함.
    }

    private static void invoke(final Junit3Test instance, final Method method) {
        try {
            method.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

