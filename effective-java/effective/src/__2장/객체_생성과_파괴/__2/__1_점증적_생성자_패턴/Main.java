package __2장.객체_생성과_파괴.__2.__1_점증적_생성자_패턴;

public class Main {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
    }
}

/*

---
what is 점증적 생성자 패턴?


파라미터가 많은 클래스에
생성자 만들어야 할 때,
빈 생성자부터 파라미터수 +1 씩 해가며 하나씩 만드는 것.



---
단점 

1. 파라미터중에서 선택해서 초기화 불가능 
2. 생성자 코드가 쥰~내 길어짐. 극혐 


*/
