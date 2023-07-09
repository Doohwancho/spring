package clean_code._03_variables_.원시값_문자열을_포장한_클래스.step1;

/*
   주장: primitive type쓰지 말고 wrapper class로 한번 말자!

   왜?
   1. 안애 .sort()라던지 입맛대로 재조정 가능
   2. 클래스 명을 잘 써서 좀 더 의도가 명확해져서 다른사람이 이해하기 편함
   3. 객체니까 null처리 핸들링도 더 수월
      ex. 함수형에 Predicate인가? Optional인가? 써서. -> 원시형 타입은 의도가 없으니, 커스텀 wrapper class로 의도부여 + null check 등 하라는 것
      => 근데 IntPredicate라던가 있지 않나?

   단점
   1.
 */

public class Person {

    private final int id;
    private final int age;
    private final int money;
    private final int distance;

    public Person(final int id, final int age, final int money, final int distance) { //int는 원시값이라 의도가 없어서, id인지 age인지 money인지 모른다. 따라서 custom Wrapper클래스 만들라는데, 내 생각은 이거 개오바임.
        this.id = id;
        this.age = age;
        this.money = money;
        if (distance < 0) {
            throw new IllegalArgumentException("잘못된 거리 값 입니다."); //primitive type이면 이런 처리를 객체 생성시에 못한다는데, 이건 어느정도 설득력이 있긴 하네.
        }
        this.distance = distance;
    }
}