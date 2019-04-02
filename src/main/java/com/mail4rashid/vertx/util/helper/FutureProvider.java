package com.mail4rashid.vertx.util.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FutureProvider {
    public CompletableFuture<List<String>> retrieveClassA() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("before  retrieveClassA");
                Thread.sleep(10 * 1000L);
                System.out.println("after  retrieveClassA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> stringList = new ArrayList<>();
            stringList.add("retrieveClassA");
            return stringList;
        });
//    }).thenCompose(a->retrieveClassB()).thenCompose(b->retrieveClassC());
    }

    public CompletableFuture<List<String>> retrieveClassB() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("before  retrieveClassB");
                Thread.sleep(10 * 1000L);
                System.out.println("after  retrieveClassB");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> stringList = new ArrayList<>();
            stringList.add("retrieveClassB");
            return stringList;
        });
    }

    public CompletableFuture<List<String>> retrieveClassC() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("before  retrieveClassC");
                Thread.sleep(10 * 1000L);
                System.out.println("after  retrieveClassC");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> stringList = new ArrayList<>();
            stringList.add("retrieveClassC");
            return stringList;
        });
    }


}