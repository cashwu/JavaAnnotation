package com.cashwu.javaannotation;


@CommandKeyword("power")
public class PowerOf {

    public double calculate(double leftVal, double rightVal) {
        //formattedOutput = leftVal + " to the power of " + rightVal + " is " + product;
        return Math.pow(leftVal, rightVal);
    }
}
