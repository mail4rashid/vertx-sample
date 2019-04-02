package com.mail4rashid.vertx.verticles.databaseverticle;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface DatabaseService {

    @Fluent
    DatabaseService deleteRecord(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    DatabaseService updateRecord(String id, JsonObject record, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    DatabaseService createRecord(String id, JsonObject record, Handler<AsyncResult<JsonObject>> resultHandler);


    // A couple of factory methods to create an instance and a proxy
    static DatabaseService create(String msg, Vertx vertx, Handler<AsyncResult<DatabaseService>> readyHandler)  {
        return new DatabaseServiceImpl(msg, vertx, readyHandler);
    }

    /**
     *
     * @param vertx
     * @param address
     * @return
     */
    static DatabaseService createProxy(Vertx vertx,
                                       String address) {
        return new DatabaseServiceVertxEBProxy(vertx, address);
    }
}