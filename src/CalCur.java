import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CalCur {
    public static void main(String[] args) {
        System.out.println(new Dollar(1).minus(new RUB(11)).calculation().resultInRUBToString());

    }
}
enum currencyRate {
    RUB(BigDecimal.valueOf(0.016)),
    EURO(BigDecimal.valueOf(1.12)),
    DOLLAR(BigDecimal.valueOf(1));

    private BigDecimal value;
    currencyRate(BigDecimal value){
        this.value = value;
    }
    public BigDecimal getValue(){
        return value;
    }
}

abstract class  Currency
{
    private BigDecimal rate;
    private BigDecimal number;
    private ArrayList<Operation> operation;
    static private String expression = "";

    abstract class Operation
    {
        private BigDecimal rightOperand;
        private String strRightOperand = "";

        public Operation(BigDecimal rOp) {
            rightOperand = rOp;
        }
        public abstract BigDecimal getResult();

        public BigDecimal getRightOperand(){
            return rightOperand;
        }
        public void setStrRightOperand(String str){
            strRightOperand += str;
        }
        public String getStrRightOperand() {
            return strRightOperand;
        }
        abstract public String toString();

    }
    class Sum extends Operation
    {
        public Sum(Currency rOp){
            super(rOp.getNumberInDollar());
            setStrRightOperand(rOp.toString());
        }

        public BigDecimal getResult() {
            BigDecimal leftOperand = Currency.this.getNumberInDollar();
            BigDecimal result = leftOperand.add(getRightOperand());
            return result;
        }
        public String toString() {
            String value = "";
            value += " + " + getStrRightOperand();
            return value;
        }
    }
    class Min extends Operation
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
        public BigDecimal getResult() {
            BigDecimal leftOperand = Currency.this.getNumberInDollar();
            BigDecimal result = leftOperand.subtract(getRightOperand());
            return result;
        }
    }
    class Mul extends Operation
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
        public BigDecimal getResult() {
            BigDecimal leftOperand = Currency.this.getNumberInDollar();
            BigDecimal result = leftOperand.multiply(getRightOperand());

            return result;
        }
    }
    class Div extends Operation
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
        public BigDecimal getResult() {
            BigDecimal leftOperand = Currency.this.getNumberInDollar();
            BigDecimal result = leftOperand.divide(getRightOperand(),DECIMAL128);
            return result;
        }
    }

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
        return number.divide(currencyRate.EURO.getValue(),DECIMAL128);
    }
    public BigDecimal getNumberInRUB() {
        return number.divide(currencyRate.RUB.getValue(),DECIMAL128);
    }
    public String resultInDollarToString() {
        addToExpression(" = " + getNumberInDollar().toString() + " $");
        return getExpression();
    }
    public String resultInEuroToString() {
        addToExpression( " = " + getNumberInEuro().toString() + " €");
        return getExpression();
    }
    public String resultInRUBToString() {
        addToExpression(" = " + getNumberInRUB().toString() + " ₽");
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
        for (Currency.Operation result : operation) {
            addToExpression(this.toString());
            setNumber(result.getResult());
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

class Dollar extends Currency
{
    Dollar(double n)
    {
        super();
        BigDecimal num = BigDecimal.valueOf(n);
        setNumber(num);
        BigDecimal r = currencyRate.DOLLAR.getValue();
        setRate(r);
    }
    public String toString() {
        String string = "";
        string += getNumberInDollar() + " $";
        return string;
    }
}

class Euro extends Currency {
    Euro(double n) {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(currencyRate.EURO.getValue());
        setNumber(num);
        BigDecimal r = currencyRate.EURO.getValue();
        setRate(r);
    }
    public String toString() {
        String string = "";
        string += getNumberInEuro() + " €";
        return string;
    }
}

class RUB extends Currency
{
    RUB(double n)
    {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(currencyRate.RUB.getValue());
        setNumber(num);
        BigDecimal r = currencyRate.RUB.getValue();
        setRate(r);
    }
    public String toString()
    {
        String string = "";
        string += getNumberInRUB() + " ₽";
        return string;
    }
}
