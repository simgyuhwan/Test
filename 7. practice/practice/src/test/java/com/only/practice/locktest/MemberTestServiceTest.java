package com.only.practice.locktest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Gyuhwan
 */
@SpringBootTest
class MemberTestServiceTest {

  @Autowired
  private MemberTestService memberTestService;

  @DisplayName("저장 후 조회 테스트")
  @Test
  void saveAndGetCountTest() {
    String firstName = "hello";
    Long count = memberTestService.createAndMemberCnt(firstName);

    assertThat(count).isEqualTo(1);
  }
}