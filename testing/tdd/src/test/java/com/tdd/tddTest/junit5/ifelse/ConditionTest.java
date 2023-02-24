package com.tdd.tddTest.junit5.ifelse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.condition.OS.MAC;

public class ConditionTest {

    @Disabled
    public void disabledTest() { System.out.println("disabled"); }

    /*
        build.gradle
        test {
            environment "MY_ENV_VAR", "true"
        }
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "MY_ENV_VAR", matches = "true")
    public void onlyRunIfTheEnvironmentVariableIsSet() {
        assertTrue(1 == 1);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    public void onlyRunThisOnJava8() {
        assertTrue(2 + 2 == 4);
    }

    @Test
    @EnabledOnOs(MAC)
    public void onlyRunThisOnMac() {
        assertTrue(System.getProperty("user.home").startsWith("/Users"));
    }

//    @Test
//    @EnabledOnOs(WINDOWS)
//    public void onlyRunThisOnWindows() {
//        assertTrue(System.getenv("SystemRoot").contains(":\\Windows"));
//    }

    /*
        how to run this with property?
        edit configuration ->
        -ea -Dconfig.value=true
     */
    @Test
    @EnabledIfSystemProperty(named = "config.value", matches = "true")
    public void onlyRunIfPropertyIsSet() {
        assertTrue(1==1);
        System.out.println("system property is set");
    }
}
