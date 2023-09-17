package com.rp.sec06;

import com.rp.courseutil.Util;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelDemo {

  public static void main(String[] args) {

    Flux.range(1, 1000)
        .log()
        .parallel(10)
        .runOn(Schedulers.parallel())
        .map(i -> {
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          System.out.println("maper Thread " + Thread.currentThread().getName()
              + " received: " + i);
          return i;
        }).runOn(Schedulers.boundedElastic())
        .subscribe(i-> {
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          System.out.println("subscribe Thread " + Thread.currentThread().getName()
              + " received: " + i);
        });



    Util.sleepSeconds(60);
  }

}
