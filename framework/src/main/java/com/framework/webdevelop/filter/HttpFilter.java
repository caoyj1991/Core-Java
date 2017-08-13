package com.framework.webdevelop.filter;

import com.network.protocol.http.HttpRequest;
import com.network.protocol.http.HttpResponse;

/**
 * Author Jun
 * Email
 * Date   7/1/17
 * Time   3:01 PM
 */
public abstract class HttpFilter {

    public abstract void doFilter(HttpRequest httpRequest, HttpResponse httpResponse, HttpDoFilter httpDoFilter);

}
