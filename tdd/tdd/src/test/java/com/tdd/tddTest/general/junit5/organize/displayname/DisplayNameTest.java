package com.tdd.tddTest.general.junit5.organize.displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("테스트 로그에 이게 보이나?")
public class DisplayNameTest {
    @Test
    @DisplayName("테스트 로그에 이게 보이나?")
    public void displayNameTest(){
        assertTrue(true);
    }

    @Test
    @DisplayName("테스트 로그에 이게 보이나????")
    public void displayNameTest2(){
        assertTrue(true);
    }
}

/*

안보이는 경우,
1. command+,
2. build tools -> gradle -> run test using intellij, not gradle

 */
