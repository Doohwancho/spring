package defensive_programming.early_exit.wrong_advice;

public class ZeroAnalyzer implements NumberAnalyzer {
    public boolean check(int number) {
        return number == 0;
    }
    
    public String getResult() {
        return "Zero.";
    }
}
