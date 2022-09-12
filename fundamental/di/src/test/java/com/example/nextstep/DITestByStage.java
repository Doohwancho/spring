package com.example.nextstep;

import com.example.nextstep.di.stage0.UserService0;
import com.example.nextstep.di.stage1.UserDao1;
import com.example.nextstep.di.stage1.UserService1;
import com.example.nextstep.di.stage2.InMemoryUserDao2;
import com.example.nextstep.di.stage2.UserDao2;
import com.example.nextstep.di.stage2.UserService2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DITestByStage {

    @Test
    void stage0() {
        final var user = new User(1L, "gugu");

        final var actual = UserService0.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu"); //true

        user.setAccount("haha");

        assertThat(actual.getAccount()).isEqualTo("haha"); //true
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

        final UserDao2 userDao = new InMemoryUserDao2();
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
}
