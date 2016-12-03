package com.dengpf.Lab.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kobe73er on 16/11/17.
 */
public class PassHole {


    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Foot {

        private static Lock lock = new ReentrantLock();
        private static int passTime = 5000;

        public static void Pass(Person passPerson) {
            lock.lock();
            System.out.println(passPerson.getName() + "is passing");
            while (passTime > 0) {
                passTime--;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();

        }
    }


    private static class PassThrough implements Runnable {

        private Person passPerson;

        public PassThrough(Person passPerson) {
            this.passPerson = passPerson;
        }

        @Override
        public void run() {
            Foot.Pass(passPerson);
        }
    }

    public static void main(String args[]){
        ExecutorService executer = Executors.newCachedThreadPool();
        Random random = new Random();
        for(int i=0;i<10;i++){

            executer.submit(new PassThrough(new Person(random.nextInt()+" ")));
        }
        executer.shutdown();
    }
}
