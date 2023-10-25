package com.rp.sec08;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.error(new RuntimeException("oops"));
        Flux<String> flux3 = Flux.just("c", "d", "e");

        //final Flux<String> combinedFlux = flux1.concatWith(flux2);
        // concat : one by one sequence
        Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);


        flux.subscribe(Util.subscriber());


    }

}
