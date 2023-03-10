package io.reflectoring.step2TransactionExceptionHandling;

import org.springframework.transaction.annotation.Transactional;


//TODO - a-7. exception handling in @Transactional
public class Case1Transactional {
    public static void main(String[] args) {
        //Q. 상품발송 안에 세 메서드중 하나라도 실패하면, 모두 실패하게끔 처리하고 싶다면? Exception 처리를 어떻게 해야할까?

        //case1) 각 메서드 안에 try-catch를 넣어서 처리하는 방법
        //단점1: 코드가 길어지고 난잡함
        //단점2: 만약 포장(), 발송()은 성공했는데, 영수증 발생이 fail했다면? -> 영수증 발생에 catch에서 포장.undo(), 발송.undo() 한다? -> 개오바
        상품발송();
    }

    private static void 상품발송() {
        포장();
        영수증발생();
        발송();
    }

    private static void 발송() {
        try {
            throw new UnsupportedOperationException("Unsupported 발송");
        } catch (Exception e) {
            System.out.println("Case1Transactional: 발송 실패");
        }
    }

    private static void 영수증발생() {
        try {
            throw new UnsupportedOperationException("Unsupported 영수증발생");
        } catch (Exception e) {
            System.out.println("Case1Transactional: 영수증발생 실패");
        }
    }

    private static void 포장() {
        try {
            throw new UnsupportedOperationException("Unsupported 포장");
        } catch (Exception e) {
            System.out.println("Case1Transactional: 포장 실패");
        }
    }
}
