package functional.optional.scratch;

import java.util.function.Function;

interface Functor<T, F extends Functor<?,?>> {
    <R> F map(Function<T,R> f);
}