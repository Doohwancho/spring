package _7_defensive_programming.magic_number;

public class AfterMagicNumber {
    private static final double UNDERWEIGHT_THRESHOLD = 18.5; //static이라 하나 돌려쓰기 -> 효율증가
    private static final double NORMAL_WEIGHT_UPPER_THRESHOLD = 24.9;
    private static final double OVERWEIGHT_UPPER_THRESHOLD = 29.9;
    
    public static void main(String[] args) {
        double weight = 70.0;
        double height = 1.75;
        double bmi = weight / (height * height);
        if (bmi < UNDERWEIGHT_THRESHOLD) { //readability 증가
            System.out.println("Underweight");
        } else if (bmi >= UNDERWEIGHT_THRESHOLD && bmi < NORMAL_WEIGHT_UPPER_THRESHOLD) {
            System.out.println("Normal weight");
        } else if (bmi >= NORMAL_WEIGHT_UPPER_THRESHOLD && bmi < OVERWEIGHT_UPPER_THRESHOLD) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }
    }
    
    public double getUnderweightThreshold(){ //getter도 활용 가능
        return UNDERWEIGHT_THRESHOLD;
    }
    
}
