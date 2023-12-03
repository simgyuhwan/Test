package com.only.practice.wefun;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class 토끼Test {

  @DisplayName("첫 번째 케이스")
  @Test
  void firstCase() {
    // given
    int n = 9;
    int k = 3;
    int expected = 3;
    토끼 me = new 토끼();

    // when
    int result = me.solution(n, k);

    // then
    assertThat(result).isEqualTo(expected);
  }

}