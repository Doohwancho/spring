package defensive_programming.early_exit.before;

public class Main {
    
    //nested if-else문이 어지럽다!
    public String analyzeNumber(int number) {
        String result;
        if (number > 0) {
            if (number < 10) {
                result = "Positive single-digit number.";
            } else {
                result = "Positive multi-digit number.";
            }
        } else if (number < 0) {
            if (number > -10) {
                result = "Negative single-digit number.";
            } else {
                result = "Negative multi-digit number.";
            }
        } else {
            result = "Zero.";
        }
        return result;
    }
    
}
