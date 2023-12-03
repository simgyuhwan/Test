package com.only.practice.nullcheck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class BasicCalculatorTest {

  @Test
  void test() {
    BasicCalculator calculator = new BasicCalculator();
    int result = calculator.add(1, 2);
    assertThat(result).isEqualTo(3);
  }

}