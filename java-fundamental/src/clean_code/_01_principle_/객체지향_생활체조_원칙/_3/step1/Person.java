package clean_code._01_principle_.객체지향_생활체조_원칙._3.step1;

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