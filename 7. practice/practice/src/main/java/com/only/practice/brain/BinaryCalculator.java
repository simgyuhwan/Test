package com.only.practice.brain;

/**
 * Created by Gyuhwan
 */
public class BinaryCalculator {

  public static void main(String[] args) {
    String s = "1234";
    int digit = Character.digit(s.charAt(1), 10);
    System.out.println(digit);
  }
}