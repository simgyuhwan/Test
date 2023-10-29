package com.only.practice.locktest;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Gyuhwan
 */
@DataJpaTest
class TeamTest {

  @Autowired
  private EntityManager em;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @BeforeEach
  void setUp() {

  }

  @Test
  void testLockTwoTables() {

  }

}