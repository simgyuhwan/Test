package tdd_test.demo.dollar;

public class Money implements Expression{
    protected int amount;
    protected String currency;

    Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    String currency(){
        return currency;
    }

    public Expression times(int multiplier){
        return new Money(amount * multiplier, currency);
    }


    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    static Money franc(int amount){
        return new Money(amount, "CHF");
    }

    static Money dollar(int amount){
        return new Money(amount, "USD");
    }

    @Override
    public String toString() {
        return amount + "  " + currency;
    }


    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }

    public Money reduce (Bank bank, String to){
        int rate = bank.rate(currency, to);
        return new Money(amount/rate, to);
    }


//    Money reduce(Expression source, String to){
//        if(source instanceof Money) return ((Money) source).reduce(new Bank(),to);
//        Sum sum = (Sum) source;
//        return sum.reduce(new Bank(),to);
//    }

}
