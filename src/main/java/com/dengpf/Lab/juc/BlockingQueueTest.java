package com.dengpf.Lab.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kobe73er on 16/11/17.
 */


public class BlockingQueueTest {

    private static ArrayBlockingQueue blockingDeque = new ArrayBlockingQueue(1);

    static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Integer ele = (Integer) blockingDeque.take();
                    System.out.println("take:" + ele);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable {
        int i = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("produce:" + i);
                    blockingDeque.put(i++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Producer());
        executorService.execute(new Consumer());


        executorService.shutdown();
    }
}
