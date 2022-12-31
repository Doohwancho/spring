package com.tdd.tddTest.mockito.fundamental.mockAndSpy;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpyTest {

//    ---
//    C. Spy
//    spy로 만들어진 객체는 실제 객체의 메서드를 호출한다.



    // @Autowired
    @Spy
    ProductService productService;

    @Test
    void testSpy_스터빙X() {
        Product product = productService.getProduct();

//        assertNull(product.getSerial()); //만약 @Mock productService 이었다면,  product.getSerial()이 null을 반환함
        assertEquals("A001", product.getSerial()); //spy는 실제 객체의 메서드를 호출하기 때문에, "A001"을 반환함
    }

    @Test
    void testSpy_스터빙O() {
        Product productDummy = new Product("B001", "keyboard");

        when(productService.getProduct()).thenReturn(productDummy); //stubbing desired output

        Product product = productService.getProduct();

        assertEquals(productDummy.getSerial(), product.getSerial()); //both "B001", not "A001"

        //Q. 근데 stubbing 쓸거면, 왜 spy 사용함?
        //어짜피 stubbing 쓰면, 내가 desired하는 output 뽑는건 똑같잖아?
        //그럼 mock으로 만들어도 되는거 아닌가?

        //A. spy는 실제 객체의 메서드를 호출하기 때문에, 실제 객체의 메서드를 호출하고 싶을 때 사용한다.
        //ex. 실제 객체의 메서드를 호출하고 싶은데, 그 메서드가 private이거나, final이거나, static이거나, native이거나,
        //    또는 내가 테스트하고 싶은 객체가 아닌데, 그 객체의 메서드를 호출해야 하는 경우 등등
        //    이런 경우에는 spy를 사용한다.

        //그럼 집합으로 표현하면,
        //실제 객체 > spy(partly stub) > mock(full stub)  이겠네?
    }



    @Test
    @DisplayName("spying 테스트")
    public void spyingMockTest(){
        List spyList = spy(ArrayList.class);

        spyList.add("apple");
        //@Mock 객체는 껍데기 뿐이라 .get()호출하면 에러나는 반면, spying 객체는 실제 객체처럼 동작한다.
        assertEquals("apple", spyList.get(0));

        //spying 객체는 when()도 먹히고
        when(spyList.indexOf("stub1")).thenReturn(1);
        doReturn("stub2").when(spyList).get(2); //객체에 2번째 엘레멘트 없으면, .thenReturn()쓰지 말고 doReturn()먼저 쓰고 .when() 더해주면 해결
        assertEquals(1, spyList.indexOf("stub1"));
        assertEquals("stub2", spyList.get(2));

        //spying 객체는 verify()도 먹힌다.
        verify(spyList, never()).get(1);

        /*
            어라? Spy는 Mock과 똑같은데? 왜 씀?

            Mock은 빈 객체에서 내가 원하는 소수 기능에 when().thenX() 지정해서 쓰는거고,
            spy는 역할이 많아서 그 역할을 다 when().thenX()하기 곤란한 데, 소수 기능만 stub해야할 때 쓴다.
         */
    }
}

