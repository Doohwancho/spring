package com.example.atddexample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class GreetingServiceTest {
    Logger log = Logger.getLogger("GreetingSerivceTest");

    private EmployeeRepository repository;
    private EmployeeService service;

    @BeforeAll
    void beforeAll() {
        this.repository = new EmployeeRepository();
        this.service = new EmployeeService(repository);
    }


    /**
     * 요구사항
     *
        - 사용자(Employee)는 lastName을 인자로 인사말(greeting)을 요구한다. 시스템은 lastName으로 DB에서 Employee를 찾고
            - 존재하는 경우 "Hello firstName lastName !"을
            - 존재하지 않는 경우 "Who is this lastName you're talking about?"을 반환한다.
     */

    /**
     * lets break it down
     *
     *
     * 1. Employee employee = new Employee();
     * 2. employee.greeting(lastName); //Q1 - domain.greeting()? service.greeting()?
     * 3. service.greeting(lastName);
     * 4. Employee target = repository.findByLastName(lastName);
     * 5. if(target) { return "Hello firstName" + lastName + "!"; }
     * 6. if(!target) { return "Who is this lastName you're talking about?"; }
     *
     */

    @Test 
    @DisplayName("이름을 넣으면 greeting message를 반환한다.")
    void printGreetingMessageTest() {
        //given
        Employee employee = new Employee("kim");

        String lastName = "cho";
        Employee targetEmployee = new Employee(lastName);
        repository.add(targetEmployee);

        //when
        /**
         * question1
         *
         * domain.greeting();을 해야하나, service.greeting();을 해야하나?
         *
         * case1) domain.greeting();
         *
         * domain 내부에 greeting() 메서드가 생긴다.
         * 근데 서비스를 호출해야 해서, 서비스에 대한 의존성이 생긴다.
         * 이 떄, 의존성의 방향이 service -> domain 일까? 아니면 domain -> service 일까?
         * 만약 A -> B 일 때, B가 바뀌면 A도 바뀐다고 가정하면, A는 B에 의존한다고 할 수 있다.
         * 그렇다면 service와 domain 중에서, 어느쪽이 바뀌었을 때, 다른 쪽도 바뀌어야 하나?
         *
         * domain객체 내부에서 Service객체를 주입받아, service.greeting();을 하는 경우엔,
         * domain -> service 의존관계 아닌가? service가 바뀌면(클래스 이름이라던지, 메서드 명이라던지), domain도 바뀌어야 하니까.
         *
         * Q. 근데 의존관계를 이렇게 해버리면, 뭐가 문제지?
         *
         * A. domain이 서비스를 의존하니까, 서비스가 바뀌면, 도메인도 수정해야 함.
         * 그리고 Employee를 만들 때, 서비스를 생성자 주입으로 받는데,
         * 이거 좀 오바임.
         * Employee employee = new Employee("name"); 이렇게 만들지,
         * 아니 어느 미친 사람이
         * Employee employee = new Employee(new EmployeeService(), "name"); 이런식으로 만들겠냐고
         *
         *
         * Q. clean architecture, hexagonal architecture에서는, 비즈니스 로직을 domain 안에 넣는다며?
         * 그러면 service, repository를 필연적으로 주입 받아야 하는거 아니야?
         *
         * 아니면, db가 필요없는 메서드만 킵하고, 나머지는 서비스 레이어에서 처리하나?
         * 이것도 말이 안되는데?
         *
         *
         * case2) service.greeting();
         *
         * domain <- service 로 의존성을 바꿔보자.
         *
         */
        String result = service.greeting(lastName);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo("Hello firstName "+lastName+"!");
        //Q2. 테스트 분기처리 어떻게 하지?
        // 1. 모든 경우에 수를 여러 테스트로 만든다?
        //  장점: 명확
        //  단점: 테스트 수가 너무 많아진다.
        // 2. parameterizedTest 였나? 그걸로 input 넣고, if else 분기처리 하면 되나?
        //  장점: 테스트 수가 적어진다
        //  단점: 하나의 메서드에서 여러 테스트를 하게된다 -> 불명확
    }
    
    @Test 
    @DisplayName("이름으로 직원을 찾을 수 없으면, Exception Message를 반환한다.")
    void printExceptionMessageWhenEmployeeNotFound() {
        //given
        Employee employee = new Employee("kim");
        String lastName = "cho";

        //when
        String result = service.greeting(lastName);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo("Who is this lastName you're talking about?");
    }
    
    /**
     * case1) domain -> service 의존관계 (anti-pattern)
     */
//    private class Employee {
//
//        Long id;
//        String lastName;
//
//        private EmployeeService service;
//
//       public Employee(EmployeeService service){
//           this.service = service;
//       }
//
//        public Employee(Long id, String lastName){
//           this.id = id;
//           this.lastName = lastName;
//        }
//
//        public String greeting(String lastName) {
//            return service.greeting(lastName);
//        }
//    }
//
//    private class EmployeeService {
//        EmployeeRepository repository;
//
//        public EmployeeService(EmployeeRepository repository){
//            this.repository = repository;
//        }
//
//        public String greeting(String lastName) {
//            Employee target = repository.findByLastName(lastName);
//            if(target == null){
//                return "Who is this lastName you're talking about?";
//            }
//            return "Hello firstName " + lastName + "!";
//        }
//    }

//    private class EmployeeRepository {
//        Map<Long, String> database = new HashMap<>();
//        Long sequence = 0L;
//
//        public Employee findByLastName(String lastName) {
//            for (Map.Entry<Long, String> entry : database.entrySet()) {
//                if (entry.getValue().equals(lastName)) {
//                    return new Employee(entry.getKey(), entry.getValue());
//                }
//            }
//            return null;
//        }
//
//        public void add(Employee targetEmployee) {
//            database.put(targetEmployee.id, targetEmployee.lastName);
//        }
//    }


    /**
     * case2) service -> domain 의존관계
     */
    private class Employee {
        Long id;
        String lastName;

        public Employee(String lastName) {
            this.lastName = lastName;
        }
    }


    private class EmployeeService {
        EmployeeRepository repository;

        public EmployeeService(EmployeeRepository repository){
            this.repository = repository;
        }

        public String greeting(String lastName) {
            Employee target = repository.findByLastName(lastName);
            if(target == null){
                return "Who is this lastName you're talking about?";
            }
            return "Hello firstName " + lastName + "!";
        }
    }


    private class EmployeeRepository {
        Map<Long, String> database = new HashMap<>();
        Long sequence = 0L;

        public Employee findByLastName(String lastName) {
            for (Map.Entry<Long, String> entry : database.entrySet()) {
                if (entry.getValue().equals(lastName)) {
                    return new Employee(entry.getValue());
                }
            }
            return null;
        }

        public void add(Employee targetEmployee) {
            database.put(++sequence, targetEmployee.lastName);
        }
    }

}
