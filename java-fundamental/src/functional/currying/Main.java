package functional.currying;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Integer b = 2;
    
    //case1)
    private static List<Integer> calculate(List<Integer> list, Integer a){
        return list.stream().map(new Function<Integer, Function<Integer, Function<Integer, Integer>>>() {
            @Override
            public Function<Integer, Function<Integer, Integer>> apply(final Integer x) {
              return new Function<Integer, Function<Integer, Integer>>() {
                @Override
                public Function<Integer, Integer> apply(final Integer y) {
                  return new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer t) {
                      return x + y * t;
                    }
                  };
                }
              };
            }
          }.apply(b).apply(a)).collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(calculate(list, 3)); //[5, 8, 11, 14, 17]
    }
	
    //case2)
    private static List<Integer> calculateWithLamda(List<Integer> list, Integer a){
        return list.stream().map(((Function<Integer, Function<Integer, Function<Integer, Integer>>>)
                x -> y -> t -> x + y * t).apply(b).apply(a)).collect(Collectors.toList());
    }
    
}

/*

---
what is currying?


함수를 반환하는 함수,

what for?
-> **기능이 추가된** 새 함수 반환하려고.



ex. 
var func = function(value){};
var enhanced = currying(func);



근데 그 모양이, 
multiply(a,b,c);를
multiply(a)(b)(c); 이렇게 인자별로 쪼개짐.  


---
case1)


function multiply(a, b){
	return a * b;
}

multiply(2,3); //6





---
case2)
Q. 근데 만약에 이 함수를 '재활용'해서 2배수 값을 반환하는 함수를 만든다면?

function multiplyByTwo(a){
	return multiply(a, 2);
}

multiplyByTwo(3); //6


같은 논리로 3배수, 4배수 함수도 만들 수 있음.

n배수 함수 만드는게 중복되잖아?
이걸 커링 패턴으로 generalize 할 수 있음.




---
case3) currying

function multiplyByX(x){
	return function(a) {
		return multiply(a, x);
	}
}

timesFour = multiplyByX(4);
multiplyByFour = timesFour(2); //8

or,

multiplyByX(2)(4); //8






*/
