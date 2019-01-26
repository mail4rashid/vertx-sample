package com.mail4rashid.vertx.exceptions;

/**
 * Created by Mohammed Rashid on 2019-01-26.
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg, int status){
        super(msg);
    }
}
