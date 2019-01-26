package com.mail4rashid.vertx.domain;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class Greeting {
    private String greeting;

    public Greeting() {
    }

    public Greeting(String name){
        this.greeting = "Welcome to Vertx:  " + name;
    }
}
