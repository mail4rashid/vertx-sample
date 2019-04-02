package com.mail4rashid.vertx.verticles.databaseverticle;

import com.mail4rashid.vertx.util.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;

public class DatabaseVerticle extends AbstractVerticle {

    /**
     *
     * @param startFuture
     * @throws Exception
     */
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);
        System.out.println("DatabaseService start: ");
        DatabaseService.create("some message here", vertx, ready -> {
            if (ready.succeeded()) {
                ServiceBinder binder = new ServiceBinder(vertx);
                binder.setAddress(Constants.DB_QUEUE_NAME)
                        .register(DatabaseService.class, ready.result());
                System.out.println("DatabaseService Success: ");

                if (startFuture.tryComplete())
                    startFuture.complete();

            } else {
                System.out.println("DatabaseService Failed: ");
                startFuture.fail(ready.cause());
            }
        });
    }
}
