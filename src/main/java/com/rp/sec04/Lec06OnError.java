package com.rp.sec04;

import com.rp.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
//                .delayElements(Duration.ofSeconds(1))
                .map(i -> 10 / (5 - i))
               // .onErrorReturn(-1) // return -1 then cancel and stop
               // .onErrorResume(e -> fallback()) // give fallback value then cancel and stop
                .onErrorContinue((err, obj) -> {

                })
                .subscribe(Util.subscriber());


    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }

}
