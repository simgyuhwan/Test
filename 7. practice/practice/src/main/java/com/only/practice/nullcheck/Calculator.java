package com.only.practice.nullcheck;

/**
 * Created by Gyuhwan
 */
public interface Calculator {

  default int add(int a, int b) {
    validate(a, b);
    return a + b;
  }

  default int subtract(int a, int b) {
    validate(a, b);
    return a - b;
  }

  private void validate(int a, int b) {
    if (a < 0 || b < 0) {
      throw new IllegalArgumentException("음수는 허용되지 않는다.");
    }
  }

}
