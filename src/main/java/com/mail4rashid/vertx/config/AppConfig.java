package com.mail4rashid.vertx.config;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class AppConfig {
    private int serverThreads;
    private int workerThreads;

    public AppConfig(int serverThreads, int workerThreads) {
        this.serverThreads = serverThreads;
        this.workerThreads = workerThreads;
    }

    public int getServerThreads() {
        return serverThreads;
    }

    public int getWorkerThreads() {
        return workerThreads;
    }
}
