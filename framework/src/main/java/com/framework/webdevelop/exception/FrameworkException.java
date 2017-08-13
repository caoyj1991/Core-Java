package com.framework.webdevelop.exception;

/**
 * Author Jun
 * Email
 * Date   7/1/17
 * Time   2:42 PM
 */
public class FrameworkException extends Exception{

    public FrameworkException(){
        super();
    }

    public FrameworkException(String message){
        super(message);
    }

    public FrameworkException(String message, Throwable cause){
        super(message, cause);
    }
}
