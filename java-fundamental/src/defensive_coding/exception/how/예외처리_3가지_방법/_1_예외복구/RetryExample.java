package defensive_coding.exception.how.예외처리_3가지_방법._1_예외복구;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/*
    case1) 예외 복구

    - 어떻게든 정상 상태로 돌려놓는 것이다.
    - 예외복구의 핵심은 예외 발생해도 app이 정상작동 한다는 것
    - ex) retry - 몇 초 뒤 다시 시도 혹은 다른 방법으로 시도하라고 안내한다.
 */

public class RetryExample {
    void readFile(){
        try {
            // 예외가 발생할 수 있는 코드
            File file = new File("file.txt");
            FileReader fileReader = new FileReader(file);
            return; //작업 성공시 리
        } catch (FileNotFoundException e) {
            // 예외 복구 코드
            System.out.println("file.txt 파일이 존재하지 않습니다. 기본 설정을 사용합니다.");

            //로그 출력. 정해진 시간만큼 대기
        } finally {
            //리소스 반납 및 정 작업
        }
        throw new RetryFailException("최대 재시도 횟수가 넘어가면 직접 예외 발생"); //최대 재시도 횟수가 넘어가면 직접 예외 발생
    }

    private class RetryFailException extends RuntimeException{
        public RetryFailException(String msg) {
            super(msg);
        }
    }
}
