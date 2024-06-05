package _2_oop._1_상태_데이터의_캡슐화.solid._1_single_responsibility;

public class BookPrinter {

    /** solution */
    //Book에 모든 메서드 다 때려박지 말고,
    //Book은 VO로 쓰고, Book을 활용하는 메서드는 별개에 객체에서 생성, 보관하자.



    // method1 for Book.java
    void printTextToConsole(String text){
        //our code for formatting and printing the text
    }

    // method2 for Book.java
    void printTextToAnotherMedium(String text){
        // code for writing to any other location..
    }
}