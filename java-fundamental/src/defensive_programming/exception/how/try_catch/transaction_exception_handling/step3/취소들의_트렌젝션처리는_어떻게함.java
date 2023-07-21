package defensive_programming.exception.how.try_catch.transaction_exception_handling.step3;

public class 취소들의_트렌젝션처리는_어떻게함 {


    //Q. 취소 트랜젝션을 step2에서 묶어처리하는걸 알겠어.
    //   근데, 취소하는것 도, 실패하면, 이전것들도 취소해야하는데, 이건 어떻게하지?

    void 상품발송(){
        try {
            //step1)
            포장();

            //step2)
            영수증발행();

            //step3)
            발송();
        } catch(포장Exception | 영수증Exception | 발송Exception e){
            if(e instanceof 포장Exception){
                포장취소();
            } else if(e instanceof 영수증Exception){
                try {
                    영수증발행취소(); //만약 실패했고
                    포장취소(); //성공했다면?
                } catch(영수증발행취소Exception | 포장취소Exception e1){
                    e1.printStackTrace();
                    if(e1 instanceof 영수증발행취소Exception){
                        //영수증발행취소()가 실행 후 실패했고, 포장취소는 아직 실행이 안된 상태이다.
                        //어떻게 해야할까?
                        //먼저 포장취소();를 돌려보고,
                            //성공하면?
                                //영수증발행취소 로깅은 이미 되어있는데 음..
                                //로깅을 영수증발행취소() 안에서 해버리면, (포장취소() 성공, 근데 영수증발행취소() 실패) 묶어서는 로깅이 안되네?
                                //그러면 각종 취소 메서드에 있던 retry logging을 위로 옮겨야 하나?
                                //retry를 몇번 했습니다 로깅은 안에 남기고, retry해도 안되면 throw e 한 다음에,
                                //이 부분에서 몰아서 로깅하는게 좋겠다.

                                //근데 핵심은 이 3개중에 하나라도 실패하면, 3개 전체 다 주문 취소하는거니까,
                                //step1은 취소됬는데 step2가 있다? -> step3실행 전에, validation check에 step1,2 조건이 충족됬는지 확인하는 로직 추가해야 할 듯?
                            //실패하면?
                                //둘 다 실패했다고 로깅처리 해야겠지?
                                //근데 둘 다 실패했으면, 엄청 큰 문제 아닌가?
                                //step3때 validation check로, step1,2이 성공했는지 체크했어도, logging 봐서, 해당 userId에 log가 찍혀있으면, 취소처리 해야한다.
                    } else if(e1 instanceof 포장취소Exception){
                        //영수증 발행 취소는 성공했는데, 포장취소가 실패한 상태이다.
                        //얘도 위처럼 처리..
                    }
                }
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
        final int MAX_RETRIES = 3;
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                // Attempt to cancel the packaging
//                cancelPackaging();

                // If this operation succeeds, break out of the loop
                break;
            } catch (포장취소Exception e) {
                // If this was our last retry, rethrow the exception
                if (attempt == MAX_RETRIES - 1) {
                    throw e;
                }
                // Otherwise, log the failure and try again
                System.out.println("Failed to cancel packaging, attempt " + (attempt + 1));
            }
        }
    }

    private void 영수증발행취소() {
        final int MAX_RETRIES = 3;
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                // Attempt to cancel the packaging
//                cancelPackaging();

                // If this operation succeeds, break out of the loop
                break;
            } catch (영수증발행취소Exception e) {
                // If this was our last retry, rethrow the exception
                if (attempt == MAX_RETRIES - 1) {
                    throw e;
                }
                // Otherwise, log the failure and try again
                System.out.println("Failed to cancel packaging, attempt " + (attempt + 1));
            }
        }
    }

    private void 발송취소() {
        final int MAX_RETRIES = 3;
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                // Attempt to cancel the packaging
//                cancelPackaging();

                // If this operation succeeds, break out of the loop
                break;
            } catch (발송취소Exception e) {
                // If this was our last retry, rethrow the exception
                if (attempt == MAX_RETRIES - 1) {
                    throw e;
                }
                // Otherwise, log the failure and try again
                System.out.println("Failed to cancel packaging, attempt " + (attempt + 1));
            }
        }
    }

    class 포장Exception extends RuntimeException {
        public 포장Exception(String msg){
            super(msg);
        }
    }
    class 포장취소Exception extends RuntimeException {
        public 포장취소Exception(String msg){
            super(msg);
        }
    }

    class 영수증Exception extends RuntimeException {
        public 영수증Exception(String msg){
            super(msg);
        }
    }
    class 영수증발행취소Exception extends RuntimeException {
        public 영수증발행취소Exception(String msg){
            super(msg);
        }
    }

    class 발송Exception extends RuntimeException {
        public 발송Exception(String msg){
            super(msg);
        }
    }
    class 발송취소Exception extends RuntimeException {
        public 발송취소Exception(String msg){
            super(msg);
        }
    }
}
