import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class CalCur {
    public static void main(String[] args) {
        System.out.println(new Euro(1).plus(new Euro(11)).minus(new Dollar(5)).multiply(2).calculation().getNumberInEuro());
        System.out.println(new Euro(1).plus(new Euro(11)).minus(new Dollar(5)).multiply(2).calculation().getNumberInRUB());
        System.out.println(new Euro(1).plus(new Euro(11)).minus(new Dollar(5)).multiply(2).calculation().getNumberInDollar());
    }
}

abstract class  Currency
{
    private BigDecimal rate;
    private BigDecimal number;
    private ArrayList<Operation> operation;
    static Map<String,BigDecimal> currencyRate = Map.of("RUB",BigDecimal.valueOf(0.016), "Euro", BigDecimal.valueOf(1.12), "Dollar",BigDecimal.valueOf(1));

    abstract class Operation
    {
        private BigDecimal rightOperand;

        public Operation(BigDecimal rOp) {
            rightOperand = rOp;
        }
        public abstract BigDecimal getResult();

        public BigDecimal getRightOperand(){
            return rightOperand;
        }
    }
    class Sum extends Operation
    {
        public Sum(BigDecimal rOp) {
            super(rOp);
        }

        public BigDecimal getResult() {
            BigDecimal leftOperand = Currency.this.getNumberInDollar();
            BigDecimal result = leftOperand.add(getRightOperand());
            return result;
        }
    }
    class Min extends Operation
    {
        public Min(BigDecimal rOp) {
            super(rOp);
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
    public Currency minus (Currency c){
        BigDecimal rOp = c.getNumberInDollar();
        operation.add(new Min(rOp));
        return this;
    }
    public Currency plus (Currency c) {
        BigDecimal rOp = c.getNumberInDollar();
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
        return number.divide(currencyRate.get("Euro"),DECIMAL128);
    }
    public BigDecimal getNumberInRUB() {
        return number.divide(currencyRate.get("RUB"),DECIMAL128);
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
            setNumber(result.getResult());
        }
        operation.clear();
        return this;
    }
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
        BigDecimal r = currencyRate.get("Dollar");
        setRate(r);
    }
    Dollar(Currency n) {
        super();
        BigDecimal num = n.getNumberInDollar();
        setNumber(num);
        BigDecimal r = currencyRate.get("Dollar");
        setRate(r);
    }
}

class Euro extends Currency
{
    Euro(double n)
    {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(currencyRate.get("Euro"));
        setNumber(num);
        BigDecimal r = currencyRate.get("Euro");
        setRate(r);
    }
    Euro(Currency n)
    {
        super();
        BigDecimal num = n.getNumberInDollar();
        setNumber(num);
        BigDecimal r = currencyRate.get("Euro");
        setRate(r);
    }
}

class RUB extends Currency
{
    RUB(double n)
    {
        super();
        BigDecimal num = BigDecimal.valueOf(n).multiply(currencyRate.get("RUB"));
        setNumber(num);
        BigDecimal r = currencyRate.get("RUB");
        setRate(r);
    }
    RUB(Currency n) {
        super();
        BigDecimal num = n.getNumberInDollar();
        setNumber(num);
        BigDecimal r = currencyRate.get("RUB");
        setRate(r);
    }
}
