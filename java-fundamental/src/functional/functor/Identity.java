package functional.functor;

import java.util.function.Function;

class Identity <T> implements Functor<T, Identity<?>> { //1. Identity<String>이면, 저 ?가  String이게 됨. 
    private final T value;
  
    Identity(T value) { this.value = value; }
  
    public <R> Identity<R> map(Function<T,R> f) {
        final R result = f.apply(value);
        return new Identity<>(result);
    }
}