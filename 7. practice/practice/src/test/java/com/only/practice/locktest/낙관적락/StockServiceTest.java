package com.only.practice.locktest.낙관적락;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * Created by Gyuhwan
 */
@SpringBootTest
class StockServiceTest {

  @Autowired
  private StockService stockService;

  @Autowired
  private StockRepository stockRepository;

  @Test
  void 낙관적락_재고_선점_테스트() throws InterruptedException {
    Stock savedStock = 재고_1개_생성();
    int numberOfThread = 3;

    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThread);

    Future<?> future1 = executorService.submit(() -> stockService.decrease(savedStock.getId(), 1L));
    Future<?> future2 = executorService.submit(() -> stockService.decrease(savedStock.getId(), 1L));
    Future<?> future3 = executorService.submit(() -> stockService.decrease(savedStock.getId(), 1L));

    Exception exception = new Exception();

    try {
      future1.get();
      future2.get();
      future3.get();
    } catch (ExecutionException e) {
      exception = (Exception) e.getCause();
    }

    assertTrue(exception instanceof OptimisticLockingFailureException);
  }

  private Stock 재고_1개_생성() {
    Stock stock = Stock.createStock("first", 1L);
    return stockRepository.save(stock);
  }
}