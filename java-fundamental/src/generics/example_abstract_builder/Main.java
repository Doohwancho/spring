package generics.example_abstract_builder;

import static generics.example_abstract_builder.Size.SMALL;
import static generics.example_abstract_builder.Topping.HAM;
import static generics.example_abstract_builder.Topping.ONION;
import static generics.example_abstract_builder.Topping.SAUSAGE;



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
