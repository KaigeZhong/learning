package com.thread.apply;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerL {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        /**
         * atomicInteger是基于乐观锁CAS的
         * CAS本身具备ABA问题:
         * 在CAS算法中，需要取出内存中某时刻的数据（由用户完成），在下一时刻比较并替换（由CPU完成，该操作是原子的）。这个时间差中，会导致数据的变化。
         *
         * 假设如下事件序列：
         *
         * 线程 1 从内存位置V中取出A。
         * 线程 2 从位置V中取出A。
         * 线程 2 进行了一些操作，将B写入位置V。
         * 线程 2 将A再次写入位置V。
         * 线程 1 进行CAS操作，发现位置V中仍然是A，操作成功。
         * 尽管线程 1 的CAS操作成功，但不代表这个过程没有问题——对于线程 1 ，线程 2 的修改已经丢失。
         *
         *
         *
         * AtomicStampedReference 解决ABA问题
         */
        System.out.println(atomicInteger.incrementAndGet());
    }
}
