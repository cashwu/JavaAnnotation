package com.cashwu.javaannotation;

@CommandKeyword("divide")
public class Divider implements MathProcessing {
    @Override
    public double doCalculation(double leftVal, double rightVal) {
        return leftVal / rightVal;
    }
}
