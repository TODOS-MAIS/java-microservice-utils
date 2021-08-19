package br.com.arca.commons.util;

import java.util.Optional;

public class NumberUtils {
    public static boolean isGreatherOrEqualsThan(Number firstNumber, Number secondNumber) {
        if(firstNumber == null || secondNumber == null) {
            return false;
        }

        return firstNumber.doubleValue() >= secondNumber.doubleValue();
    }
}
