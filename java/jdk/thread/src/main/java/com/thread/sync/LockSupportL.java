package com.thread.sync;

import java.util.concurrent.locks.LockSupport;

/**
 * ①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
 *
 * ②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。
 */
public class LockSupportL {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread A = new Thread(() -> {
            System.out.println("子线程");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //多次调用unpark的效果和调用一次unpark的效果一样
            LockSupport.unpark(mainThread);
        });
        A.start();
        LockSupport.park();
        System.out.println("主线程");
    }
}
