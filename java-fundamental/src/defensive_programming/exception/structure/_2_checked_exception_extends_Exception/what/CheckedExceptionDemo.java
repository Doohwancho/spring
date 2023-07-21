package defensive_programming.exception.structure._2_checked_exception_extends_Exception.what;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
    1. checked Exception의 대부분 catch로 에러 복구 처리를 해주어야 하는 경우가 많다. (ex. db connection error, sql exception, etc)

    - example of checked exception in java
        1. IOException
            - FileNotFoundException
        2. ClassNotFoundException
        3. SQLException
        4. InterruptedException

 */
public class CheckedExceptionDemo {
    public static void main(String[] args) {
        try {
            File file = new File("nonexistentfile.txt");
            FileReader fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            // FileNotFoundException is a checked exception
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
