package functional.monad;

import java.util.function.Function;

class Wrap<T> {

	private final T value;

	private Wrap(T value) {
		this.value = value;
	}

	// case1)
	public static <T> Wrap<T> of(T value) {
		return new Wrap<>(value);
	}

	public <R> Wrap<R> map(Function<T, R> mapper) {
		return Wrap.of(mapper.apply(value));
	}

	// case2)
	public static Wrap<Integer> inc(Integer x) { //inc()을 여러번 functional compose로 적용하고 싶은데, Wrap<Integer>은 . 하고 compose chaining이 안되네? -> 아래에 flatMap 구현 
		return Wrap.of(x + 1);
	}

	// case3)
	public <R> Wrap<R> flatMap(Function<T, Wrap<R>> mapper) {  //함수를 인자로 넘겨받아서, 인자로 넘겨받은 함수를 적용. inc()를 적용시키되, 다시 Wrap으로 감싸지 않는 방법 -> 따라서 여러번 적용 가능  
		return mapper.apply(value);
	}
	
	//사실 map도 flatmap처럼 구현 가능하다. 
    public <R> Wrap<R> customMapUsingFlatMap(Function<T,R> mapper) { //flatmap을 이용한 map구현. .andThen()은 함수 A를 다른 함수 B와 조합해줌. mapper의 결과값을 .andThen(Wrap::of)의 인자값으로 전달해줌  
        return flatMap(mapper.andThen(Wrap::of)); //of는 Wrap으로 감싸고, flatMap()은 Wrap으로 감싸진걸 풀 필요 없이 여러 함수형 함수를 compose가능. 
    }
}