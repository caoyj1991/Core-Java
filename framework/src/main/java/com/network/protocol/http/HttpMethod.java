package com.network.protocol.http;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:03 PM
 */
public class HttpMethod {

    public enum Type{
        GET,
        POST,
        PUT,
        DELETE;
    }

    public static Type resolver(String methodWord){
        switch (methodWord){
            case "GET":
                return Type.GET;
            case "POST":
                return Type.POST;
            case "PUT":
                return Type.PUT;
            case "DELETE":
                return Type.DELETE;
            default:
                throw new UnsupportedOperationException("do not support it method type is :"+methodWord);
        }
    }
}
