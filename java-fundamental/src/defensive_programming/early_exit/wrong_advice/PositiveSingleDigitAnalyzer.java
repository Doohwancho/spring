package defensive_programming.early_exit.wrong_advice;

public class PositiveSingleDigitAnalyzer implements NumberAnalyzer {
    public boolean check(int number) {
        return number > 0 && number < 10;
    }
    
    public String getResult() {
        return "Positive single-digit number.";
    }
}
