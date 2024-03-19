package com.only.practice.context;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TempRepositoryTest {

  @Autowired
  TempRepository repository;

  @DisplayName("insert and select")
  @Test
  void basicInsertTest() {
    Temp temp = Temp.of("first");
    Temp saveTemp = repository.save(temp);
    Temp temp1 = repository.findById(saveTemp.getId()).get();
  }
}