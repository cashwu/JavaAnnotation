package com.cashwu.javaannotation;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter an operation and two numbers:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String[] parts = userInput.split(" ");
        String keyword = parts[0];
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);

        process(keyword, leftVal, rightVal);
    }

    private static void process(String keyword,
                                double leftVal,
                                double rightVal) {
        Object processor = retrieveProcessor(keyword);
        double result = 0;

        if (processor instanceof MathProcessing) {
            result = ((MathProcessing) processor).doCalculation(leftVal, rightVal);
        }
        else {
            result = handleCalculate(processor, leftVal, rightVal);
        }

        System.out.println("result = " + result);
    }

    private static double handleCalculate(Object processor,
                                          double leftVal,
                                          double rightVal) {
        double result = 0;

        try {
            CommandKeyword commandKeyword = processor.getClass()
                                                     .getAnnotation(CommandKeyword.class);
            String methodName = commandKeyword.method();

            Method method = processor.getClass().getMethod(methodName, double.class, double.class);
            result = (double) method.invoke(processor, leftVal, rightVal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Object retrieveProcessor(String keyword) {

        Object[] processings = {new Adder(),
                                new Subtracter(),
                                new Multiplier(),
                                new Divider(),
                                new PowerOf()};

        for (Object processing : processings) {

            CommandKeyword commandKeyword = processing.getClass()
                                                      .getAnnotation(CommandKeyword.class);

            String name = commandKeyword.value();

            if (keyword.equalsIgnoreCase(name)) {
                return processing;
            }
        }

        //        switch(keyword) {
        //            case "add":
        //                return new Adder();
        //            case "subtract":
        //                return new Subtracter();
        //            case "multiply":
        //                return new Multiplier();
        //            case "divide":
        //                return new Divider();
        //        }
        return null;

    }

    static double valueFromWord(String word) {
        String[] numberWords = {"zero",
                                "one",
                                "two",
                                "three",
                                "four",
                                "five",
                                "six",
                                "seven",
                                "eight",
                                "nine"};
        double value = -1d;
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if (value == -1d)
            value = Double.parseDouble(word);

        return value;
    }

}









