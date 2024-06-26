package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._3_equals_재정의._3_ex_ArrayList의_equals;

public class EqualsInList {
    private Object[] elementData;
    private int size;

    /**
     *  ArrayList에  .contains()에서 재구현한 equals는 이렇게 생김.
     *
     *  for loop으로 돌면서, 객체가 본인인지 체크하고, 같은 타입인지 체크하고, 값이 같은지 체크한다.
     */


    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
}
