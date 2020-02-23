package com.saprykinav.currencycalculator.operation;

import com.saprykinav.currencycalculator.currency.Currency;

import java.math.BigDecimal;

public class Mul extends Operation
{
    public Mul(BigDecimal rOp) {
        super(rOp);
        setStrRightOperand(rOp.toString());
    }

    public String toString() {
        String value = "";
        value += " * " + getStrRightOperand();
        return value;
    }
    public BigDecimal getResult(BigDecimal leftOperand) {
        BigDecimal result = leftOperand.multiply(getRightOperand());

        return result;
    }
}
