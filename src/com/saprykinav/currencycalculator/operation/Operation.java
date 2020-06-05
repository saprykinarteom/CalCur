package com.saprykinav.currencycalculator.operation;


import java.math.BigDecimal;

public abstract class Operation
{
    private BigDecimal rightOperand;
    private String strRightOperand = "";

    public Operation(BigDecimal rOp) {
        rightOperand = rOp;
    }
    public abstract BigDecimal getResult(BigDecimal leftOperand);

    public BigDecimal getRightOperand(){
        return rightOperand;
    }
    public void setStrRightOperand(String str){
        strRightOperand += str;
    }
    public String getStrRightOperand() {
        return strRightOperand;
    }
    public abstract String toString();
}
