package com.mail4rashid.vertx.verticles.service;

import com.mail4rashid.vertx.domain.Greeting;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;

import java.util.concurrent.Callable;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class Welcome {
    public Welcome(){
        // initiate your class
    }

    /**
     * Return a greeting for a particular name.
     * @param name - Name to greet
     * @return - Greeting object
     */
    public Greeting greet(String name){
        try{
            Thread.sleep(1500);
            return new Greeting(name);
        }catch (InterruptedException e){
            throw new RuntimeException("This is a safe message");
        }
    }

    public Observable<Greeting> getGreetMesage(String name) {
        return new ObservableFromCallable<>(new Callable<Greeting>() {
            @Override
            public Greeting call() throws Exception {
                Thread.sleep(1500);
                return new Greeting(name);
            }
        });
    }
}
