package com.framework.webdevelop.proxy;

import java.lang.reflect.Method;

/**
 * Author Jun
 * Email
 * Date   6/28/17
 * Time   10:41 AM
 */
public interface ProxyInterface {
    void before(Method method, Object[] args);
    void after(Method method, Object[] args, Object result);
}
