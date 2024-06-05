package _7_defensive_programming.early_exit.wrong_advice;

public class Main {
    
    //너무 OOP 뇌절이 아닌가.. 단순 비교식에 클래스 & 인터페이스가 대체 몇개나 나오는거야..
    public String analyzeNumber(int number) {
        NumberAnalyzer[] analyzers = new NumberAnalyzer[]{
            new ZeroAnalyzer(),
            new PositiveSingleDigitAnalyzer(),
            //...Add the other classes here.
        };
        
        for (NumberAnalyzer analyzer : analyzers) {
            if (analyzer.check(number)) {
                return analyzer.getResult();
            }
        }
        
        throw new IllegalArgumentException("Number not recognized.");
    }
}
