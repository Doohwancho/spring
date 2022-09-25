package __2.객체_생성과_파괴.__2.__2_자바_빈즈_패턴;

public class Main {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setFat(0);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}

/*

--
what is 자바 빈즈 패턴?


생성자는 NoArgs이나,
필드 초기화를 setter()로 하는 것.


---
단점

1. 객체 불변성 파괴 
2. 객체 하나 생성하는데 .setter()를 몇개나 쓰는거야!!!!
3. 멀티 쓰레드 환경에서 vo의 변수에 여러 쓰레드가 .setter()남발하면 thread safe하지 않아서 freeze 처리 등 해줘야 하나, 성능 떨어지고 freeze 메서드가 확실히 실행됬는지 컴파일러가 보증해주지 않아 런타임 오류에 취약.  

*/
