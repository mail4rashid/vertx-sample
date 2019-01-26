package com.mail4rashid.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mail4rashid.vertx.config.AppConfig;
import com.mail4rashid.vertx.service.Welcome;
import com.mail4rashid.vertx.web.RestAPIController;
import io.vertx.core.Vertx;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class ServiceBinder extends AbstractModule{

    @Provides @Singleton
    public RestAPIController provideController(Vertx vertx, Welcome service){
        return new RestAPIController(vertx, service);
    }

    @Provides @Singleton
    public Welcome provideService(){
        return new Welcome();
    }

    @Provides @Singleton
    public AppConfig provideAppConfig(){
        return new AppConfig(30, 30);
    }

    @Override
    protected void configure() {

    }
}
