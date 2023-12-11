package com.only.practice.cuncurrency;

public class SynchExample {
    //    static class Counter {
//        private static Object object = new Object();
//        public static int count = 0;
//
//        public static void increment() {
//            synchronized (object) {
//                count++;
//            }
//        }
//    }
    static class Counter {
        public static int count = 0;

        public void increment() {
            synchronized (this) {
                Counter.count++;
            }
        }
    }

    static class MyRunnable implements Runnable {
        Counter counter;

        public MyRunnable(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        Counter counter = new Counter();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyRunnable(counter));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println(Counter.count);
    }
}
