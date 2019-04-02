package com.mail4rashid.vertx.verticles.service;

import com.google.inject.Inject;
import com.mail4rashid.vertx.config.Events;
import com.mail4rashid.vertx.domain.Greeting;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;



/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class WorkerVerticle extends AbstractVerticle {
    private Welcome service;

    /**
     * Constructor takes a service this verticle should be using.
     * @param service - Welcome service instance
     */
    @Inject
    public WorkerVerticle(Welcome service) {
        this.service = service;
    }

    private static String getCurrentThreadInfo() {
        return Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + ")";
    }

    /**
     * Start method gets called when the verticle is deployed.
     *
     * @param done
     */
    @Override
    public void start(Future<Void> done){

        MessageConsumer<String> consumer= vertx.eventBus().consumer(Events.GREET);

        consumer.handler(m -> {
            // reply to the sender or fail the message.
            try{

                JsonObject data = new JsonObject(m.body());

                 service.getGreetMesage(data.getString("name"))
                        .flatMap((Function<Greeting, ObservableSource<Greeting>>) Observable::just)
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Observer<Greeting>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                d.dispose();
                            }

                            @Override
                            public void onNext(Greeting greeting) {
                                m.reply(Json.encode(greeting));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }catch (EncodeException e){
                m.fail(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), "Failed to encode data.");
            }
        });

        done.complete();
    }
}
