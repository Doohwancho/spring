package jdk_versions.jdk8.lambda.step3_functional_interface.supplier;

@FunctionalInterface
public interface Supplier<T> {
    T get(); //() -> T
    //ex. Supplier<People> makeObject = () -> new People();
}
