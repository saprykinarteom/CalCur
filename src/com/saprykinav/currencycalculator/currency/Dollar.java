package com.saprykinav.currencycalculator.currency;

import com.saprykinav.currencycalculator.CurrencyRate;

import java.math.BigDecimal;

public class Dollar extends Currency
{
    public Dollar(double n)
    {
        super();
        BigDecimal num = BigDecimal.valueOf(n);
        setNumber(num);
        BigDecimal r = CurrencyRate.DOLLAR.getValue();
        setRate(r);
    }
    public String toString() {
        String string = "";
        string += getNumberInDollar() + " $";
        return string;
    }
}