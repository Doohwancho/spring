package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._5_무한재귀조심;



public class ColorPoint extends Point {
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) //여기는 서로 무사히 통과하는데,
            return false;

        if(!(o instanceof ColorPoint)) //얘가 ColorPoint의 equals()을 호출하면,
            return o.equals(this);

        return super.equals(o) && ((ColorPoint) o).color == color;
    }
}
