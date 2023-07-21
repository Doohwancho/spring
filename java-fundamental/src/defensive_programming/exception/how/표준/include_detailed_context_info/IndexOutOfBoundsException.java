package defensive_programming.exception.how.표준.include_detailed_context_info;

public class IndexOutOfBoundsException extends RuntimeException {
    
    private int ERR_CODE = 100; //default error code. 프로젝트 전에 에러 코드 부터 협의 끝나고 시작하자.
    private int index;
    private int lowerBound;
    private int upperBound;
    
    /**
     * IndexOutOfBoundsException을 생성한다.
     *
     * @param msg 에러난 컨텍스트 정보
     * @param lowerBound 인덱스의 최솟값
     * @param upperBound 인덱스의 최댓값 + 1
     * @param index 인덱스의 실젯값
     */
    public IndexOutOfBoundsException(String msg, int lowerBound, int upperBound, int index) { //context관련 정보를 받아서,
        super(String.format("최솟값: %d, 최댓값: %d, 인덱스: %d", lowerBound, upperBound, index
        )+"context: " + msg);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }

    public IndexOutOfBoundsException(String msg) {
        super(msg);
    }

    public int getErrorCode(){
        return ERR_CODE;
    }

}
