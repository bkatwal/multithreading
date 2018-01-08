package com.bkatwal.multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer consumer pattern using blocking queue
 */
public class ProducerConsumerBlockingQueue {

  public static void main(String[] args) {
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

    Producer1 producer1 =  new Producer1(blockingQueue);

    Consumer consumer =  new Consumer(blockingQueue);

    new Thread(producer1).start();

    new Thread(consumer).start();


  }


}

class Producer1 implements Runnable {

  private BlockingQueue<Integer> blockingQueue;

  private Random random = new Random();

  public Producer1(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void produce() throws InterruptedException {
    while (true) {
      Integer newInt = this.random.nextInt(100);
      blockingQueue.put(newInt);
      System.out.println("put value in queue: " + newInt);
      Thread.sleep(500);
    }
  }

  public void run() {
    try {
      produce();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Consumer implements Runnable {

  private BlockingQueue<Integer> blockingQueue;

  public Consumer(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }
  public void consume() throws InterruptedException {

    while(true){
      Integer intFromQueue = blockingQueue.take();
      System.out.println("Taken value from queue: " + intFromQueue);
      Thread.sleep(500);//to simulate some work by consumer
    }
  }

  public void run() {
    try {
      consume();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
