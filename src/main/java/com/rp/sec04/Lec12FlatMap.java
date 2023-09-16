package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec04.helper.OrderService;
import com.rp.sec04.helper.UserService;

import java.io.BufferedReader;
import java.util.Objects;

public class Lec12FlatMap {

    public static void main(String[] args) {

        BufferedReader reader;

        // if require return type as mono or flux, 90 percent chance flatmap is required
        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                            .filter(p -> Objects.nonNull(p.getPrice()) && Double.parseDouble(p.getPrice()) > 10)
                .subscribe(Util.subscriber());

//        UserService.getUsers()
//            .concatMap(user -> OrderService.getOrders(user.getUserId()))
//            .filter(p -> Objects.nonNull(p.getPrice()) && Double.parseDouble(p.getPrice()) > 10)
//            .subscribe(Util.subscriber());

        Util.sleepSeconds(60);


    }


}
