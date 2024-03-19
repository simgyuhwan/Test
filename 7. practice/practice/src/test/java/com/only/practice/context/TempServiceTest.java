package com.only.practice.context;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TempServiceTest {
  @Autowired
  TempService service;

  @Autowired
  TempRepository repository;

  @BeforeEach()
  void init() {
    repository.deleteAllInBatch();
  }
  @Test
  @DisplayName("트랜잭션 + 트랜잭션 with findByName")
  void doubleTransaction() {
    service.question1();
  }

  @Test
  @DisplayName("트랜잭션 + 트랜잭션 with findById")
  void doubleTransactionWithFindById() {
    service.question2();
  }
  @Test
  @DisplayName("트랜잭션 + x with findByName")
  void oneTransactionWithFindById() {
    service.question3();
  }

  @Test
  @DisplayName("트랜잭션 + 트랜잭션(Requires_New)")
  void oneTransactionNews() {
    service.question4();
  }

  @Test
  @DisplayName("트랜잭션 + 트랜잭션(Requires_new) with findById")
  void transactionRequiresNew() {
    service.question5();
  }

  @Test
  @DisplayName("x + x")
  void noneTransaction() {
    service.question6();
  }

  @Test
  @DisplayName("x + 트랜잭션")
  void afterTransaction() {
    service.question7();
  }
}