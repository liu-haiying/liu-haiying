package com.hy.liu.example;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CreateThread {

    private final static ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "default");

    /**
     * 通过实现Runnable接口创建线程
     */
    public static class ThreadDemo1 implements Runnable {

        @Override
        public void run() {

            THREAD_LOCAL.set("RUNNABLE");

            System.out.println("你的业务代码 ---> runnable接口创建线程" + THREAD_LOCAL.get());
        }
    }

    /**
     * 通过继承Thread类创建线程
     */
    public static class ThreadDemo2 extends Thread {

        @Override
        public void run() {

            THREAD_LOCAL.set("THREAD");

            System.out.println("你的业务代码 ---> 继承Thread类创建线程" + THREAD_LOCAL.get());
        }
    }

    /**
     * 通过实现callable接口创建线程
     */
    public static class ThreadDemo3 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            THREAD_LOCAL.set("CALLABLE");

            System.out.println("你的业务代码 ---> 通过实现callable接口创建线程" + THREAD_LOCAL.get());

            return 100;
        }
    }

    /**
     * 主线程
     * @param args
     */
    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(new ThreadDemo1(), "线程1");
        thread.start();

        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        threadDemo2.start();

        FutureTask<Integer> demo = new FutureTask<Integer>(new ThreadDemo3());
        Thread thread1 = new Thread(demo);
        thread1.start();

        System.out.println(demo.get());
        System.out.println(        THREAD_LOCAL.get());
    }
}
