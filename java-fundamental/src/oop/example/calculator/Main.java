package oop.example.calculator;

import oop.example.calculator.domain.Calculator;
import oop.example.calculator.domain.PositiveNumber;
import oop.example.calculator.ui.ConsoleInputUI;
import oop.example.calculator.ui.ConsoleOutputUI;

public class Main{

    public static void main(String[] args) {
        PositiveNumber operand1 = new PositiveNumber(ConsoleInputUI.enterOperand());
        String operator = ConsoleInputUI.enterOperator();
        PositiveNumber operand2 = new PositiveNumber(ConsoleInputUI.enterOperand());

        int result = Calculator.calculate(operand1, operator, operand2);

        ConsoleOutputUI.printResult(result);
    }
}