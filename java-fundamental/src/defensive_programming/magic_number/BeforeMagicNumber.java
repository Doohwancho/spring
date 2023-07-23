package defensive_programming.magic_number;

public class BeforeMagicNumber {
    public static void main(String[] args) {
        double weight = 70.0;
        double height = 1.75;
        double bmi = weight / (height * height);
        if (bmi < 18.5) {
            System.out.println("Underweight");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            System.out.println("Normal weight");
        } else if (bmi >= 25 && bmi < 29.9) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }
    }

}
