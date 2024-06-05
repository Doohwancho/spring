package com.kata.orderinhexagonal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AppTests {
    @Test
    public void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

}
