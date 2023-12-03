package com.only.practice.nullcheck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class AnnotationCheckTest {

  @DisplayName("롬복 @Nonnull test")
  @Test
  void nonNullTest() {
    assertThatThrownBy(() -> new AnnotationCheck(null))
        .isInstanceOf(NullPointerException.class);
  }

}