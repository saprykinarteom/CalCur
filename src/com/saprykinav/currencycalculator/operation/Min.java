package com.saprykinav.currencycalculator.operation;

import com.saprykinav.currencycalculator.currency.Currency;

import java.math.BigDecimal;

public class Min extends Operation
{
    public Min(Currency rOp){
        super(rOp.getNumberInDollar());
        setStrRightOperand(rOp.toString());
    }

    public String toString() {
        String value = "";
        value += " - " + getStrRightOperand();
        return value;
    }
    public BigDecimal getResult(BigDecimal leftOperand) {
        BigDecimal result = leftOperand.subtract(getRightOperand());
        return result;
    }
}
