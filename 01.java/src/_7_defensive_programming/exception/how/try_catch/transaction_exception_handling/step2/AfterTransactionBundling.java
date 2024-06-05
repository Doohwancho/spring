package _7_defensive_programming.exception.how.try_catch.transaction_exception_handling.step2;

public class AfterTransactionBundling {


    //Q. 아래 3 메서드 중, 하나라도 실패하면, 모두 실패하게끔 처리하고 싶다면?
    //A. 아래처럼 묶어서 처리하면 됨

    void 상품발송(){
        try {
            포장();
            영수증발행();
            발송();
        } catch(포장Exception | 영수증Exception | 발송Exception e){
            if(e instanceof 포장Exception){
                포장취소();
            } else if(e instanceof 영수증Exception){
                영수증발행취소();
                포장취소();
            } else if(e instanceof 발송Exception){
                발송취소();
                영수증발행취소();
                포장취소();
            }
        }
    }

    private void 포장() throws 포장Exception{
    }

    private void 영수증발행() throws 영수증Exception{

    }

    private void 발송() throws 발송Exception{

    }

    private void 포장취소() {
    }

    private void 영수증발행취소() {

    }

    private void 발송취소() {
    }

    class 포장Exception extends RuntimeException {
        public 포장Exception(String msg){
            super(msg);
        }
    }
    class 영수증Exception extends RuntimeException {
        public 영수증Exception(String msg){
            super(msg);
        }
    }
    class 발송Exception extends RuntimeException {
        public 발송Exception(String msg){
            super(msg);
        }
    }
}
