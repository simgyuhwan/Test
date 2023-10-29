package com.only.practice.autotest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Gyuhwan
 */
@SpringBootTest
class CatTest {

  @Autowired
  @Qualifier("cat")
  private Animal pet;

  @DisplayName("의존성 주입 테스트")
  @Test
  void dependencyInjectionTest() {
    pet.makeSound();
  }
}