package com.exception;

/**
 * Author Jun
 * Email
 * Date   6/23/17
 * Time   10:14 PM
 */
public class ServerException extends Exception {

    private String message;

    public ServerException(String message){
        super(message);
    }
}
