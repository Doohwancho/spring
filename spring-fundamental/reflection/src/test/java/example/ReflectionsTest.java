package example;

import annotation.Controller;
import annotation.Repository;
import annotation.Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

class ReflectionsTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    @DisplayName("TODO 클래스 레벨에 @Controller, @Service, @Repository 애노테이션이 설정되어 모든 클래스 찾아 로그로 출력한다. applicationContext가 하는 것인 듯?")
    @Test
    void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("example");

        final var classes = new HashSet<Class<?>>();
        classes.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        classes.addAll(reflections.getTypesAnnotatedWith(Service.class));
        classes.addAll(reflections.getTypesAnnotatedWith(Repository.class));

        classes.forEach(aClass -> log.info(aClass.getName()));
    }
}

