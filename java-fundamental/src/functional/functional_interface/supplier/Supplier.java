package functional.functional_interface.supplier;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
