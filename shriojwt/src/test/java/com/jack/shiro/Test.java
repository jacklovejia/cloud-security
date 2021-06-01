package com.jack.shiro;

/**
 * inheritableThreadLocal
 */
public class Test {
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        String value = "aaa";
        threadLocal.set(value);
        inheritableThreadLocal.set(value);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadLocal:"+threadLocal.get());
                System.out.println("inheritableThreadLocal:"+inheritableThreadLocal.get());
            }
        }).start();
    }
}
