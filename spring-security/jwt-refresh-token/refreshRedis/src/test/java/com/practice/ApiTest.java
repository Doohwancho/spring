package com.practice;

import com.practice.util.DatabaseCleanup;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @LocalServerPort
    private int port;

    @BeforeEach
    void beforeEach() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanup.afterPropertiesSet();
        }
        databaseCleanup.execute();

        hook();
    }

    public URI uri(String path) {
        try {
            return new URI(format("http://localhost:%d%s", port, path));
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

    public abstract void hook();
}