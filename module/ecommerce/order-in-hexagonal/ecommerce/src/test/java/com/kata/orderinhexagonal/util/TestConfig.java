package com.kata.orderinhexagonal.util;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestConfig {

  @Autowired
  private DatabaseCleanup databaseCleanup;

  @BeforeEach
  void setUp() {
    databaseCleanup.afterPropertiesSet();
    databaseCleanup.execute();
  }
}
