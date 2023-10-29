package com.only.practice.wefun;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class 관리비Test {

  @DisplayName("첫 번째 케이스")
  @Test
  void firstCase() {
    // given
    int day = 6;
    int k = 1;
    int[] expected = {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0};
    관리비 me = new 관리비();

    // when
    int[] result = me.solution(day, k);

    // then
    assertArrayEquals(expected, result);
  }

  @DisplayName("두 번째 케이스")
  @Test
  void secondCase() {
    // given
    int day = 6;
    int k = 25;
    int[] expected = {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0};
    관리비 me = new 관리비();

    // when
    int[] result = me.solution(day, k);

    // then
    assertArrayEquals(expected, result);
  }

}