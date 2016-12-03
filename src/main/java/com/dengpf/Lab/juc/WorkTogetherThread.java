package com.dengpf.Lab.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kobe73er on 16/11/17.
 */
public class WorkTogetherThread {

    private static ArrayBlockingQueue<Integer> blockingDeque = new ArrayBlockingQueue(1);


    static int counter = 0;

    private static class printJiShuThread implements Runnable {


        @Override
        public void run() {
         System.out.println("jishu:"+counter++);

        }
    }

    private static class printOshuThread implements Runnable {

        @Override
        public void run() {
            System.out.println("oshu:"+counter++);
        }

    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0;i<50;i++) {
            executorService.submit(new printJiShuThread());
            executorService.submit(new printOshuThread());
        }
        executorService.shutdown();
    }
}
