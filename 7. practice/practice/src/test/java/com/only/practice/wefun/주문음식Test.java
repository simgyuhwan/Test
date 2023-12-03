package com.only.practice.wefun;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class 주문음식Test {

  @DisplayName("첫 번째 케이스")
  @Test
  void firstCase() {
    // given
    String[] orders = {"alex pizza pasta", "alex pizza pizza", "alex noodle",
        "bob pasta", "bob noodle sandwich pasta", "bob steak noodle"};
    String[] expected = {"bob"};
    주문음식 me = new 주문음식();

    // when
    String[] solution = me.solution(orders);

    // then
    Assertions.assertArrayEquals(expected, solution);
  }

}