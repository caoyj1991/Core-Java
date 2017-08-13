package com.framework.webdevelop.dispatcher;

import com.framework.webdevelop.annotation.FrameworkAnnotationResolver;
import com.framework.webdevelop.annotation.entity.AnnotationClazz;
import com.framework.webdevelop.annotation.entity.AnnotationObjectType;
import com.framework.webdevelop.bean.BeanFactory;
import com.framework.webdevelop.exception.FrameworkException;
import com.framework.webdevelop.filter.HttpDoFilter;
import com.network.protocol.http.HttpMethod;
import com.network.protocol.http.HttpRequest;
import com.network.protocol.http.HttpResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Jun
 * Email
 * Date   7/1/17
 * Time   2:56 PM
 */
public class HttpDispatcher {

    private static volatile HttpDispatcher instance = null;

    private HttpDispatcher(){

    }

    public static HttpDispatcher getInstance(){
        if(instance == null){
            synchronized (HttpDispatcher.class){
                if(instance == null){
                    instance = new HttpDispatcher();
                }
            }
        }
        return instance;
    }

    public HttpResponse doExecute(HttpRequest httpRequest) throws Exception {
        List<Class> clazzes = getFilterList();
        HttpResponse httpResponse = new HttpResponse();
        if(clazzes ==null && clazzes.size()==0){
            execute(httpRequest, httpResponse);
            return httpResponse;
        }

        for (int i=0;i<clazzes.size()-1;i++){
            executeFilter(clazzes, i, httpRequest, httpResponse);
        }
        return httpResponse;
    }

    private void executeFilter(List<Class> clazzes, int index, HttpRequest httpRequest, HttpResponse httpResponse) throws Exception{
        Method method = clazzes.get(index).getMethod("doFilter", HttpRequest.class, HttpResponse.class, HttpDoFilter.class);
        method.invoke(BeanFactory.getBeanFactory().getBean(clazzes.get(index).getName()), httpRequest, httpResponse, callBackFilter(clazzes, index));
    }

    private HttpDoFilter callBackFilter(List<Class> clazzes, int index) throws Exception{
        return new HttpDoFilter() {
                    @Override
                    public void doFilter(HttpRequest httpRequest, HttpResponse httpResponse) {
                        if (index+1== clazzes.size()){
                            execute(httpRequest, httpResponse);
                        }else {
                            try {
                                executeFilter(clazzes, index+1, httpRequest, httpResponse);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };
    }

    private void execute(HttpRequest httpRequest, HttpResponse httpResponse){
        System.out.println("execute");
        String uri = httpRequest.getURI();
        HttpMethod.Type type = httpRequest.getMethodType();
        AnnotationClazz annotationClazz = FrameworkAnnotationResolver.getInstance().findAnnotationClazz(uri, type);
        try {
            if(annotationClazz == null){
                throw new FrameworkException("uri is not exit");
            }
            Class clazz = loadClass(annotationClazz.getClazzName());
            Method method = clazz.getMethod(annotationClazz.getMethodName(), HttpRequest.class);
            httpResponse =(HttpResponse)method.invoke(BeanFactory.getBeanFactory().getBean(annotationClazz.getClazzName()), httpRequest);
        }catch (FrameworkException e){
            httpResponse.setHttpResponseByStatus(404);
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Class> getFilterList(){
        List<AnnotationClazz> annotationClazzs = FrameworkAnnotationResolver.getInstance().findBean(AnnotationObjectType.FILTER);
        List<Class> clazzes = new ArrayList<>();
        if(annotationClazzs != null && annotationClazzs.size()>0){
            for (int i=0;i<annotationClazzs.size();i++){
                try {
                    Class clazz = loadClass(annotationClazzs.get(i).getClazzName());
                    clazzes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazzes;
    }

    public Class loadClass(String name) throws ClassNotFoundException{
        return Thread.currentThread().getContextClassLoader().loadClass(name);
    }
}
