package com.hy.liu.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Day1008Test {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> t1 = executorService.submit(() -> {

//            Thread.sleep(5000);

            return "123";
        });

        Future<String> t2 = executorService.submit(() -> {

            Thread.sleep(5000);

            return "456";
        });

        executorService.shutdownNow();

        try {
            boolean res = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            if (res) {

                List<String> list = new ArrayList<>();

                list.add(t1.get());
                list.add(t2.get());

                System.out.println(list);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
