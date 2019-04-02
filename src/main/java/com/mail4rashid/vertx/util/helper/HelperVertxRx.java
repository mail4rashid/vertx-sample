package com.mail4rashid.vertx.util.helper;

import io.reactivex.Observable;

public class HelperVertxRx {

    public static Observable<String> addStrings (String first, String second) {
        return Observable.fromCallable(() -> {
            System.out.println("before  addStrings");
            Thread.sleep(5 * 1000);
            System.out.println("after  addStrings");
            return first + second;
        });
    }

    public static Observable<String> addNumbers (int first, int second) {
        return Observable.fromCallable(() -> {
            System.out.println("before  addNumbers");
            Thread.sleep(5 * 1000);
            System.out.println("after  addNumbers");
            return String.valueOf(first + second);
        });
    }

}
