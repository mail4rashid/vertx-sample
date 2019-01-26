package com.mail4rashid.vertx.web;

import com.google.inject.Inject;
import com.mail4rashid.vertx.config.API;
import com.mail4rashid.vertx.config.Events;
import com.mail4rashid.vertx.service.Welcome;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class RestAPIController {
    private Vertx vertx;
    private Welcome service;
    private Router router;

    /**
     * Takes an instance of Vertx for blocking calls and
     * the service it should be using.
     * @param vertx - Vertx instance
     * @param service - Welcome service
     */
    @Inject
    public RestAPIController(Vertx vertx, Welcome service){
        this.vertx = vertx;
        this.service = service;
    }

    /**
     * Return a configured Router instance
     * @return - vertx Router
     */
    public Router getRouter(){
        if(router == null){
            router = Router.router(vertx);
            router.get(API.WELCOME).handler(this::getWelcomeMsg);
        }

        return router;
    }


    /**
     * Handler that uses the event bus and a worker verticle
     * to process the request async
     * @param ctx - RoutingContext for the request
     */
    private void getWelcomeMsg(RoutingContext ctx) {
        String data = new JsonObject()
                .put("name", ctx.request().getParam("name"))
                .encode();

        vertx.eventBus().send(Events.GREET, data, res -> { handleEventBusResponse(res, ctx); });
    }

    /**
     * Helper method for handling event bus responses
     * @param res - Event bus response
     * @param ctx - RoutingContext for the request
     */
    private void handleEventBusResponse(AsyncResult<Message<Object>> res, RoutingContext ctx){
        if(res.succeeded()){
            ctx.response().end(res.result().body().toString());
        }
        else {
            ctx.fail(res.cause());
        }
    }
}
