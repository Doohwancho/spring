package oop.pop.ex2.oop_calculator.tobe;

import oop.pop.ex2.oop_calculator.domain.PositiveNumber;

public interface ArithmeticOperator {
    boolean supports(String operator);
    int calculate(final PositiveNumber operand1, final PositiveNumber operand2);
}