package com.tdd.tddTest.junit5.ifelse.assumption;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionTest {

    @Test
    @DisplayName("개발환경이 아니면 abort test")
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation"); //주의! test abort!가 외는게 아니라, ignored가 되어, 나머지 테스트는 처리된다.
        // remainder of test
        System.out.println("hello world");
    }
}
