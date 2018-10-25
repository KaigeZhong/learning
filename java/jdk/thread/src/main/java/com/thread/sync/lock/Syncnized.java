package com.thread.sync.lock;

public class Syncnized {
    static Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            syncMethod();
        }).start();

        new Thread(() -> {
            syncMethod();
        }).start();
    }

    public static void syncMethod() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": syncMethod begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ": syncMethod end");
        }
    }
}
