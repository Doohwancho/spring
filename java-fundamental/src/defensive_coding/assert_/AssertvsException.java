package defensive_coding.assert_;

import java.io.IOException;

public class AssertvsException {

    //case1) assert
    //이 기능이 동작 안하면 프로그램 차제가 실행이 안되야 할 때 assert를 쓴다. 에러나면 프로그램 꺼져야 한다.
    public void process(int value) {
        assert value >= 0 : "Value must be non-negative"; //If the value is negative, this indicates a bug in the code, and an AssertionError is thrown.

        // Rest of the method...
    }


    //case2) exception
    //에러나도 retry나 logging등 graceful handling 할 때쓴다.
    public void process(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        // Rest of the method...
    }

    public void callerMethod() {
        try {
            process("a");
        } catch(IOException e){
            //재시도

            //logging
            e.printStackTrace();
        }
    }
}
