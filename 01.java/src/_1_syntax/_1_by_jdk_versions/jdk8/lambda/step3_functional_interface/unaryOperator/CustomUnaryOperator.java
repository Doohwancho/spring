package _1_syntax._1_by_jdk_versions.jdk8.lambda.step3_functional_interface.unaryOperator;

@FunctionalInterface
public interface CustomUnaryOperator<T> {
    T apply(T t);
    //ex. UnaryOperator<Integer, Integer> sum = (int i) -> i + i;
}
