package com.saprykinav.currencycalculator.currency;

import com.saprykinav.currencycalculator.CurrencyRate;

import java.math.BigDecimal;

public class Euro extends Currency {
    public Euro(double n) {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(CurrencyRate.EURO.getValue());
        setNumber(num);
        BigDecimal r = CurrencyRate.EURO.getValue();
        setRate(r);
    }
    public String toString() {
        String string = "";
        string += getNumberInEuro() + " €";
        return string;
    }
}
