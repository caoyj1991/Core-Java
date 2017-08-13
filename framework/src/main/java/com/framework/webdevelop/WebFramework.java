package com.framework.webdevelop;

import com.exception.ServerException;
import com.framework.webdevelop.annotation.FrameworkAnnotationResolver;
import com.framework.webdevelop.bean.BeanFactory;
import com.network.sorket.blockingio.BlockingSocketService;

/**
 * Author Jun
 * Email
 * Date   6/25/17
 * Time   4:42 PM
 */
public class WebFramework {

    public static void main(String[] args){
        FrameworkAnnotationResolver.getInstance().initialization("com.framework");
        BeanFactory.getBeanFactory();
        try {
            new BlockingSocketService(9999).start();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }

}
