package com.collection.queue;

import java.util.concurrent.LinkedBlockingDeque;

public class LkedBlockingQueue {
  public static void main(String[] args) throws InterruptedException {
    LinkedBlockingDeque<String> strings = new LinkedBlockingDeque<>();
    String poll = strings.poll();
    String take = strings.take();
    System.out.println(poll);
    System.out.println(take);
  }
}
