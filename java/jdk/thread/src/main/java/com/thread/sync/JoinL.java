package com.thread.sync;

public class JoinL {
    /**
     * A.join()方法阻塞调用此方法的线程(calling thread)，直到线程A完成，此线程再继续
     */
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(() -> {
            System.out.println("子线程: beginning");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程: done");
        });
        A.start();
        A.join();
        System.out.println("主线程: done");
    }
}
