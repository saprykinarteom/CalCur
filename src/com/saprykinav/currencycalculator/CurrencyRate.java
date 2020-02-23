package com.saprykinav.currencycalculator;

import java.math.BigDecimal;
// The currency rate is added and changed manually
public enum CurrencyRate {
    rub(BigDecimal.valueOf(0.016)),
    euro(BigDecimal.valueOf(1.12)),
    dollar(BigDecimal.valueOf(1));

    private BigDecimal value;
    CurrencyRate(BigDecimal value){
        this.value = value;
    }
    public BigDecimal getValue(){
        return value;
    }
}