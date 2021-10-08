package com.hy.liu.threadDemo;

public class Day0923Test {

    public static void main(String[] args) {

        Object resource1 = new Object();

        Object resource2 = new Object();


        Thread thread1 = new Thread(() -> {

            synchronized (resource1) {

                System.out.println(Thread.currentThread().getName() + "--->我现在持有资源1");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                System.out.println(Thread.currentThread().getName() + "--->我在等待资源2");

                synchronized (resource2) {

                    System.out.println(Thread.currentThread().getName() + "--->我获取到了资源2");
                }
            }
        }, "线程1");

        Thread thread2 = new Thread(() -> {

            synchronized (resource1) {

                System.out.println(Thread.currentThread().getName() + "--->我现在持有资源1");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                System.out.println(Thread.currentThread().getName() + "--->我在等待资源2");

                synchronized (resource2) {

                    System.out.println(Thread.currentThread().getName() + "--->我获取到了资源2");
                }
            }
        }, "线程2");

        thread1.start();
        thread2.start();
    }
}
