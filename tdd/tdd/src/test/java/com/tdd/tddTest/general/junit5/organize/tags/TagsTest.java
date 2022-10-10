package com.tdd.tddTest.general.junit5.organize.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagsTest {
    @Test
    @Tag("slow")
    public void slowTest1(){
        System.out.println("slow test 1");
    }

    @Test
    @Tag("slow")
    public void slowTest2(){
        System.out.println("slow test 2");
    }

    @Test
    @Tag("fast")
    public void fastTest1(){
        System.out.println("fast test 1");
    }

    @Test
    @Tag("fast")
    public void fastest1(){
        System.out.println("fast test 1");
    }
}

/*

1. edit configuration
2. test by class -> test by tags, give parameter "fast"
3. test only runs on test methods with "fast" tags

 */