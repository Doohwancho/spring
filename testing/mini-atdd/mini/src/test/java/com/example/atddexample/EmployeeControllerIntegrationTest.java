package com.example.atddexample;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = EmployeeApplication.class
)
public class EmployeeControllerIntegrationTest {

    @Autowired
    EmployeeRepository repository;

    RequestSpecification basicRequest;

    @BeforeEach
    void setUp() {
        basicRequest = given()
                .baseUri("http://localhost")
                .port(8080);

        repository.save(new Employee("existingName"));
    }

    @AfterEach
    void cleanUp(){
        repository.deleteAll();
    }

    @Test 
    void shouldReturnDefaultMessageWhenLastNameNotFound() {
        String nonExistingLastName = "nonExistingName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        given().spec(basicRequest).basePath("/api/hello/" + nonExistingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(is(expectedMessage));
    }
    
    @Test 
    void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "existingName";
        String expectedMessage = "Hello "+existingLastName+"!";

        given().spec(basicRequest).basePath("/api/hello/" + existingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(is(expectedMessage));
    }
}
