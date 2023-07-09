package clean_code._01_principle_.객체지향_생활체조_원칙._9;

public class Distance {

    private final int meter;

    public Distance(final int meter) {
        if (meter < 0) {
            throw new IllegalArgumentException("잘못된 거리 값 입니다.");
        }

        this.meter = meter;
    }

    public int toCentimeter() { //int cm = distance.getMeter() * 100; 으로 @Getter로 해결하지 말고, 단위 환산같은 애들은 내부에서 따로 메서드 만들어주자.
        return meter * 100;
    }

    public int getMeter() {
        return meter;
    }
}