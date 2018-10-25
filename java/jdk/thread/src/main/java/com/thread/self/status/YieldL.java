package com.thread.self.status;

public class YieldL {
    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            System.out.println("子线程");
        });
        A.start();
        /**
         * yield causes the calling thread to relinquish the CPU.  The
         *        thread is placed at the end of the run queue for its static priority
         *        and another thread is scheduled to run.
         */
        Thread.yield();
        System.out.println("主线程");

    }
}
