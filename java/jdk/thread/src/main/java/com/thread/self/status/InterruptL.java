package com.thread.self.status;

public class InterruptL {
    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("子线程: interrupt by other thread");
            }
        });
        A.start();

        System.out.println("主线程: interrupting 子线程");
        A.interrupt();
    }
}
