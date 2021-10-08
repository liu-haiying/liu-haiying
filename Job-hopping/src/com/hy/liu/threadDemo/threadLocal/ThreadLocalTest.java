package com.hy.liu.threadDemo.threadLocal;

public class ThreadLocalTest {

    private static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>(){

        @Override
        protected String initialValue() {

            return "default";
        }
    };

    public static String get() {

        return THREAD_LOCAL.get();
    }

    public static void set(String str) {

        THREAD_LOCAL.set(str);
    }

    public static void remove() {

        THREAD_LOCAL.remove();
    }
}
