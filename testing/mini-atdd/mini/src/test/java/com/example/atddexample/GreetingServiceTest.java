package com.example.atddexample;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class GreetingServiceTest {

    @Mock
    private EmployeeRepository repository;
    private EmployeeService service;

    @BeforeAll
    void beforeAll() {
        this.service = new EmployeeService(repository);
    }

    /**
     * 요구사항
     *
        - 사용자(Employee)는 lastName을 인자로 인사말(greeting)을 요구한다. 시스템은 lastName으로 DB에서 Employee를 찾고
            - 존재하는 경우 "Hello firstName lastName !"을
            - 존재하지 않는 경우 "Who is this lastName you're talking about?"을 반환한다.
     */


    @Test
    @DisplayName("이름을 넣으면 greeting message를 반환한다.")
    void printGreetingMessageTest() {
        //given
        String existingLastName = "ExistingLastName";
        given(repository.findByLastName(existingLastName)).willReturn(Optional.of(new Employee(existingLastName)));

        //when
        String result = service.greeting(existingLastName);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo("Hello firstName "+existingLastName+"!");
    }
    
    @Test 
    @DisplayName("이름으로 직원을 찾을 수 없으면, Exception Message를 반환한다.")
    void printExceptionMessageWhenEmployeeNotFound() {
        //given
        String nonExistingLastName = "nonExistingLastName";
        given(repository.findByLastName(nonExistingLastName)).willReturn(Optional.empty());

        //when
        String result = service.greeting(nonExistingLastName);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo("Who is this lastName you're talking about?");
    }
}
