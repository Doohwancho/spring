package functional.theory.first_class_function;

public class FirstClassFunctionTest {
	@FunctionalInterface
	interface Calculator<X, Y> {    
	   public X compute(X a, Y b);
	}
	
	   public static void main(String[] args) {               
	      //Assign a function to a variable
	      Calculator<Integer, Integer> calculator = (a,b) -> a * b; //함수를 변수에 할당했네? -> 일급함수 조건 1 충족 

	      //call a function using function variable
	      System.out.println(calculator.compute(2, 3)); //6

	      //Pass the function as a parameter
	      printResult(calculator, 2, 3); //6   ---- 함수를 인자에 할당했네? -> 일급함수 -> 일급함수 조건 2 충족 

	      //Get the function as a return result
	      Calculator<Integer, Integer> calculator1 = getCalculator(); //함수를 return할 수 있네? -> 일급함수 -> 일급함수 조건3 충족 
	      System.out.println(calculator1.compute(2, 3)); //6
	   }

	   //Function as a parameter
	   static void printResult(Calculator<Integer, Integer> calculator, Integer a, Integer b){
	      System.out.println(calculator.compute(a, b));
	   }

	   //Function as return value
	   static Calculator<Integer, Integer> getCalculator(){
	      Calculator<Integer, Integer> calculator = (a,b) -> a * b;
	      return calculator;
	   }
}

/*

---
what is first class function?

3가지 조건이 충족하면, 일급함수

1. 함수를 변수에 할당 가능해야 함. 
2. 함수를 인자에 할당 가능해야 함.
3. 함수를 return문에 반환 가능해야 함.




*/