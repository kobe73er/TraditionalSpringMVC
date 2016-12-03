package com.dengpf.Lab.juc.AtomicInteger;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kobe73er on 16/11/22.
 */
public class AtomicIntegerTest {

    private static AtomicInteger counter = new AtomicInteger(0);

    static boolean end;

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.getAndIncrement();
        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);


        for (int i = 0; i < 1000; i++) {
            executorService.execute(new MyTask());
        }

        executorService.shutdown();

        try {
            end = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(end);
        System.out.println(counter);


    }

}
