package com.thread.sync.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * state -> The synchronization state
 * exclusiveOwnerThread -> The current owner of exclusive mode synchronization.
 *
 * 1. 尝试获取锁:1)如果state等于0且队列中没有等待线程则获取锁。2)如果state不等于0，但exclusiveOwnerThread等于当前线程，则也获取锁即可重入。
 *
 * 2. 如果获取锁失败，则添加到对接尾部，并进去for(;;)循环，但立马会park即进去阻塞状态
 *
 * 3. 当释放锁时，会unpark即唤醒队列中头部线程，这样这样对应线程则继续执行for(;;)获取锁。
 *
 * 4. 非公平锁与公平锁唯一不同就是在首次获取锁时，不管队列中是否有线程等待，都会首先尝试获取锁，当获取不到时，会执行跟公平锁一样的逻辑。
 *
 * 释放锁时，两者无差别
 */
public class ReentrantLockL {
    static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(() -> {
            syncMethod();
        }).start();

        new Thread(() -> {
            syncMethod();
        }).start();
    }

    public static void syncMethod() {
        reentrantLock.lock();

        System.out.println(Thread.currentThread().getName() + ": syncMethod begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ": syncMethod end");

        reentrantLock.unlock();
    }
}
