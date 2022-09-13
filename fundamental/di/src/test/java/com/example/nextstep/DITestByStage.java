package com.example.nextstep;

import com.example.di.nextstep.User;
import com.example.di.nextstep.stage0.UserService0;
import com.example.di.nextstep.stage1.UserDao1;
import com.example.di.nextstep.stage1.UserService1;
import com.example.di.nextstep.stage2.InMemoryUserDao2;
import com.example.di.nextstep.stage2.UserDao2;
import com.example.di.nextstep.stage2.UserService2;
import com.example.di.nextstep.stage3.DIContext3;
import com.example.di.nextstep.stage3.InMemoryUserDao3;
import com.example.di.nextstep.stage3.UserService3;
import com.example.di.nextstep.stage4.DIContext4;
import com.example.di.nextstep.stage4.UserService4;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DITestByStage {

    private static final Logger log = LoggerFactory.getLogger(DITestByStage.class);

    @Test
    void stage0() {
        final var user = new User(1L, "gugu");

        final var actual = UserService0.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu");

        user.setAccount("haha");

        assertThat(actual.getAccount()).isEqualTo("haha");
    }

    /*

    stage0 - static reference

    user 객체 만들고 UserDao1에 UserService1을 통해 dependency injection 했더니,
    user객체를 공유해서,
    user 객체의 정보가 달라져도, UserDao1에 바뀐 user객체의 정보를 볼 수 있다.

    이름을 static reference로 지은건,
    Service를 통해 DAO를 reference하고 있기 때문인 듯?

     */

    @Test
    void stage1() {
        final var user = new User(1L, "gugu");

        final var userDao = new UserDao1();
        final var userService = new UserService1(userDao); //constructor injection

        final var actual = userService.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu");
    }

    /*

    stage1 - constructor injection

    VO - User.java
    DAO - UserDao1.java
    DI Helper - UserService1.java


    diff stage0 stage1

    1. 일단 둘 다 User 객체를 DAO에서 관리하고 UserService에서 DAO로 DI해주는건 똑같은데, stage0에서 DAO는 Service에서 DI할 때에만 referenced 당하는 것에 비해, stage1은 Service에서 DAO를 DI받아 관리함.
    2. 왜 DAO를 따로 관리하냐라고 물으면, DAO에 DataSource가 추가되어 db와 통신하기 때문. db통신하는 객체와 user관리하는 객체의 역할을 분리하기 위해서 랄까나?

     */

    @Test
    void stage2() {
        final var user = new User(1L, "gugu");

        final UserDao2 userDao = new InMemoryUserDao2(); //UserDao2 interface먹였기 때문에, 다른 db에 datasource를 종류별로 붙여도, 모두 UserDao2로 추상화 가능
        final var userService = new UserService2(userDao);

        final var actual = userService.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu");
    }

    @Test
    void stage2testAnonymousClass() {
        // given
        final var userDao = new UserDao2() {
            private User user;

            @Override
            public void insert(User user) {
                this.user = user;
            }

            @Override
            public User findById(long id) {
                return user;
            }
        };
        final var userService = new UserService2(userDao);
        final var user = new User(1L, "gugu");

        // when
        final var actual = userService.join(user);

        // then
        assertThat(actual.getAccount()).isEqualTo("gugu");
    }

    /*

    stage2 - constructor within interface

    diff stage1 stage2

    DAO에 interface먹임.
    왜?
    UserDao2 interface먹였기 때문에, 다른 db에 datasource를 종류별로 붙여도, 모두 UserDao2로 추상화 가능


     */

    @Test
    void stage3() throws Exception{
//        final var user = new User(1L, "gugu");

//        final DIContext3 diContext = createDIContext3();
//        final var userService = (UserService3)diContext.getBean(UserService3.class);

//        log.info(userService.getClass().getName()); //null

//        final var actual = userService.join(user);

//        assertThat(actual.getAccount()).isEqualTo("gugu");
    }

    private DIContext3 createDIContext3() {
        var classes = new HashSet<Class<?>>();
        classes.add(InMemoryUserDao3.class);
        classes.add(UserService3.class);
        return new DIContext3(classes);
    }

    /*

    stage3 - context

    diff stage2 stage3

    context에 DAO, Service가 DI로 담기고, context가 User를 DI로 담긴 DAO를 DI로 담긴 Service를 관리한다.

    DIContext3.java가 구현이 되지 않아서 위의 테스트 코드는 작동하지 않지만,

    여튼 핵심은 Context가 Bean관리를 Set으로 하고, 컨텍스트에서 빈을 꺼내서, 가지고 논다.

     */


    @Test
    void stage4() {
        final var user = new User(1L, "gugu");

        final var diContext = createDIContext4();
        final var userService = diContext.getBean(UserService4.class); //역시 diContext가 완성이 안되서 null 반환하고 에러남

        final var actual = userService.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu");
    }

    private static DIContext4 createDIContext4() {
        final var rootPackageName = DITestByStage.class.getPackage().getName();
        return DIContext4.createContextForPackage(rootPackageName);
    }


    /*

    stage4 - annotations

    DI를 할 때 Bean 주입하는걸, 생성자 파라미터로 안하고 annotation을 이용해서(@Inject) 하는 걸 표현한 듯
    이 예제도 DIContext4가 완성이 안되서 에러남.
    ClassPathScanner또한 완성이 안되어 있다.


    diff stage3 stage4

    1. context객체 생성도 DI방식으로 엘레강스(?) 하게 바뀜
    2. ClassPathScanner가 생김. 아마 DIContext4에서 .getBean()할 때, 어느 class path에 해당 bean 이 있는지 찾는거겠지?
    그리고 다 찾았으면, 해당 클래스 이름으로 생성자 만들고, 생성자로 new instance()하는 거겠지? (DIContext에 Set<beans>에 없는 경우. 있으면 꺼내주고)
    3. annotation이 생김. ClassPathScanner가 빈을 찾을 때 annotation으로 찾는가 봄(@Inject, @Service, @Repository)


     */
}
