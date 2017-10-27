package com.kentico.delivery.sample.javaapp;

import java.util.Scanner;

import io.reactivex.*;
import io.reactivex.functions.Consumer;

public class Main {
    public static void main( final String[] args ){
        Flowable.just("Sample rxjava2 app start")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });


        Demo demo = new Demo();
        demo.runTests();

        /*
        System.out.println("Press key and hit enter to finish");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        */
    }
}
