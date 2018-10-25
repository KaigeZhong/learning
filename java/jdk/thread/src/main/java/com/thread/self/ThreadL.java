package com.thread.self;

public class ThreadL {
    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            System.out.println("子线程");
        });
        A.start();
        System.out.println("主线程");
    }
}
