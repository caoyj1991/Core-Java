package com.framework.webdevelop.filter;

import com.network.protocol.http.HttpRequest;
import com.network.protocol.http.HttpResponse;

/**
 * Author Jun
 * Email
 * Date   7/1/17
 * Time   4:13 PM
 */
public interface HttpDoFilter {

    void doFilter(HttpRequest httpRequest, HttpResponse httpResponse);
}
