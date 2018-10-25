package com.thread.sync;

import java.util.concurrent.Semaphore;

/**
 * Semaphore可以控同时访问的线程个数
 */
public class SemaphoreL {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); //机器数目
        for(int i=0;i<3;i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("工人"+ Thread.currentThread().getName() +"占用一个机器在生产...");
                    Thread.sleep(2000);
                    System.out.println("工人"+ Thread.currentThread().getName() +"释放出机器");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
