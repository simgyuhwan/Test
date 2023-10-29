package com.only.practice.wefun;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class 보험상품Test {

  @DisplayName("첫 번째 케이스")
  @Test
  void firstCase() {
    // given
    int[] arr = {2, 5, 3, 8, 1};
    int k = 3;
    int t = 11;
    int expected = 6;
    보험상품 me = new 보험상품();

    // when
    int result = me.solution(arr, k, t);

    // then
    assertThat(result).isEqualTo(expected);
  }

  @DisplayName("두 번째 케이스")
  @Test
  void secondCase() {
    // given
    int[] arr = {1, 1, 2, 2};
    int k = 2;
    int t = 3;
    int expected = 5;
    보험상품 me = new 보험상품();

    // when
    int result = me.solution(arr, k, t);

    // then
    assertThat(result).isEqualTo(expected);
  }

}