import java.math.BigDecimal;
import java.util.ArrayList;


public class CalCur {
    public static void main(String[] args) {
        System.out.println(new RUB(new Dollar(3).plus(new Dollar(1)).minus(new RUB(5))).getNumber());
        Calculation Operation = new Calculation();
        Operation.add("Euro");
        Operation.add("5");
        Operation.add("plus");
        Operation.add("RUB");
        Operation.add("10");
        Operation.calculate();
        System.out.println(Operation.get_result().getNumber());
    }
}

class Calculation
{
    private ArrayList<String> _operation;
    private Currency _result;

    public Calculation(){
        _operation = new ArrayList<String>();
    }
    public void add(String a) {
        _operation.add(a);
    }
    public Currency get_result() {
        return _result;
    }
    public void calculate() {
        Currency Intermed_Res = new Currency(0,0);
        switch (_operation.get(0)){
            case "RUB":
                Intermed_Res = new RUB(Double.parseDouble(_operation.get(1)));
                for(int i = 2; i < _operation.size(); i += 3) {
                    switch (_operation.get(i)) {
                        case "plus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.plus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.plus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.plus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "minus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.minus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.minus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.minus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "multiply":
                            Intermed_Res = Intermed_Res.multiply(Double.parseDouble(_operation.get(i + 2)));
                            break;
                        case "division":
                            Intermed_Res = Intermed_Res.division(Double.parseDouble(_operation.get(i + 2)));
                            break;
                    }
                }
                _result = Intermed_Res;
                break;

            case "Dollar":
                Intermed_Res = new Dollar(Double.parseDouble(_operation.get(1)));
                for(int i = 2; i < _operation.size(); i += 3) {
                    switch (_operation.get(i)) {
                        case "plus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.plus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.plus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.plus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "minus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.minus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.minus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.minus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "multiply":
                            Intermed_Res = Intermed_Res.multiply(Double.parseDouble(_operation.get(i + 2)));
                            break;
                        case "division":
                            Intermed_Res = Intermed_Res.division(Double.parseDouble(_operation.get(i + 2)));
                            break;
                    }
                }
                _result = Intermed_Res;
                break;
            case "Euro":
                Intermed_Res = new Euro(Double.parseDouble(_operation.get(1)));
                for(int i = 2; i < _operation.size(); i += 3) {
                    switch (_operation.get(i)) {
                        case "plus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.plus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.plus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.plus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "minus":
                            switch (_operation.get(i + 1)) {
                                case "RUB":
                                    Intermed_Res = Intermed_Res.minus(new RUB(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Dollar":
                                    Intermed_Res = Intermed_Res.minus(new Dollar(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                                case "Euro":
                                    Intermed_Res = Intermed_Res.minus(new Euro(Double.parseDouble(_operation.get(i + 2))));
                                    break;
                            }
                            break;
                        case "multiply":
                            Intermed_Res = Intermed_Res.multiply(Double.parseDouble(_operation.get(i + 2)));
                            break;
                        case "division":
                            Intermed_Res = Intermed_Res.division(Double.parseDouble(_operation.get(i + 2)));
                            break;
                    }
                }
                _result = Intermed_Res;
                break;
        }
    }
}

class  Currency
{
    private BigDecimal rate;
    private BigDecimal number;

    public  Currency(double n, double r)
    {
        number = BigDecimal.valueOf(n);
        rate = BigDecimal.valueOf(r);
    }
    public Currency(BigDecimal n, double r){
        number = n;
        rate = BigDecimal.valueOf(r);
    }
    public Currency minus(Currency c)
    {
        number = number.subtract(c.getNumber().multiply(c.getRate().divide(rate)));
        return this;
    }
    public Currency plus(Currency c)
    {
        number = number.add(c.getNumber().multiply(c.getRate().divide(rate)));
        return this;
    }
    public Currency multiply(double n)
    {
        number = number.multiply(BigDecimal.valueOf(n));
        return this;
    }
    public Currency division(double n)
    {
            number =  number.divide(BigDecimal.valueOf(n));
            return this;
    };
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

class  Dollar extends Currency
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
