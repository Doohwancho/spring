package _2_oop._1_상태_데이터의_캡슐화.solid._1_single_responsibility;

public class Book {

    private String name;
    private String author;
    private String text;

    //constructor, getters and setters

    /** below 3 method violotes single-responsibility-principle */
//    // method1
//    public String replaceWordInText(String word, String replacementWord){
//        return text.replaceAll(word, replacementWord);
//    }
//
//    // method2
//    public boolean isWordInText(String word){
//        return text.contains(word);
//    }
//
//    // method3
//    void printTextToConsole(){
//        // our code for formatting and printing the text
//    }
}