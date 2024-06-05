package _7_defensive_programming.early_exit.after;

public class Main {
    
    //early exit으로 조건에 만족하지 '않은'걸 먼저 반환해서 쳐버림
    //pros
    //before에 nested-if-else문은, 뒤 조건을 볼 떄, 앞의 조건들을 다 염두해가며 읽어야 하는데,
    //early exit으로 만족하지 않는 조건들을 먼져 쳐내는 식으로 짜면, 다음 if 조건 읽을 때, 이전 조건을 염두 안해도됨. line by line으로 읽을 수 있음.
    public String analyzeNumber(int number) {
        if (number == 0) { //else 부분에 있던 애를 먼져 early exit으로 쳐냄.
            return "Zero.";
        }
        
        if (number > 0 && number < 10) {
            return "Positive single-digit number.";
        }
        
        if (number > 0) {
            return "Positive multi-digit number.";
        }
        
        if (number < 0 && number > -10) {
            return "Negative single-digit number.";
        }
        
        return "Negative multi-digit number.";
    }
}