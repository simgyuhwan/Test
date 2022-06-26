package tdd_test.demo.dollar;

import java.util.Hashtable;

public class Bank {

    private Hashtable rates = new Hashtable();

    Bank(){
        rates.put(new Pair("USD", "USD"), 1);
        rates.put(new Pair("CHF", "CHF"),1);
    }

    Money reduce(Expression source, String to){
        return source.reduce(this, to);
    }


    void addRate(String from, String to, int rate){
        rates.put(new Pair(from, to), new Integer(rate));
    }

    int rate(String from, String to){
        Integer rate = (Integer)rates.get(new Pair(from, to));
        return rate.intValue();
    }

}
