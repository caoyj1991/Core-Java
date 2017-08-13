package com.network.protocol.http;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:29 PM
 */
public class HttpResponse {
    private final String DEFAULT_SUCCESS_RESPONSE = "HTTP/1.1 200 OK\r\nContent-Type:text/html;charset=UTF-8\r\nContent-Length:93\r\n\r\n"+
            "<html>\n" +
            "<head>\n" +
            "<title>Connect Success</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "来自服务器的信息\n" +
            "</body>\n" +
            "</html>\n";

    private final String DEFAULT_ERROR_RESPONSE = "HTTP/1.1 404 OK\r\nContent-Type:text/html;charset=UTF-8\r\nContent-Length:86\r\n\r\n"+
            "<html>\n" +
            "<head>\n" +
            "<title>Not Find</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "来自服务器的信息\n" +
            "</body>\n" +
            "</html>\n";
    private String responseString = null;

    public HttpResponse(){
        this(null);
    }

    public HttpResponse(String responseString) {
        if(responseString != null){
            this.responseString = responseString;
        }else{
            this.responseString = DEFAULT_SUCCESS_RESPONSE;
        }
    }

    public void setHttpResponseByStatus(int status){
        switch (status){
            case 404:
                responseString = DEFAULT_ERROR_RESPONSE;
                break;
        }

    }

    public byte[] getBody(){
        return responseString.getBytes();
    }
}
