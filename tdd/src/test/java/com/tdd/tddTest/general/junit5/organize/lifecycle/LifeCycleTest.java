package com.tdd.tddTest.general.junit5.organize.lifecycle;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //LifeCycle 메서드 쓰려면 필요
public class LifeCycleTest {

    @BeforeAll
    public void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void simpleTest1(){
        System.out.println("Simple Test1");
    }

    @Test
    public void simpleTest2(){
        System.out.println("Simple Test2");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("@AfterAll");
    }
}

/*

@BeforeAll

@BeforeEach
Simple Test1
@AfterEach

@BeforeEach
Simple Test2
@AfterEach

@AfterAll

 */