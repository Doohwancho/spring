package com.example.productorderservice.playing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 단축키 궁금증
 *
 * 1. 파라미터에서 생성자 어떻게 만듬?
 * 2. 리펙토링 단축키? -> 코드 바깥으로 빼는법
 *
 */

@SpringBootTest
public class FunServiceTest {

    private FunPort funPort;
    private FunService funService;
    private FunRepository funRepository;

    @BeforeEach
    void setup() {
        funRepository = new FunRepository();
        funPort = new FunPortAdapter(funRepository);
        funService = new FunService(funPort);
    }

    @Test
    void 재미등록() {
        //given
        String name = "상품명";
        int price = 1000;
        FunPolicy funPolicy = FunPolicy.NONE;
        AddFunRequest request = new AddFunRequest("상품명", 1000, funPolicy);//파라미터에서 생성자 어떻게 만듬?

        //when
        funService.addProduct(request);
    }
}