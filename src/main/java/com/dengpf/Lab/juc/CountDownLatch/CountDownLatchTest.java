package com.dengpf.Lab.juc.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kobe73er on 16/11/22.
 */
public class CountDownLatchTest {

    private static class Worker implements Runnable {
        private CountDownLatch countDownLatch;
        private String name;

        public Worker(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "is working...");
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "is Done!");
            countDownLatch.countDown();

        }
    }


    private static class Boss implements Runnable {

        private CountDownLatch countDownLatch;

        public Boss(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("waiting for workers finish doing");
            try {
                this.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Boss is checking");

        }
    }


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        Worker w1 = new Worker(latch, "张三");
        Worker w2 = new Worker(latch, "李四");
        Worker w3 = new Worker(latch, "王二");

        Boss boss = new Boss(latch);

        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(boss);

        executor.shutdown();
    }
}
