package com.only.practice.deadlock;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by Gyuhwan
 */
class DeadlockTest {

  @DisplayName("데드락 테스트")
  @Test
  void deadLockTest() {
    Deadlock deadlock = new Deadlock();
    ExecutorService service = Executors.newFixedThreadPool(2);

    Future<?> future1 = service.submit(deadlock::method1);
    Future<?> future2 = service.submit(deadlock::method2);

    try {
      future1.get(1, TimeUnit.SECONDS);
      future2.get(1, TimeUnit.SECONDS);
    } catch (TimeoutException | InterruptedException | ExecutionException e) {
      e.printStackTrace();
      fail("Deadlock occurred");
    } finally {
      service.shutdown();
    }
  }


  @DisplayName("캐시 메모리 사용 확인")
  @Test
  void checkCacheMemoryUsage() {
    new Deadlock().method3();
  }

}