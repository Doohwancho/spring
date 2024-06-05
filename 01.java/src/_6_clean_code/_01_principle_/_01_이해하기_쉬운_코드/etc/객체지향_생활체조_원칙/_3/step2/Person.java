package _6_clean_code._01_principle_._01_이해하기_쉬운_코드.etc.객체지향_생활체조_원칙._3.step2;

public class Person {

    private final Id id;
    private final Age age;
    private final Money money;

    public Person(final Id id, final Age age, final Money money) { //아니 근데 아무리 해당 id, age, money에 유효성 검사 추가시키고 싶다고 해도 그렇지, 모든 VO만드는건 OOP병 말기 아닐까? new를 너무 남발하잖아.
        this.id = id;
        this.age = age;
        this.money = money;
    }

    public class Id {
        private int value;
    }

    public class Age {
        private int value;
    }

    public class Money {
        private int value;
    }
}
