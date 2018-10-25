package com.thread.sync;

import java.util.concurrent.CyclicBarrier;

/**
 * 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 */
public class CyclicBarrierL {
    static int N = 4;
    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier  = new CyclicBarrier(N);
        testCyclicBarrier(barrier);
        //CyclicBarrier是可以重用的
//        testCyclicBarrier(barrier);
    }

    private static void testCyclicBarrier(CyclicBarrier barrier) throws InterruptedException {
        for(int i=0;i<N;i++) {
            Thread.sleep(500);
            new Thread(() -> {
                System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
                try {
                    Thread.sleep(1000);      //以睡眠来模拟写入数据操作
                    System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                    //用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("所有线程写入完毕，继续处理其他任务...");
            }).start();
        }
    }

}
