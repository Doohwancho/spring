package com.tdd.tddTest.junit5.organize.nested;

import com.tdd.tddTest.domain.posts.Posts;
import org.junit.jupiter.api.*;

@DisplayName("Nested: given parent")
@TestClassOrder(ClassOrderer.OrderAnnotation.class) //nested class가 여러개 있으면, 순서 정해준다.
public class NestedTest {

    Posts parentPost = new Posts().builder()
            .title("parent")
            .content("content")
            .author("parent")
            .build();

    @BeforeEach
    public void parentBeforeEachTest(){
        System.out.println("parent Before Each");
    }
    @AfterEach
    public void parentAfterEachTest(){
        System.out.println("parent After Each");
    }

    @Nested
    @Order(1)
    @DisplayName("inner1: when a child is created")
    class innerClass1 {

        Posts childPost = new Posts().builder()
                .title("child")
                .content("content")
                .author("child")
                .build();

        @BeforeEach
        public void childBeforeEachTest(){
            System.out.println("    child1 Before Each");
        }
        @AfterEach
        public void childAfterEachTest(){
            System.out.println("    child1 After Each");
        }

        @Test
        @DisplayName("compare parent's content with child's content")
        public void Test1(){
            System.out.println("        child1 test1");

            Assertions.assertTrue(parentPost.getContent().equals(childPost.getContent()));
        }

        @Test
        public void Test2(){
            System.out.println("        child1 test2");
        }
    }

    @Nested
    @Order(2)
    @DisplayName("inner2: when a child is created")
    class innerClass2 {

        Posts childPost = new Posts().builder()
                .title("child")
                .content("content")
                .author("child")
                .build();

        @BeforeEach
        public void childBeforeEachTest(){
            System.out.println("    child2 Before Each");
        }
        @AfterEach
        public void childAfterEachTest(){
            System.out.println("    child2 After Each");
        }

        @Test
        @DisplayName("compare parent's content with child's content")
        public void Test1(){
            System.out.println("        child2 test1");

            Assertions.assertTrue(parentPost.getContent().equals(childPost.getContent()));
        }

        @Test
        public void Test2(){
            System.out.println("        child2 test2");
        }
    }
}

/*

---
intellij test screen

Nested: given parent
    inner1: when a child is created
        compare parent's content with child's content
        Test2()
    inner2: when a child is created
        compare parent's content with child's content
        Test2()


---
console.log


parent Before Each
    child1 Before Each
        child1 test1
    child1 After Each
parent After Each

parent Before Each
    child1 Before Each
        child1 test2
    child1 After Each
parent After Each


parent Before Each
    child2 Before Each
        child2 test1
    child2 After Each
parent After Each

parent Before Each
    child2 Before Each
        child2 test2
    child2 After Each
parent After Each

 */