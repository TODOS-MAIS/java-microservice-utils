package br.com.arca.commons.util;

public class NumberUtils {
    public static boolean isGreatherOrEqualsThan(Number firstNumber, Number secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            return false;
        }

        return firstNumber.doubleValue() >= secondNumber.doubleValue();
    }

    public static float calculatePercentage(Number part, Number total) {
        float percentage = 0F;
        if (total.floatValue() > 0F) {
            percentage = (part.floatValue() / total.floatValue()) * 100F;
        }
        return percentage;
    }
}