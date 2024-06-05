package _7_defensive_programming.enum_than_string;

public class StringHasNotBoundary {
    //case1) int 로 동서남북 표현하기
    //cons - 가독성이 구림
    private static int 동 = 0;
    private static int 서 = 1;
    private static int 남 = 2;
    private static int 북 = 3;
    
    //case2) String으로 동서남북 표현하기
    //cons - 제약사항이 없다. 동/서/남/북 외에 다른 값 넣었을 때, validation check를 setter에 넣거나, 불변객체로 만들거나 해야함..
    private static String 동String = "동";
    private static String 서String = "서";
    private static String 남String = "남";
    private static String 북String = "북";
    
}
