package __2.객체_생성과_파괴.__2.__3_빌더_패턴.abstract_class_builder;

import java.util.EnumSet;
import java.util.Set;
import java.util.Objects;

public abstract class Pizza{
   final Set<Topping> toppings;

   // 추상 클래스는 추상 Builder를 가진다. 서브 클래스에서 이를 구체 Builder로 구현한다.
   abstract static class Builder<T extends Builder<T>> {
      EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class); //initialize empty enum set
      
      public T addTopping(Topping topping) {
         toppings.add(Objects.requireNonNull(topping)); 
         return self();
      }

      abstract Pizza build();

      // 하위 클래스는 이 메서드를 overriding하여 this를 반환하도록 해야 한다.
      protected abstract T self();
   }

   Pizza(Builder<?> builder) {
      toppings = builder.toppings.clone();
   }
}
