package com.saprykinav.currencycalculator.currency;

import com.saprykinav.currencycalculator.CurrencyRate;

import java.math.BigDecimal;

public class Euro extends Currency {
    public Euro(double n) {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(CurrencyRate.euro.getValue());
        setNumber(num);
        BigDecimal r = CurrencyRate.euro.getValue();
        setRate(r);
    }
    public String toString() {
        String string = "";
        string += getNumberInEuro() + " €";
        return string;
    }
}
