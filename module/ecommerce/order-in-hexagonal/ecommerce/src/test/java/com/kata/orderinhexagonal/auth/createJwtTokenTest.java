package com.kata.orderinhexagonal.auth;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JwtAuthTest {
    JwtProvider jwtProvider = new JwtProvider();

    @Test
    void createJwtTokenTest() {

        String jwtToken1 = jwtProvider.createJwtToken(1);
        Long memberId1 = jwtProvider.parseToken(jwtToken1);
        String jwtToken2 = jwtProvider.createJwtToken(2);
        Long memberId2 = jwtProvider.parseToken(jwtToken2);
        Assertions.assertThat(memberId1).isEqualTo(1L);
        Assertions.assertThat(memberId2).isEqualTo(2L);
    }

}