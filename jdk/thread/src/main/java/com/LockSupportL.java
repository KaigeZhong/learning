package com;

import java.util.concurrent.locks.LockSupport;

/**
 * ①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
 *
 * ②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。
 */
public class LockSupportL {
    public static void main(String[] args) {
        final Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for(int i=0;i<10;i++){
                sum+=i;
            }
            LockSupport.park();
            System.out.println(sum);
        });
        A.start();
        //Thread.sleep(1000);
        //多次调用unpark的效果和调用一次unpark的效果一样
        LockSupport.unpark(A);
    }
}
