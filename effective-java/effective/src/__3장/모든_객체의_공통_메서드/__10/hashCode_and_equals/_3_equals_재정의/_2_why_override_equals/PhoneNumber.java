package __3장.모든_객체의_공통_메서드.__10.hashCode_and_equals._3_equals_재정의._2_why_override_equals;

public class PhoneNumber {
    /**
     * Q. 왜 equals()를 재정의 해줘야함?
     *
     * A. 만약 equals() 재정의 안하면, 그 객체는 오직 자기 자신과만 같다고 판단한다.
        두 인스턴스의 '물리적 동치성' 만 체크해야 하는게 아니라, '논리적 통치성'을 검사할 일이 있는 경우, equals()를 재정의 해주어야 한다.

        ex. 두 객체를 .equals()로 비교할 때, 같은 객체인지 알고싶은게 아니라(같은 메모리 주소, .hashCode() 비교)
            , 그 안에 들어있는 '값'이 같은지 확인하고 싶은 경우.
        만약 객체 안 필드의 '타입'이 또 다른 객체라면, 그 객체의 equals()를 재귀적으로 호출하거나, 그 객체의 값을 불러와 비교해야 한다.
     */

    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if(val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    //잘 구현된 예시
    @Override
    public boolean equals(Object o) {
        if(o == this) { //1. 자기 자신인지 먼저 체크
            return true;
        }

        if(!(o instanceof PhoneNumber)) { //2. 인스턴스 타입이 같은지 체크
            return false;
        }

        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode; //3. 값이 같은지 체크. 여기서 논리적 비교 한다.
    }
}
