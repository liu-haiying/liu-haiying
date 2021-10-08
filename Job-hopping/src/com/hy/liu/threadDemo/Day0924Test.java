package com.hy.liu.threadDemo;

import com.hy.liu.threadDemo.threadLocal.ThreadLocalTest;

import java.util.concurrent.*;

/**
 * 线程池 <br/>
 * ThreadPoolExecutor的参数 <br/>
 * corePoolSize 核心线程数 就是有任务来首先加入核心线程去执行<br/>
 * maximumPoolSize 最大线程数 在核心线程满了并且阻塞队列也满了的情况下 会开启核心线程之外的线程去执行任务（不超过最大线程）<br/>
 * keepAliveTime 保持时间 在线程数量大于核心线程的时候 如果没有任务继续进来被执行，那么核心线程之外的线程不会被立即销毁，而是等待一段时间，如果这段时间过去了还没有任务进来 就销毁了<br/>
 * unit 保持时间的单位<br/>
 * workQueue 任务阻塞队列 在核心线程满了之后 会加入阻塞队列 阻塞队列满再开启最大线程<br/>
 * threadFactory 创建新线程的工厂<br/>
 * handler 拒绝方式（饱和策略） 当核心线程满了 阻塞队列满了 最大线程也满了就不会继续接收任务，所以有了饱和策略，
 * ThreadPoolExecutor自带了4种（AbortPolicy抛异常，DiscardPolicy不处理，CallerRunsPolicy调用当前线程执行， DiscardOldestPolicy抛弃最早未处理的任务） 当然也可以自定义<br/>
 */
public class Day0924Test {

    public static void main(String[] args) {

        MyThreadOne thread1 = new MyThreadOne();

        MyThreadTwo thread2 = new MyThreadTwo();

        ExecutorService threadPools = Executors.newCachedThreadPool();

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());

        ThreadLocalTest.set("main");

        Future<?> res = threadPools.submit(thread1);

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());

        Future<?> res2 = threadPools.submit(thread2);

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());

        try {

            System.out.println(res2.get());

            threadPools.shutdownNow();

        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                10,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new MyThreadFour(), new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(thread1);
    }
}

class MyThreadOne implements Runnable {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());

        ThreadLocalTest.set("Runnable");

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());
    }
}

class MyThreadTwo implements Callable<String> {

    @Override
    public String call() throws Exception {

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());

        ThreadLocalTest.set("Callable");

        return Thread.currentThread().getName() + "--->" + ThreadLocalTest.get();
    }
}

class MyThreadThree extends Thread{

    @Override
    public void run() {

        ThreadLocalTest.set("Thread");

        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalTest.get());
    }
}

class MyThreadFour implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {

        return new Thread(r);
    }
}