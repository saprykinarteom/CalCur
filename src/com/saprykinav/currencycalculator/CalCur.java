package com.saprykinav.currencycalculator;

import com.saprykinav.currencycalculator.currency.Dollar;
import com.saprykinav.currencycalculator.currency.Rub;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CalCur {
    public static void main(String[] args) {
        // example of calculating an expression "1 $ - 11 ₽ = 51.5 ₽"
        System.out.println(new Dollar(1).minus(new Rub(11)).calculation().resultInRUBToString());
    }
}