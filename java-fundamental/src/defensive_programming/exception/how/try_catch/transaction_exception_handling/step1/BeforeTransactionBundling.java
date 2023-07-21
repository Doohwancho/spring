package defensive_programming.exception.how.try_catch.transaction_exception_handling.step1;

public class BeforeTransactionBundling {

    //Q. 아래 3 메서드 중, 하나라도 실패하면, 모두 실패하게끔 처리하고 싶다면?
    //문제점: 아래처럼 만들면, 영수증발생() 중 에러나면, 영수증발생취소() 만 실행되고, 포장취소()가 실행 안됨
    void 상품발송(){
        포장();
        영수증발행();
        발송();
    }

    private void 포장() {
        try {

        } catch(Exception e){
            포장취소();
        }
    }

    private void 영수증발행() {
        try {

        } catch(Exception e){
            영수증발행취소();
        }
    }

    private void 발송() {
        try {

        } catch(Exception e){
            발송취소();
        }
    }

    private void 포장취소() {
    }

    private void 영수증발행취소() {

    }

    private void 발송취소() {
    }

}
