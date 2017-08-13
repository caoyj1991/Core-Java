package com.exception;

/**
 * Author Jun
 * Email
 * Date   6/23/17
 * Time   10:14 PM
 */
public class ServerException extends Exception {

    private String message;
    private Exception exception;

    public ServerException(String message, Exception e){
        this.message = message;
        this.exception = e;
    }
}
