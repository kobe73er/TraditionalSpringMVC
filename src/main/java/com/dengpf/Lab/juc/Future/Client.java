package com.dengpf.Lab.juc.Future;

import java.io.File;
import java.util.concurrent.*;

/**
 * Created by kobe73er on 16/11/29.
 */
public class Client {

    private static class Task implements Callable {

        private int counter;

        public Task(int counter) {
            this.counter = counter;
        }

        @Override
        public Object call() throws Exception {

            return counter;
        }
    }

    private static class TaskCountFileNum implements Callable {

        private CountDownLatch countDownLatch;
        private String location;

        private File file;


        public TaskCountFileNum(String location) {
            this.location = location;
            file = new File(location);
        }

        public TaskCountFileNum(CountDownLatch countDownLatch, String location) {
            this.countDownLatch = countDownLatch;
            this.location = location;
            file = new File(location);

        }

        // 递归遍历
        private static int getDirectory(File file) {
            File flist[] = file.listFiles();
            int fileCount = 0;

            if (flist == null || flist.length == 0) {
                return 0;
            }
            for (File f : flist) {
                if (f.isDirectory()) {
                    fileCount += getDirectory(f);
                } else {
                    fileCount++;

                }
            }
            return fileCount;
        }

        @Override
        public Object call() throws Exception {
            countDownLatch.countDown();
            return getDirectory(file);
        }
    }

    private static class CountTotal implements Callable {

        private CountDownLatch countDownLatch;
        private Future[] futures;


        public CountTotal(CountDownLatch countDownLatch, Future... futures) {
            this.countDownLatch = countDownLatch;
            this.futures = futures;
        }


        @Override
        public Object call() throws Exception {
            countDownLatch.await();
            int totalNum = 0;
            for (Future item : futures) {
                totalNum += (Integer) item.get();
            }
            return totalNum;
        }
    }


    public static void main(String args[]) {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future f1 = executorService.submit(new TaskCountFileNum(countDownLatch, "/Users/kobe73er/Downloads/TraditionalSpringMVC/src/main"));
        Future f2 = executorService.submit(new TaskCountFileNum(countDownLatch, "/Users/kobe73er/Downloads/TraditionalSpringMVC/src/test"));
//        Future f3 = executorService.submit(new TaskCountFileNum("/Users/kobe73er/Downloads/TraditionalSpringMVC"));
        Future f3 = executorService.submit(new CountTotal(countDownLatch, f1, f2));
        executorService.shutdown();


        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println("total: " + f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
