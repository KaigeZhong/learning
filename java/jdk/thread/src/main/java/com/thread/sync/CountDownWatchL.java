package com.thread.sync;

import java.util.concurrent.CountDownLatch;

/**
 * ountDownLatch典型用法：
 * 某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为n new CountDownLatch(n) ，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。
 * 一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 */
public class CountDownWatchL {
        public static void main(String[] args) {
            final CountDownLatch latch = new CountDownLatch(2);

            doNewThreadTask(latch);
            doNewThreadTask(latch);

            try {
                System.out.println("主线程： 等待2个子线程执行完毕...");
                latch.await();
                System.out.println("主线程： 2个子线程已经执行完毕");
                System.out.println("主线程： 继续执行主线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    private static void doNewThreadTask(CountDownLatch latch) {
        new Thread(() -> {
            try {
                System.out.println("子线程: "+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("子线程: "+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }){
        }.start();
    }
}
