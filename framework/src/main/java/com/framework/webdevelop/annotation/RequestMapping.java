package com.framework.webdevelop.annotation;

import com.network.protocol.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author Jun
 * Email
 * Date   6/25/17
 * Time   1:03 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String uri() default  "/";
    HttpMethod.Type method() default HttpMethod.Type.GET;
}
