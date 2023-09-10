package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            System.out.println("emitting by thread " + Thread.currentThread().getName());
            // synchronousSink allows only one-time invocation-call to emit item
            // synchronousSink does not need main loop as in fluxSink
            // synchronousSink runs the loop in below to emit countries
            synchronousSink.next(Util.faker().country().name());

            // synchronousSink.complete(); // complete after first item emission then exit loop
            //synchronousSink.error(new RuntimeException("oops"));
        })
        .take(2)
        .subscribe(Util.subscriber());


    }

}
