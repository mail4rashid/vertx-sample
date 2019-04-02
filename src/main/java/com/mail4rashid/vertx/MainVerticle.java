package com.mail4rashid.vertx;

import com.google.inject.Inject;
import com.mail4rashid.vertx.config.AppConfig;
import com.mail4rashid.vertx.util.helper.FutureProvider;
import com.mail4rashid.vertx.util.helper.HelperVertxRx;
import com.mail4rashid.vertx.verticles.service.WorkerVerticle;
import com.mail4rashid.vertx.web.HttpServerVerticle;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class MainVerticle extends AbstractVerticle {

    /**
     * MainVerticle method that instantiates Vertx and deploys the launcher verticle
     * @param args
     */
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        deploy(vertx, ServiceLauncher.class, new DeploymentOptions());
    }

    /**
     * Service Launcher verticle will deploy all our service verticles
     * including server and workers.
     */
    private static class ServiceLauncher extends AbstractVerticle{
        private AppConfig appConfig;

        /**
         * Constructor takes AppConfig that can contain different deployment
         * information.
         * @param appConfig - AppConfig instance
         */
        @Inject
        public ServiceLauncher(AppConfig appConfig) {
            this.appConfig = appConfig;
        }

        /**
         * Start method uses CompositeFuture to deploy all required verticles
         *
         * @param done
         */
        @Override
        public void start(Future<Void> done) {

            DeploymentOptions serverOpts = new DeploymentOptions()
                    .setWorkerPoolSize(appConfig.getServerThreads());

            DeploymentOptions workerOpts = new DeploymentOptions()
                    .setWorker(true)
                    .setWorkerPoolSize(appConfig.getWorkerThreads());

            CompositeFuture.all(
                    deploy(vertx, HttpServerVerticle.class, serverOpts),
                    deploy(vertx, WorkerVerticle.class, workerOpts)
            ).setHandler(r -> {
                if (r.succeeded()) {
                    done.complete();
                } else {
                    done.fail(r.cause());
                }
            });
        }
    }

    /**
     * Deploy a vertx-guice verticle on a vertx instance with deployment options
     * @param vertx - Vertx instance to deploy on
     * @param verticle - Verticle class to deploy
     * @param opts - Deployment options to use for deployment
     * @return - Future that can be used to handle successful / failed deployments
     */
    private static Future<Void> deploy(Vertx vertx, Class verticle, DeploymentOptions opts) {
        Future<Void> done = Future.future();
        String deploymentName = "java-guice:" + verticle.getName();
        JsonObject config = new JsonObject()
                .put("guice_binder", ServiceBinder.class.getName());

        opts.setConfig(config);

        vertx.deployVerticle(deploymentName, opts, r -> {
            if(r.succeeded()){
                System.out.println("Successfully deployed verticle: " + deploymentName);
                done.complete();
            }
            else {
                System.out.println("Failed to deploy verticle: " + deploymentName);
                done.fail(r.cause());
            }
        });

        return done;
    }
}

