package com.rp.sec01;

import reactor.core.publisher.Mono;

import java.io.IOException;

public class Lec02MonoJust {

    public static void main(String[] args) throws IOException {

        // publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received : " + i));
        System.out.println("Press a key to end");
        System.in.read();
    }

}
