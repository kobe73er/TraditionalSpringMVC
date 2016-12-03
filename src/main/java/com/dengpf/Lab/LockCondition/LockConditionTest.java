package com.dengpf.Lab.LockCondition;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by kobe73er on 16/11/25.
 */
public class LockConditionTest {

    static Random rd = new Random(10);

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();


    private static class Account {
        private static int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public float getBalance() {
            return balance;
        }


        public static void add(int num) {
            lock.lock();
            try {
                System.out.println("before added: " + balance + " num: " + num);
                balance += num;
                System.out.println(" after added: " + balance + " num: " + num);
                condition.signalAll();

                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            } finally {
                lock.unlock();

            }
        }

        public static void delete(int num) {
            lock.lock();
            try {

                while (balance < num) {
                    condition.await();
                }
                System.out.println(" before deposited: " + balance + " num: " + num);

                balance -= num;
                System.out.println(" after deposited: " + balance + " num: " + num);
            } catch (InterruptedException ex) {
            } finally {
                lock.unlock();
            }
        }

    }

    private static class CunqianTask implements Runnable {

        private int addedMount;

        public CunqianTask(int addedMount) {
            this.addedMount = addedMount;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Account.add(rd.nextInt(10));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static class RetriveTask implements Runnable {

        private int retiveAmount;

        public RetriveTask(int retiveAmount) {
            this.retiveAmount = retiveAmount;
        }

        @Override
        public void run() {
            while (true) {
                Account.delete(rd.nextInt(10));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Random rd = new Random(10);

        executor.execute(new CunqianTask(rd.nextInt(10)));
        executor.execute(new RetriveTask(rd.nextInt(10)));

        executor.shutdown();

    }
}
