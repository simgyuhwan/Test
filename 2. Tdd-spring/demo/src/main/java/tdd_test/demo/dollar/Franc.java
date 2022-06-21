package tdd_test.demo.dollar;

public class Franc extends Money{

    private String currency;
    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    Money times(int multiplier){
        return Money.franc(amount * multiplier);
    }

    @Override
    String currency() {
        return currency;
    }
}
