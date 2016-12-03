package com.dengpf.Lab.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kobe73er on 16/11/17.
 */
public class ExampleOfCAndP {

    private static Buffer buffer = new Buffer();

    private static class Buffer {
        private static final int CAPACITY = 1; // buffer size
        private java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>();


        private static Lock lock = new ReentrantLock();

        private static Condition notFull = lock.newCondition();
        private static Condition notEmpty = lock.newCondition();


        public void write(int value) {
            lock.lock();

            try {
                while (queue.size() == CAPACITY) {
                    notFull.await();
                }
                queue.offer(value);
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


        public int read() {
            int value = 0;
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                value = queue.remove();
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }


    private static class Producer implements Runnable {

        @Override
        public void run() {
            int i = 1;
            while (true) {
                System.out.println("write:" + i);
                buffer.write(i++);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("read:"+buffer.read());
                buffer.read();
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Producer());
        executor.execute(new Consumer());
        executor.shutdown();
    }

}
