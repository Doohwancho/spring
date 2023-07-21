package defensive_programming.exception.how.예외처리_3가지_방법._3_예외처리회피.throws로_책임넘기기._2_throws;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    case2) 에러 일어나면, catch해서 throws해서 callerMethod()에게 넘기는 방법

    pros
    1. separation of concerns
        - read file 실패시, 에러핸들링이 readFile() 안이 적합하지 않을 수도 있음.
        - 예를들어, 아래 처럼 catch에 read file 실패시, 다른 파일 읽으라고 처리하고 싶다면? callerMethod()로 throws Error 해야 함.
        - 다른 throws ~ catch하기 좋은 예제들
            1. asking the user for a different file
            2. retrying with a different file
            3. logging the error
            4. even terminating the program
    2. 에러가 난 context를 잡을 수 있음.
        - User module에서 readFile() 불러서 에러났으면, throws~catch 잡아서 user module에서 어떤 컨텍스트에서 에러났다고,
            context 관련 정보를 error stack strace에 포함시킬 수 있음.
    3. 다른 callerMethod() 모듈에서 다른식으로 error handling 할 수 있다.
        - user module에서 readFile()해서 에러났으면, 반드시 열어야 하니까, readFile(backupFile); 재시도 할 수 있는데,
        - 장바구니 모듈에서 readFile() 해서 에러났는데, 반드시 재시도 안해도 되는 경우, 그저 logging에 적고 넘길 수 있다.
    4. catch에 complex business logic + logging 들어가 있는 경우
        - 이걸 case1처럼 no throws 해버리면, 코드가 너무 장황해진달까?
        - 그리고 user.callerMethod()에서 요구하는 비즈니스 요구사항과, cart.callerMethod()에서 요구하는 비즈니스 요구사항은 다를 수 있으니께..

    cons
    1. debugging시에 call stack 늘어나서, 에러 catch해서 처리한 곳 보고 엥? 어디가 에러? 할 수 있음.
    2. 무분별한 throws는 지향하라!
        만약에 error나 checked Exception이라, throw 해도 상급자가 처리 못한다?
            -> 그냥 불필요한 throws Exception, try~catch 덕지덕지 붙이지 말고, crash난 자리에 crash 시켜야,
        1. 코드도 깔끔해지고,
        2. crash난 부분이 어딘지 확인도 용이
 */
public class Case2Throws {
    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            System.out.println(data);
        }
        scanner.close();
    }

    public void callerMethod1() {
        try {
            readFile("non-existing-file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in the caller method.");
            e.printStackTrace();

            //complex business logic 1

            try {
                readFile("another-existing-file.txt");
            } catch (FileNotFoundException e2){
                System.out.println("another existing file도 없어요!");
                e2.printStackTrace();
            }
        }
    }

    public void callerMethod2() {
        try {
            readFile("non-existing-file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in the caller method.");
            e.printStackTrace();

            //complex business logic 2

            try {
                readFile("another-existing-file.txt");
            } catch (FileNotFoundException e2){
                System.out.println("another existing file도 없어요!");
                e2.printStackTrace();
            }
        }
    }
}
