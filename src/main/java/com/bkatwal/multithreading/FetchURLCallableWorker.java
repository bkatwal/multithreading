package com.bkatwal.multithreading;

import java.util.concurrent.Callable;

public class FetchURLCallableWorker implements Callable<String>{

  private String url;

  public FetchURLCallableWorker(String url){
    this.url = url;
  }

  public String call() throws Exception {
    return null;
  }


}
