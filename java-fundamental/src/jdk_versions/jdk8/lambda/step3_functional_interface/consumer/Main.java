package jdk_versions.jdk8.lambda.step3_functional_interface.consumer;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Consumer<List<Integer>> numberConsumer = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) * list.get(i));
            }
        };
        Consumer<List<Integer>> printConsumer = list -> {
            for (Integer num : list) {
                System.out.println(num);
            }
        };
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numberConsumer.andThen(printConsumer).accept(numbers);
    }
}

/*

---
what is consumer?


Consumer<T> : <T> -> Void

list 전용 함수형 인터페이스인가보네 




*/