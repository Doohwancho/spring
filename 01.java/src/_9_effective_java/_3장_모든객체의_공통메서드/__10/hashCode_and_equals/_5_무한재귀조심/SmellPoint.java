package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._5_무한재귀조심;

public class SmellPoint extends Point {
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) //여기는 서로 무사히 통과하는데,
            return false;

        if(!(o instanceof SmellPoint)) //호출된 얘가 다시 ColorPoint의 equals()을 호출하는걸 무한 재귀 -> StackOverflowError
            return o.equals(this);

        return super.equals(o);
    }
}
