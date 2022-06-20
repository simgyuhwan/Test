package tdd_test.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public void company(String company){
        if(company.contains("삼성")){
            System.out.println("삼성이다");

        }else if(company.contains("LG")){
            System.out.println("LG 이다");

        }else if(company.contains("Apple")){
            System.out.println("Apple 이다");
        }
    }

    public void company2(String company){
        Arrays.stream(Company.values()).filter(n -> company.contains(n.value)).forEach(Company::print);
    }

    public static enum Company{
        SAMSUNG("삼성"), LG("LG"), APPLE("Apple");

        private String value;

        Company(String value){
            this.value = value;
        }
        public void print(){
            System.out.println(this.value + "이다");
        }
    }

    public static void main(String[] args) {
       // Arrays.stream(Company.values()).filter(c -> c.value.equals("삼성")).forEach(Company::print);

        Test test = new Test();
        test.company2("삼성");
    }

}
