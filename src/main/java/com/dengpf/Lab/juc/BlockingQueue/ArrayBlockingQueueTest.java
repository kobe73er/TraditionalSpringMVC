package com.dengpf.Lab.juc.BlockingQueue;

import java.util.concurrent.*;

/**
 * Created by kobe73er on 16/11/30.
 */
public class ArrayBlockingQueueTest {

    private static ArrayBlockingQueue blockingDeque = new ArrayBlockingQueue<Integer>(1);

    private static class Consumer implements Callable {


        @Override
        public Object call() throws Exception {
            while (true) {
                Integer ci = (Integer) blockingDeque.take();
                System.out.println("consumer consume:" + ci);

                Thread.sleep(1000);
            }
        }
    }

    private static class Producer implements Callable {

        @Override
        public Object call() throws Exception {
            while (true) {

                blockingDeque.put(1);
                System.out.println("producer produce:" + 1);
                Thread.sleep(1000);

            }
        }
    }


    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Consumer());
        executorService.submit(new Producer());
        executorService.shutdown();

    }
}
