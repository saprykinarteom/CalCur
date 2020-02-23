package com.saprykinav.currencycalculator.currency;

import com.saprykinav.currencycalculator.CurrencyRate;
import com.saprykinav.currencycalculator.operation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public abstract class  Currency
{
    private BigDecimal rate;
    private BigDecimal number;
    private ArrayList<Operation> operation;
    static private String expression = "";


    public Currency (){
        operation = new ArrayList<Operation>();
    };
    public Currency minus (Currency rOp){
        operation.add(new Min(rOp));
        return this;
    }
    public Currency plus (Currency rOp) {
        operation.add(new Sum(rOp));
        return this;
    }
    public Currency multiply(double n){
        BigDecimal rOp = BigDecimal.valueOf(n);
        operation.add(new Mul(rOp));
        return this;
    }
    public Currency division(double n) {
        BigDecimal rOp = BigDecimal.valueOf(n);
        operation.add(new Div(rOp));
        return this;
    }
    public BigDecimal getRate()
    {
        return rate;
    }
    public BigDecimal getNumberInDollar() {
        return number;
    }
    public BigDecimal getNumberInEuro() {
        return number.divide(CurrencyRate.euro.getValue(),DECIMAL128);
    }
    public BigDecimal getNumberInRub() {
        return number.divide(CurrencyRate.rub.getValue(),DECIMAL128);
    }
    public String resultInDollarToString() {
        addToExpression(" = " + getNumberInDollar().toString() + " $");
        return getExpression();
    }
    public String resultInEuroToString() {
        addToExpression( " = " + getNumberInEuro().toString() + " €");
        return getExpression();
    }
    public String resultInRubToString() {
        addToExpression(" = " + getNumberInRub().toString() + " ₽");
        return getExpression();
    }
    public void setNumber(double newNumber)
    {
        number= BigDecimal.valueOf(newNumber);
    }
    public void setNumber(BigDecimal newNumber)
    {
        number= newNumber;
    }
    public void setRate (BigDecimal newRate) {
        rate = newRate;
    }
    public void setRate(double newRate)
    {
        rate = BigDecimal.valueOf(newRate);
    }
    public Currency calculation()
    {
        addToExpression(this.toString());
        for (Operation result : operation) {
            setNumber(result.getResult(this.getNumberInDollar()));
            addToExpression(result.toString());
        }

        operation.clear();
        return this;
    }
    public void addToExpression(String string) {
        this.expression += string;
    }
    public String getExpression(){
        return expression;
    }
    abstract public String toString();

    public static final MathContext DECIMAL128 =
            new MathContext(34, RoundingMode.HALF_EVEN);
}
