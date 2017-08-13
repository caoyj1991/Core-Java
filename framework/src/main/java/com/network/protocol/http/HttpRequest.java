package com.network.protocol.http;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:28 PM
 */
public class HttpRequest {


    private HttpHeader httpHeader;
    private HttpBody httpBody;

    public HttpRequest(HttpHeader httpHeader, HttpBody httpBody){
        this.httpHeader = httpHeader;
        this.httpBody = httpBody;
    }

    public String getURI(){
        return httpHeader.getURI();
    }

    public HttpMethod.Type getMethodType(){
        return httpHeader.getHttpMethod();
    }

    public HttpBody getHttpBody(){
        return httpBody;
    }

}
