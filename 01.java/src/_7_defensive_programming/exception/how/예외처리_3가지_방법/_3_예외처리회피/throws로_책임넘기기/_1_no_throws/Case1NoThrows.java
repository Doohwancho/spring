package _7_defensive_programming.exception.how.예외처리_3가지_방법._3_예외처리회피.throws로_책임넘기기._1_no_throws;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    case1) exception handling을 에러 일어난 장소에서 하는 방법

    pros
    1. readFile()은 파일 읽는 책임만 있지, 에러 핸들링의 책임은 없다고 생각해, 다른 객체(caller)에게 에러 핸들링 전담해서 위임 가능
    2. debugging시, 에러가 일어난 곳이랑, catch한 곳이랑 멀 수록, 원래 어디서 에러났는지 파악하기 어렵다.
        - exception 여기 있으면 불필요한 call stack 여러개 보고 안헤깔려도 된다.
 */

public class Case1NoThrows {
    public void readFile() {
        try {
            File file = new File("file.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
