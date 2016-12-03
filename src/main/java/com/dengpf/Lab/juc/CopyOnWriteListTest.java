package com.dengpf.Lab.juc;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kobe73er on 16/11/17.
 */
public class CopyOnWriteListTest {


    static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

    static class readThread implements Runnable {

        Random random = new Random();

        @Override
        public void run() {
            for (int k = 0; k < 1000; k++) {
                int pos = random.nextInt(100);
                copyOnWriteArrayList.get(pos);
            }
        }
    }


    static class WriteThread implements Runnable {

        Random random = new Random();

        @Override
        public void run() {
            for (int k = 0; k < 1000; k++) {
                int pos = random.nextInt(100);
                copyOnWriteArrayList.add(pos);
            }
        }
    }

    public static void main(String args[]) {


        for (int i = 0; i < 100; i++) {
            copyOnWriteArrayList.add(i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long start = System.currentTimeMillis();
        executorService.execute(new readThread());
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        long start1 = System.currentTimeMillis();
        executorService.execute(new WriteThread());
        long end1= System.currentTimeMillis();
        System.out.println(end1 - start1);

        executorService.shutdown();

    }
}

