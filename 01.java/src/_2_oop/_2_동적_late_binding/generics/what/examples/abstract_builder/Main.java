package _2_oop._2_동적_late_binding.generics.what.examples.abstract_builder;

import static _2_oop._2_동적_late_binding.generics.what.examples.abstract_builder.Size.SMALL;
import static _2_oop._2_동적_late_binding.generics.what.examples.abstract_builder.Topping.HAM;
import static _2_oop._2_동적_late_binding.generics.what.examples.abstract_builder.Topping.ONION;
import static _2_oop._2_동적_late_binding.generics.what.examples.abstract_builder.Topping.SAUSAGE;



public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE)
                .addTopping(ONION)
                .build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();
    }
}
