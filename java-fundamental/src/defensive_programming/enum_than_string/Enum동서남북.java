package defensive_programming.enum_than_string;

public class Enum동서남북{
    
    public static void main(String[] args) {
        동서남북 동 = 동서남북.동; //ide에서 .찍었을 때, 동/서/남/북 선택지 4개로 제한되고, 그 외의 인풋은 컴파일에러나서 잡아줌.
    }
    enum 동서남북 {
        동,서,남,북;
        
        String getDirection(){
            return name();
        }
    }
}
