package com.network.protocol.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:02 PM
 */
public class HttpProtocolResolver {

    public static HttpRequest getHttpRequest(InputStream inputStream) throws IOException {
        int c = -1;
        StringBuffer line = new StringBuffer();
        boolean isBody = false;
        long bodyLength = 0;
        HttpHeader httpHeader = null;
        HttpBody httpBody = null;
        while ((c = inputStream.read()) != -1){
            char temp = (char)c;
            line.append(temp);
            if(temp == '\n' && line.toString().contains("\r\n\r\n")){
                String headerString = line.toString();
                httpHeader = new HttpHeader(headerString);
                httpBody = new HttpBody();
                HttpMethod.Type methodType = httpHeader.getHttpMethod();
                if(methodType.equals(HttpMethod.Type.GET)){
                    httpBody.setValue(httpHeader.getParams());
                    break;
                }else if (methodType.equals(HttpMethod.Type.POST)){
                    isBody = true;
                    bodyLength = httpHeader.getContentLength();
                    line = new StringBuffer();
                }
            }else if (isBody & bodyLength>0){
                bodyLength--;
                if(bodyLength == 0){
                    httpBody.setValue(line.toString());
                    break;
                }
            }
        }
        return new HttpRequest(httpHeader, httpBody);
    }
}
