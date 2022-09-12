package com.example.nextstep;

import com.example.nextstep.di.stage0.UserService1;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DITestByStage {

    @Test
    void stage0() {
        final var user = new User(1L, "gugu");

        final var actual = UserService1.join(user);

        assertThat(actual.getAccount()).isEqualTo("gugu");
    }
}
