package com.saprykinav.currencycalculator;

import com.saprykinav.currencycalculator.currency.Dollar;
import com.saprykinav.currencycalculator.currency.Euro;
import com.saprykinav.currencycalculator.currency.Rub;


public class CalCur {
    public static void main(String[] args) {
        // example of calculating an expression "1 $ * 2 + 2 € = 265 ₽"
        System.out.println(new Dollar(1).multiply(2).plus(new Euro(2)).calculation().resultInRubToString());
    }
}