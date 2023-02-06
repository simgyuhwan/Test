package com.practice.jpa.algorithm.lecture;

import java.util.Scanner;

public class 문자찾기 {
    public static int solution(String str, char t) {
        int answer = 0;
        str = str.toUpperCase();
        t = Character.toUpperCase(t);

        for(char x : str.toCharArray()) {
            if(x == t) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char c = sc.next().charAt(0);

        System.out.print(문자찾기.solution(str, c));
    }
}
