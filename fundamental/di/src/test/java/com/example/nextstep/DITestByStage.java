package com.example.nextstep;

import com.example.nextstep.di.stage0.UserService1;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DITestByStage {

    @Test
    void stage0() {
        final var user = new User(1L, "gugu");

        final var actual = UserService1.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu"); //true

        user.setAccount("haha");

        assertThat(actual.getAccount()).isEqualTo("haha"); //true
    }

    /*

    user 객체 만들고 UserDao1에 UserService1을 통해 dependency injection 했더니,
    user객체를 공유해서,
    user 객체의 정보가 달라져도, UserDao1에 바뀐 user객체의 정보를 볼 수 있다.

     */
}
