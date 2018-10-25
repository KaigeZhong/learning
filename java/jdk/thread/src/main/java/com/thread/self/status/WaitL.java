package com.thread.self.status;

public class WaitL {
    public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread A = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程: 执行完毕");
            synchronized (obj) {
                obj.notify();
            }
        });
        A.start();
        System.out.println("主线程: 等待子线程执行");
        synchronized (obj) {
            obj.wait();
        }
        System.out.println("主线程: done");
    }
}
