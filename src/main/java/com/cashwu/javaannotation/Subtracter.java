package com.cashwu.javaannotation;

@CommandKeyword("subtract")
public class Subtracter implements MathProcessing {
    @Override
    public double doCalculation(double leftVal, double rightVal) {
        return leftVal - rightVal;
    }
}
