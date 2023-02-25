package oop.calculator;

import oop.calculator.domain.Calculator;
import oop.calculator.domain.PositiveNumber;
import oop.calculator.ui.ConsoleInputUI;
import oop.calculator.ui.ConsoleOutputUI;

public class Main{

    public static void main(String[] args) {
        PositiveNumber operand1 = new PositiveNumber(ConsoleInputUI.enterOperand());
        String operator = ConsoleInputUI.enterOperator();
        PositiveNumber operand2 = new PositiveNumber(ConsoleInputUI.enterOperand());

        int result = Calculator.calculate(operand1, operator, operand2);

        ConsoleOutputUI.printResult(result);
    }
}