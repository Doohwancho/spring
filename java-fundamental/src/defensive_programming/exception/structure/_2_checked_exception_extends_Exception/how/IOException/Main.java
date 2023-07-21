package defensive_programming.exception.structure._2_checked_exception_extends_Exception.how.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close(); //반드시 finally로 닫아주자.
                } catch (IOException e) { //.close()도 catch처리 해주자.
                    System.out.println("An error occurred while closing the file reader.");
                    e.printStackTrace();
                }
            }
        }
    }
}

