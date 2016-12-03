package com.dengpf.Lab.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kobe73er on 16/11/17.
 */


public class MultiThreadTest implements Runnable {

    private int threadId;

    public MultiThreadTest(int threadId) {
        this.threadId = threadId;
    }


    public static void main(String args[]) {
        MultiThreadTest m1 = new MultiThreadTest(0);
        MultiThreadTest m2 = new MultiThreadTest(1);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(m1);
        executorService.execute(m2);

    }

    static int counter = 0;


    @Override
    public void run() {
        synchronized (MultiThreadTest.class) {
            while (counter < 100) {
                if (counter % 2 == threadId) {
                    System.out.println(threadId + ":" + counter);
                    counter++;
                    MultiThreadTest.class.notifyAll();

                } else {
                    try {
                        MultiThreadTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


}
