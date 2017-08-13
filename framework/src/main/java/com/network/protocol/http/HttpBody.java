package com.network.protocol.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   4:03 PM
 */
public class HttpBody {

    private Map<String, Object> params = null;
    private String value = null;

    public HttpBody(){
        this(null);
    }

    public HttpBody(String value){
        this.value = value;
        params = new HashMap<>();
        resolver();
    }

    public void setValue(String value){
        this.value = value;
        resolver();
    }

    public Map<String, Object> getParams(){
        return params;
    }

    private void resolver(){
        if(value == null){
            return;
        }
        String[] paramArray = value.split("&");
        for (int i=0;i<paramArray.length;i++){
            String[] item = paramArray[i].split("=");
            params.put(item[0], item[1]);
        }
    }
}
