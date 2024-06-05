package _2_oop.pop.ex2.oop_calculator;

import _2_oop.pop.ex2.oop_calculator.domain.Calculator;
import _2_oop.pop.ex2.oop_calculator.domain.PositiveNumber;
import _2_oop.pop.ex2.oop_calculator.ui.ConsoleInputUI;
import _2_oop.pop.ex2.oop_calculator.ui.ConsoleOutputUI;

public class Main{

    public static void main(String[] args) {
        PositiveNumber operand1 = new PositiveNumber(ConsoleInputUI.enterOperand());
        String operator = ConsoleInputUI.enterOperator();
        PositiveNumber operand2 = new PositiveNumber(ConsoleInputUI.enterOperand());

        int result = Calculator.calculate(operand1, operator, operand2);

        ConsoleOutputUI.printResult(result);
    }
}