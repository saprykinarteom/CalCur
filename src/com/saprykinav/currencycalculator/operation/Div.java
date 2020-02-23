package com.saprykinav.currencycalculator.operation;

import com.saprykinav.currencycalculator.currency.Currency;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL128;

public class Div extends Operation
{
    public Div(BigDecimal rOp) {
        super(rOp);
        setStrRightOperand(rOp.toString());
    }
    public String toString() {
        String value = "";
        value += " / " + getStrRightOperand();
        return value;
    }
    public BigDecimal getResult(BigDecimal leftOperand) {
        BigDecimal result = leftOperand.divide(getRightOperand(),DECIMAL128);
        return result;
    }
}

