package com.saprykinav.currencycalculator.operation;

import com.saprykinav.currencycalculator.currency.Currency;

import java.math.BigDecimal;

public class Sum extends Operation
{
    public Sum(Currency rOp){
        super(rOp.getNumberInDollar());
        setStrRightOperand(rOp.toString());
    }

    public BigDecimal getResult(BigDecimal leftOperand) {
        BigDecimal result = leftOperand.add(getRightOperand());
        return result;
    }
    public String toString() {
        String value = "";
        value += " + " + getStrRightOperand();
        return value;
    }
}
