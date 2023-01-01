package com.tdd.tddTest.mockito.testDouble.stub.whenLater;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class DoThrowTest {

    @Mock
    Posts post;

    @Mock
    User user;

    @Test
    @DisplayName("doThrow() test")
    public void doThrowTest(){
        Posts post = mock(Posts.class);
        assertTrue(post != null);

        doThrow(IllegalStateException.class).when(post).getCreatedDate(); //new RuntimeException()

        assertThrows(IllegalStateException.class, () -> {
            post.getCreatedDate();
        });
    }
}
