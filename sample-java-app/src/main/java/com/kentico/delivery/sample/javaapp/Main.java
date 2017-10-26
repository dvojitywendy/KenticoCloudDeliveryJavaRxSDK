package com.kentico.delivery.sample.javaapp;

import io.reactivex.*;
import io.reactivex.functions.Consumer;

public class Main {
    public static void main( final String[] args ){
        System.out.println("Sample rxjava2 app start");


        //To show error, simply uncomment the following
        Flowable.just("Hello world")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });


        //Demo demo = new Demo(getDeliveryService());
        //demo.runTests();
    }
}
