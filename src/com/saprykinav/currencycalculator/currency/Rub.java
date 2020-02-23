package com.saprykinav.currencycalculator.currency;

import com.saprykinav.currencycalculator.CurrencyRate;

import java.math.BigDecimal;

public class Rub extends Currency {
    public Rub(double n) {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(CurrencyRate.Rub.getValue());
        setNumber(num);
        BigDecimal r = CurrencyRate.Rub.getValue();
        setRate(r);
        }
    public String toString()
        {
        String string = "";
        string += getNumberInRUB() + " ₽";
        return string;
        }
        }
