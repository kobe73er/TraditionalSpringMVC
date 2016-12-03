package com.dengpf.Lab.juc;

import java.util.concurrent.*;

/**
 * Created by kobe73er on 16/11/18.
 */
public class CallableTest {

    private static class C1 implements Callable {

        @Override
        public Object call() throws Exception {

            return "c1";
        }
    }

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future future = executorService.submit(new C1());

        try {
           System.out.print(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
