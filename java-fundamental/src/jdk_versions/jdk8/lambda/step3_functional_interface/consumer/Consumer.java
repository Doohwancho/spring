package jdk_versions.jdk8.lambda.step3_functional_interface.consumer;

import java.util.Objects;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t); //(T) -> void
    //ex. Consumer<Integer> printInt = (int i) -> System.out.println("" + i);

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}