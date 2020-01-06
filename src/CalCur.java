import java.math.BigDecimal;
import java.util.ArrayList;


public class CalCur {
    public static void main(String[] args) {
        System.out.println(new RUB(10).plus(new Euro(20)).minus(new RUB(30)).equal());
    }
}

abstract class operation {
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;

    public operation(BigDecimal lOp, BigDecimal rOp) {
        leftOperand = lOp;
        rightOperand = rOp;
    }
    public abstract BigDecimal getResult();
    public BigDecimal getLeftOperand() {
        return leftOperand;
    }
    public BigDecimal getRightOperand(){
        return rightOperand;
    }
}

class sum extends operation
{
    public sum(BigDecimal lOp, BigDecimal rOp) {
        super(lOp, rOp);
    }

    public BigDecimal getResult() {

        return super.getLeftOperand().add(super.getRightOperand());
    }
}
class min extends operation
{
    public min(BigDecimal lOp, BigDecimal rOp) {
        super(lOp, rOp);
    }

    public BigDecimal getResult() {

        return super.getLeftOperand().subtract(super.getRightOperand());
    }
}

class mul extends operation
{
    public mul(BigDecimal lOp, BigDecimal rOp) {
        super(lOp, rOp);
    }

    public BigDecimal getResult() {

        return super.getLeftOperand().multiply(super.getRightOperand());
    }
}

class div extends operation
{
    public div(BigDecimal lOp, BigDecimal rOp) {
        super(lOp, rOp);
    }

    public BigDecimal getResult() {

        return super.getLeftOperand().divide(super.getRightOperand());
    }
}

abstract class  Currency
{
    private BigDecimal rate;
    private BigDecimal number;
    private ArrayList<Currency> operation;

    public  Currency(double n, double r)
    {
        number = BigDecimal.valueOf(n);
        rate = BigDecimal.valueOf(r);
        operation = new ArrayList<Currency>();
    }
    public Currency(BigDecimal n, double r){
        number = n;
        rate = BigDecimal.valueOf(r);
    }

    public Currency(BigDecimal n, BigDecimal r){
        number = n;
        rate = r;
    }

    public Currency(Currency c) {
        number = c.getNumber();
        rate = c.getRate();
    }

    private Currency _minus(Currency c)
    {
        number = number.subtract(c.getNumber().multiply(c.getRate().divide(rate)));
        return this;
    }
    public Currency minus (Currency c){
        operation.add(new Currency(this._minus(c)));
        return this;
    }
    private Currency _plus(Currency c)
    {
        number = number.add(c.getNumber().multiply(c.getRate().divide(rate)));
        return this;
    }
    public Currency plus (Currency c) {
        operation.add(new Currency(this._plus(c)));
        return this;
    }
    private Currency _multiply(double n)
    {
        number = number.multiply(BigDecimal.valueOf(n));
        return this;
    }
    public Currency multiply(double n){
        operation.add(new Currency(this._multiply(n)));
        return this;
    }
    private Currency _division(double n)
    {
            number =  number.divide(BigDecimal.valueOf(n));
            return this;
    }
    public Currency division(double n){
        operation.add(new Currency(this._division(n)));
        return this;
    }

    public BigDecimal equal() {
        return operation.get(operation.size()-1).getNumber();
    }

    public BigDecimal getRate()
    {
        return rate;
    }
    public void setRate(double newRate)
    {
        rate = BigDecimal.valueOf(newRate);
    }
    public BigDecimal getNumber() {
        return number;
    }
    public void setNumber(double newNumber)
    {
        number= BigDecimal.valueOf(newNumber);
    }

}

class Dollar extends Currency
{
    Dollar(double n)
    {
        super(n, 1);
    }
    Dollar(Currency n) {
        super((n.getNumber().multiply(n.getRate()).divide(BigDecimal.valueOf(1))), 1);
    }
}

class Euro extends Currency
{
    Euro(double n)
    {
        super(n, 1.12);
    }
    Euro(Currency n) {
        super((n.getNumber().multiply(n.getRate()).divide(BigDecimal.valueOf(1.12))), 1.12);
    }
}

class RUB extends Currency
{
    RUB(double n)
    {
        super(n, 0.016);
    }
    RUB(Currency n) {
        super((n.getNumber().multiply(n.getRate()).divide(BigDecimal.valueOf(0.016))), 0.0160);
    }
}
