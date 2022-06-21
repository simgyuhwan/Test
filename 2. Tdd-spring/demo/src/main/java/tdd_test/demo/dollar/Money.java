package tdd_test.demo.dollar;

public abstract class Money {
    protected int amount;

    abstract Money times(int multiplier);

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && object.getClass().equals(money.getClass());
    }

    static Franc franc(int amount){
        return new Franc(amount); 
    }

    static Money dollar(int amount){
        return new Dollar(amount);
    }
}
