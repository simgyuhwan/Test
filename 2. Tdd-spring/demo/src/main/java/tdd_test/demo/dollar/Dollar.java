package tdd_test.demo.dollar;

import java.util.Objects;

public class Dollar extends Money{

    public Dollar(int amount) {
        this.amount = amount;
    }

    Money times(int multiplier){
        return new Dollar(amount * multiplier);
    }


}
