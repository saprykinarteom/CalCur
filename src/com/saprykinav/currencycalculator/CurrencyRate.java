package com.saprykinav.currencycalculator;

import java.math.BigDecimal;
// The currency rate is added and changed manually
public enum CurrencyRate {
    Rub(BigDecimal.valueOf(0.016)),
    EURO(BigDecimal.valueOf(1.12)),
    DOLLAR(BigDecimal.valueOf(1));

    private BigDecimal value;
    CurrencyRate(BigDecimal value){
        this.value = value;
    }
    public BigDecimal getValue(){
        return value;
    }
}