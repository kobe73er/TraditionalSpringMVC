package com.dengpf.Lab.juc.ThreadLocal;

import org.hibernate.annotations.Synchronize;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kobe73er on 16/11/22.
 */
public class ThreadLocalTest {
    static int counter = 0;

    private static class MyRunnable implements Runnable {


        @Override
        public void run() {
            synchronized (ThreadLocalTest.class) {
                counter++;
            }

        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100000; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(counter);
    }

}
