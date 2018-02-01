package com.bkatwal.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Ref: https://stackoverflow.com/questions/48558432/how-do-i-prove-programatically-that-stringbuilder-is-not-threadsafe-in-java
 */
public class StringBuilderThreadSafeTest {

  private static final int CHARS_PER_THREAD = 1_000_000;
  private static final int NUMBER_OF_THREADS = 4;

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    StringBuilder builder = new StringBuilder();
    Runnable appender = () -> {
      for (int i = 0; i < CHARS_PER_THREAD; i++) {
        builder.append('A');
      }
    };
    ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    List<Future<?>> futures = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      futures.add(executorService.submit(appender));
    }
    for (Future<?> future : futures) {
      future.get();
    }
    executorService.shutdown();
    String builtString = builder.toString();

  }
}

