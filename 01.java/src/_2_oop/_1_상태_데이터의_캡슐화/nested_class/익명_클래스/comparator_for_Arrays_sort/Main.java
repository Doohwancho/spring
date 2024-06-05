package _2_oop._1_상태_데이터의_캡슐화.nested_class.익명_클래스.comparator_for_Arrays_sort;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String[] words = {"apple", "Banana", "Cherry", "date"};
        
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        
        System.out.println(Arrays.toString(words));
    }
}
