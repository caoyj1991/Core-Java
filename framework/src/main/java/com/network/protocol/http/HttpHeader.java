package com.network.protocol.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:03 PM
 */
public class HttpHeader {

    private Map<String, Object> header = null;
    private String headerArray[] = null;
    private String[] firstLine= null;
    private String uri = null;
    private String params = null;

    public HttpHeader(String headerString){
        this.headerArray = headerString.split("\r\n");
        header = new HashMap<>();
        resolver();
    }

    public HttpMethod.Type getHttpMethod(){
        return HttpMethod.resolver(firstLine[0]);
    }

    public long getContentLength(){
        Object value = header.get("content-length");
        return (long)value;
    }

    public String getURI(){
        return uri;
    }

    public String getParams(){
        return params;
    }

    private void resolver(){
        firstLine = headerArray[0].split(" ");
        int indexPosition = firstLine[1].indexOf("?");
        if(indexPosition != -1){
            String httpURI = null;
            try {
                httpURI = URLDecoder.decode(firstLine[1],"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(httpURI != null){
                uri = httpURI.substring(0, indexPosition);
                params = httpURI.substring(indexPosition+1, httpURI.length());
            }
        }
        for (int i=1; i<headerArray.length; i++){
            String line = headerArray[i];
            String[] lineSplit =  line.split(":");
            header.put(lineSplit[0].trim().toLowerCase(), lineSplit[1].trim());
        }
    }
}
