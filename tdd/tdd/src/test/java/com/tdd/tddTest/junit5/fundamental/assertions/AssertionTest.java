package com.tdd.tddTest.junit5.fundamental.assertions;
import com.tdd.tddTest.domain.posts.Posts;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class AssertionTest {

    @Test
    public void assertionTest(){

        Posts post = new Posts().builder()
                .title("title")
                .content("content")
                .author("author")
                .build();

        assertTrue(post.getTitle().equals("title"));
        assertFalse(!post.getTitle().equals("title"));

        assertEquals(new Integer(1), new Integer(1)); //두 객체의 값이 같은가?
//        assertSame(new Integer(1), new Integer(1)); //두 객체가 같은가?


        assertAll(
                () -> assertTrue(1+1 == 2),
                () -> assertTrue(2+2 == 4), //n개 에러나도 모든 에러메시지 한번에 볼 수 있다
                () -> assertTrue(3+3 == 6)
        );

        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(10)); //타임아웃 준수
//        assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(100)); //타임아웃 초과
    }
}
