package level1_nextstep.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import level1_nextstep.vo.Question;
import level1_nextstep.vo.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    @DisplayName("reflection으로 클래스 이름 뽑을 때도, 다 같은 이름이 아니다!")
    @Test
    void givenObject_whenGetsClassName_thenCorrect() {
        final Class<Question> clazz = Question.class;

        assertThat(clazz.getSimpleName()).isEqualTo("Question");
        assertThat(clazz.getName()).isEqualTo("vo.Question");
        assertThat(clazz.getCanonicalName()).isEqualTo("vo.Question");
    }

    @DisplayName("Class.forName()이렇게도 클래스 객체 생성 가능하구나")
    @Test
    void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        final Class<?> clazz = Class.forName("level1_nextstep.vo.Question");

        assertThat(clazz.getSimpleName()).isEqualTo("Question");
        assertThat(clazz.getName()).isEqualTo("vo.Question");
        assertThat(clazz.getCanonicalName()).isEqualTo("vo.Question");
    }

    @DisplayName("해당 클래스 객체가 가진 모든 필드의 이름을 리스트에 담아 조회")
    @Test
    void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        final Object student = new Student();
        final Field[] fields = student.getClass().getDeclaredFields();
        final List<String> actualFieldNames = Stream.of(fields)
                .map(Field::getName)
                .collect(Collectors.toList());

        assertThat(actualFieldNames).contains("name", "age");
    }

    @DisplayName("해당 클래스 객체가 가진 모든 메서드의 이름을 리스트에 담아 조회")
    @Test
    void givenClass_whenGetsMethods_thenCorrect() {
        final Class<?> animalClass = Student.class;
        final Method[] methods = animalClass.getDeclaredMethods();
        final List<String> actualMethods = Stream.of(methods)
                .map(Method::getName)
                .collect(Collectors.toList());

        assertThat(actualMethods)
                .hasSize(3)
                .contains("getAge", "toString", "getName");
    }

    @DisplayName("Question 클래스는 생성자가 2개다. 파라미터 3개자리 하나, 6개짜리 하나")
    @Test
    void givenClass_whenGetsAllConstructors_thenCorrect() {
        final Class<?> questionClass = Question.class;
        final Constructor<?>[] constructors = questionClass.getDeclaredConstructors();

        assertThat(constructors).hasSize(2);
    }


    @DisplayName("같은 클래스 객체인데, 다른 생성자를 이용해 객체 생성 후 검증")
    @Test
    void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect() throws Exception {
        final Class<?> questionClass = Question.class;

        final Constructor<?> firstConstructor = questionClass.getDeclaredConstructors()[0];
        final Constructor<?> secondConstructor = questionClass.getDeclaredConstructors()[1];

        final Question firstQuestion = (Question) firstConstructor.newInstance("gugu", "제목1", "내용1");
        final Question secondQuestion = (Question) secondConstructor.newInstance(0, "gugu", "제목2", "내용2", new Date(), 0);

        assertThat(firstQuestion.getWriter()).isEqualTo("gugu");
        assertThat(firstQuestion.getTitle()).isEqualTo("제목1");
        assertThat(firstQuestion.getContents()).isEqualTo("내용1");
        assertThat(secondQuestion.getWriter()).isEqualTo("gugu");
        assertThat(secondQuestion.getTitle()).isEqualTo("제목2");
        assertThat(secondQuestion.getContents()).isEqualTo("내용2");
    }

    @DisplayName("Question 클래스의 private 필드는 6개지만, public 필드는 0개다.")
    @Test
    void givenClass_whenGetsPublicFields_thenCorrect() {
        final Class<?> questionClass = Question.class;
        final Field[] fields = questionClass.getFields();

        assertThat(fields).hasSize(0);
    }

    @DisplayName(".getFields()는 public fields 반환. .getDeclaredFields()는 private field 반환.")
    @Test
    void givenClass_whenGetsDeclaredFields_thenCorrect() {
        final Class<?> questionClass = Question.class;
        final Field[] fields = questionClass.getDeclaredFields();

        assertThat(fields).hasSize(6);
        assertThat(fields[0].getName()).isEqualTo("questionId");
    }


    @DisplayName(".getDeclaredField()로 가져온 6개의 private field 중, 이름이 'questionId' 반환 ")
    @Test
    void givenClass_whenGetsFieldsByName_thenCorrect() throws Exception {
        final Class<?> questionClass = Question.class;
        final Field field = questionClass.getDeclaredField("questionId");

        assertThat(field.getName()).isEqualTo("questionId");
    }


    @DisplayName(".getDeclaredField()로 가져온 'questionId' 필드의 타입은 long이다.")
    @Test
    void givenClassField_whenGetsType_thenCorrect() throws Exception {
        final Field field = Question.class.getDeclaredField("questionId");
        final Class<?> fieldClass = field.getType();

        assertThat(fieldClass.getSimpleName()).isEqualTo("long");
    }

    @DisplayName("Student class에 age는 분명 private인데, .setAccessible(true)하니까 외부에서 access 가능해졌다.")
    @Test
    void givenClassField_whenSetsAndGetsValue_thenCorrect() throws Exception {
        final Class<?> studentClass = Student.class;
        final Student student = (Student) studentClass.getDeclaredConstructor().newInstance();
        final Field field = studentClass.getDeclaredField("age");

        field.setAccessible(true);

        assertThat(field.getInt(student)).isZero();
        assertThat(student.getAge()).isZero();

        field.set(student, 99);

        assertThat(field.getInt(student)).isEqualTo(99);
        assertThat(student.getAge()).isEqualTo(99);
    }
}

