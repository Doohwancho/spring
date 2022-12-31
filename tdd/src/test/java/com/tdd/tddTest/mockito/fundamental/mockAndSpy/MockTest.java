package com.tdd.tddTest.mockito.fundamental.mockAndSpy;

import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MockTest {

    /*
        ---
        A. what is Mock Object?

        OOP에서 테스트 하고 싶은 Object A가 있고, 그 Object가 의존하는 다른 Object B가 있다고 치자.
        Object A를 테스트 하기 위해서는 Object B가 필요하다. 하지만 Object B는 테스트 하고 싶은 Object A가 아니기 때문에
        이러한 Object A와 Object B의 의존관계를 해결하기 위해 B를 Mock Object를 사용하여 Object A에 주입해준다.

        장점: mock 객체를 만들면 테스트 시간도 줄이면서 불필요한 리소스 소비를 막고 객체의 행동까지 테스트하는 개발자 마음대로 조정할 수 있다.

        ex.
        Service A가 있고, Service A는 Repository B를 의존한다고 치자.
        Service A를 테스트 하기 위해서는 Repository B가 필요하다. 하지만 Repository B는 테스트 하고 싶은 객체가 아닐 뿐더러,
        Repository B는 DB와 연결되어 있기 때문에 테스트 하기 어렵다.
        DB IO가 부하도 걸리고 시간도 걸리기 때문.
        이러한 의존관계를 해결하기 위해 Repository B를 Mock Object를 사용하여 Service A에 주입해준다.

*/
}
