package io.reflectoring.step2TransactionExceptionHandling;

import org.springframework.transaction.annotation.Transactional;

//TODO - a-7 exception handling in @Transactional
public class Case2Transactional {
    public static void main(String[] args) {
        //Q. 상품발송 안에 세 메서드중 하나라도 실패하면, 모두 실패하게끔 처리하고 싶다면? Exception 처리를 어떻게 해야할까?

        //case2) 하위 모듈에서 throws Exception을 하고, 상위 모듈에서 try-catch를 해서 처리하는 방법
        //장점1: 하위 모듈에 try~catch가 덕지덕지 붙어있지 않아 코드가 깔끔하다.
        //장점2: 포장(), 발송()은 성공했는데, 영수증이 실패했다고 하면, 영수증에 catch에서 포장.undo(), 발송.undo()을 안해도 된다. 부모인 상품발송에 catch 부분에서 이를 처리한다.
        상품발송();
    }

    @Transactional
    private static void 상품발송() {
        try {
            포장();
            영수증발생();
            발송();
        } catch (Exception e) {
            System.out.println("Case2Transactional: 상품발송 실패");
        }
    }

    private static void 발송() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported 발송");
    }

    private static void 영수증발생() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported 영수증발생");
    }

    private static void 포장() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Unsupported 포장");
    }
}
