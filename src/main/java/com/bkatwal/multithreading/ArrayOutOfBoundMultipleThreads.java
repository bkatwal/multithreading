package com.bkatwal.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * below program demonstrates, how add method of ArrayList is not thread safe if accessed from
 * multiple threads. At the end of program the ideal size of list should be 2000, but intermittently
 * program will give less then 2000 size and sometimes might get ArrayIndexOutOfBoundException, because
 * in rare case both thread might end up trying to put in same ArrayList location/index and one will
 * throw ArrayIndexOutOfBound for that position
 */
public class ArrayOutOfBoundMultipleThreads {

  static List<Integer> list = new ArrayList<Integer>();

  public static void main(String[] args) throws InterruptedException {

    //check thread safe array list
    new ThreadSafeArrayList().testThreadSafeArray();

   Thread t1 = new Thread(new Runnable() {
     public void run() {
      for(int i=0; i< 1000; i++){
        list.add(i);
      }
     }
   });

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        for(int i=0; i< 1000; i++){
          list.add(i);
        }
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Size of list = " + list.size());
  }
}

class ThreadSafeArrayList{
  List<Integer> list = new CopyOnWriteArrayList<Integer>();

  void testThreadSafeArray() throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        for(int i=0; i< 1000; i++){
          list.add(i);
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        for(int i=0; i< 1000; i++){
          list.add(i);
        }
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Size of thread safe list = " + list.size());
  }
}