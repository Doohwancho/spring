package _9_effective_java._2장_객체_생성과_파괴.객쳬_생성.생성자에_인자가_많으면_빌더패턴.__3_빌더_패턴.abstract_class_builder;

import static _9_effective_java._2장_객체_생성과_파괴.객쳬_생성.생성자에_인자가_많으면_빌더패턴.__3_빌더_패턴.abstract_class_builder.Size.SMALL;

public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(Topping.SAUSAGE)
                .addTopping(Topping.ONION)
                .build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(Topping.HAM)
                .sauceInside()
                .build();
    }
}
