package com.only.practice.deadlock;

/**
 * Created by Gyuhwan
 */
public class Deadlock {

  volatile boolean running = true;
  private final Object lock1 = new Object();
  private final Object lock2 = new Object();
  private volatile int count = 0;

  public void method1() {
    synchronized (lock1) {
      System.out.println("Thread 1 : Holding lock 1");

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.getStackTrace();
      }

      System.out.println("Thread 1: Waiting for lock 2...");
      synchronized (lock2) {
        System.out.println("Thread 1: Holding lock 1 & 2");
      }
    }
  }

  public void method2() {
    synchronized (lock2) {
      System.out.println("Thread 2: Holding lock 2...");

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.getStackTrace();
      }

      System.out.println("Thread 2: Waiting for lock 1...");
      synchronized (lock1) {
        System.out.println("Thread 2: Holding lock 1 & 2");
      }
    }
  }

  public void method3() {
    new Thread(() -> {
      int count = 0;
      while (running) {
        count++;
      }
      System.out.println("the current count is : " + count);
    }).start();

    new Thread(() -> {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      running = false;
    }).start();
  }

  public void method4() {
    new Thread(() -> {
      count++;
      System.out.println("first " + count);
    }).start();
    new Thread(() -> {
      count++;
      System.out.println("second " + count);
    }).start();
    new Thread(() -> {
      count++;
      System.out.println("third " + count);
    }).start();
    new Thread(() -> {
      count++;
      System.out.println("fourth " + count);
    }).start();

    count++;
    System.out.println("fifth " + count);

    System.out.println(count);
  }

  public static void main(String[] args) {
    new Deadlock().method4();
  }
}
