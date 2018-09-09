package com.collection.queue;

import java.util.concurrent.SynchronousQueue;

public class SynchBlockingQueque {
  public final static SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

  public static void main(String[] args) throws Exception {
//    putAndTake();
//    offer();

  }

  static void offer() throws InterruptedException {
    SynchronousQueue<String> strings = new SynchronousQueue<>(true);
    Thread producer = new Thread(() -> {
      try {
        String take = strings.take();
        System.out.println(take);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    boolean a = strings.offer("a");
    System.out.println(a);
    producer.start();
    Thread.sleep(1000L);
    boolean b = strings.offer("b");
    System.out.println(b);
  }
  static void putAndTake() throws InterruptedException {
    Thread producer = new Thread(() -> {
      try {
        QUEUE.put("put");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread consumer = new Thread(() -> {
      try {
        String take = QUEUE.take();
        System.out.println(take);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    producer.start();
    Thread.sleep(1000L);
    consumer.start();
  }
}
