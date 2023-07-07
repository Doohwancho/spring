package oop.generics.example_abstract_builder;

import java.util.EnumSet;
import java.util.Set;

import java.util.Objects;

public abstract class Pizza{
   final Set<Topping> toppings;

 //클래스네?
   abstract static class Builder<T extends Builder<T>> {  // extends Builder<T>
	   //Q. 왜 그냥 Builder<T>라고 안함?
	   //A. Builder<T>로 해도 되는데, Builder<T extends Builder>로 한 이유는,
	   //Pizza가 최상위 abstract class이고, 얘 '자식'들이 이거 상속받을거라서 범위제한을 아래로 함.
	   // ex. Builder extends Pizza.Builder<Builder> 이런식으로. 
	   
	   //그리고 자식이 class Builder extends Pizza.Builder<Pizza> 처럼 타입에 아무거나 넣어도 컴파일 에러가 안남. 이걸 방지하기 위해 T를 더 자세하게 명시함. 
	   
	   //Q. 왜 그냥 Builder<T extends Builder> 라고 안함?
	   //A. 이래도 에러는 안뜨긴 하는데, Builder가 raw type이니 주의하라고 경고줌.  
	   //raw type이란 name of a generic class or interface without any type arguments. ex. Box<T>
	   //그래서 Builder가 raw type인게 뭐가 문제라는거지?
	   
	   /*
			List<String> good = new ArrayList<>();
			List bad = good;
			// warning: unchecked call to add(E) as a member of the raw type List
			bad.add(1);
			for (String str : good) {
			    System.out.println(str);
			}
	    */
	   //위 코드는 컴파일은 되지만, 코드를 실행하면 java.lang.ClassCastException이 발생한다. 
	   //static type language인 자바에서 동적 타입인 raw type 사용은 최대한 피해야 한다.
	   //ex. List list = new ArrayList<>(); 이런거 반드시 피하자.
	   
      EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class); //initialize empty enum set
      
      public T addTopping(Topping topping) {
         toppings.add(Objects.requireNonNull(topping)); 
         return self();
      }

      abstract Pizza build();

      // 하위 클래스는 이 메서드를 overriding하여 this를 반환하도록 해야 한다.
      protected abstract T self(); //자식 override: @Override protected Builder self()
   }

   Pizza(Builder<?> builder) {
      toppings = builder.toppings.clone();
   }
}
