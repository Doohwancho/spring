package clean_code._03_variables_.원시값_문자열을_포장한_클래스.step2;

public class Person {

    private final Id id; //final -> 불변 변수라 공유하기 좋다.
    private final Age age;
    private final Money money;

    public Person(final Id id, final Age age, final Money money) { //아니 근데 아무리 해당 id, age, money에 유효성 검사 추가시키고 싶다고 해도 그렇지, 모든 VO만드는건 OOP병 말기 아닐까? new를 너무 남발하잖아.
        this.id = id;
        this.age = age;
        this.money = money;
    }

    public class Id {
        private int value;

        public Id(int value){
            if(value > 100){ //validation check 가능!
                throw new RuntimeException();
            }
            this.value = value;
        }

        //add custom method
    }

    public class Age {
        private int value;

        //add custom method
    }

    public class Money {
        private int value;

        //add custom method
    }
}
