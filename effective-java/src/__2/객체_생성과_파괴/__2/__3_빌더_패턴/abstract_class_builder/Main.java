package __2.객체_생성과_파괴.__2.__3_빌더_패턴.abstract_class_builder;

import static __2.객체_생성과_파괴.__2.__3_빌더_패턴.abstract_class_builder.Size.SMALL;
import static __2.객체_생성과_파괴.__2.__3_빌더_패턴.abstract_class_builder.Topping.*;

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
