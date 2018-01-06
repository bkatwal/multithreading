package com.bkatwal.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    for(int i=0; i<10; i++){
      executorService.submit(new Task(i));
    }

    System.out.println("All task submitted:");


    executorService.shutdown();

    //Will wait for all task to complete, max wait time is 1 minute
    executorService.awaitTermination(1, TimeUnit.MINUTES);

    System.out.println("All task completed");

  }

}


class Task implements Runnable{

  int taskId;

  public Task(int taskId){
    this.taskId = taskId;
  }

  public void run() {
    System.out.println("Start task===" + this.taskId);
    try {
      //some task
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Stop Task===" + this.taskId);
  }
}