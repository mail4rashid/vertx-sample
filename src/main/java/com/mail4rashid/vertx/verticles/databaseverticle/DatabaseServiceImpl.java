package com.mail4rashid.vertx.verticles.databaseverticle;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseServiceImpl implements DatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    /**
     *
     * @param msg
     * @param vertx
     * @param readyHandler
     */
    public DatabaseServiceImpl(String msg, Vertx vertx, Handler<AsyncResult<DatabaseService>> readyHandler) {

        boolean succeeded = true;
        if (!succeeded) {
            LOGGER.error("failedFuture Could not open a DatabaseServiceImpl", "thRowable");
            readyHandler.handle(Future.failedFuture("ar.cause()"));
        } else {
            LOGGER.info("DatabaseServiceImpl succeededFuture", "trhowable");
            readyHandler.handle(Future.succeededFuture(this));
        }
    }


    /**
     *
     * @param id
     * @param resultHandler
     * @return
     */
    @Override
    public DatabaseService deleteRecord(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        try {
            //TODO write the code for delete record
        } catch (Exception e) {
            e.printStackTrace();
            resultHandler.handle(Future.failedFuture(HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase()));
        }
        return this;
    }

    /**
     *
     * @param id
     * @param record
     * @param resultHandler
     * @return
     */
    @Override
    public DatabaseService updateRecord(String id, JsonObject record, Handler<AsyncResult<JsonObject>> resultHandler) {
        try {
            //TODO write the code for update record
        } catch (Exception e) {
            e.printStackTrace();
            resultHandler.handle(Future.failedFuture(HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase()));
        }
        return this;
    }

    /**
     *
     * @param id
     * @param record
     * @param resultHandler
     * @return
     */
    @Override
    public DatabaseService createRecord(String id, JsonObject record, Handler<AsyncResult<JsonObject>> resultHandler) {
        try {
            //TODO write the code for create record
        } catch (Exception e) {
            e.printStackTrace();
            resultHandler.handle(Future.failedFuture(HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase()));
        }
        return this;
    }
}
