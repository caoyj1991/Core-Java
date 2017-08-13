package com.framework.webdevelop.bean;

import com.framework.webdevelop.annotation.FrameworkAnnotationResolver;
import com.framework.webdevelop.annotation.entity.AnnotationClazz;
import com.framework.webdevelop.annotation.entity.AnnotationField;
import com.framework.webdevelop.annotation.entity.AnnotationObjectType;
import com.framework.webdevelop.exception.FrameworkException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author Jun
 * Email
 * Date   7/1/17
 * Time   9:31 PM
 */
public class BeanFactory {

    private static volatile BeanFactory beanFactory;
    private Map<String, Object> beanMap = null;

    private BeanFactory(){
        try {
            initialization();
        }catch (FrameworkException e){
            e.printStackTrace();
        }
    }

    public static BeanFactory getBeanFactory(){
        if (beanFactory == null){
            synchronized (BeanFactory.class){
                if(beanFactory ==null){
                    beanFactory = new BeanFactory();
                }
            }
        }
        return beanFactory;
    }

    public Object getBean(String clazzName) throws InterruptedException{
        Object object = beanMap.get(clazzName);
        if(object == null){
            throw new InterruptedException("[Framework Exception] bean can not be found "+clazzName);
        }
        return object;
    }

    private void initialization() throws FrameworkException{
        initializationClass();
        initializationField();
    }

    private void initializationClass() throws FrameworkException{
        List<AnnotationClazz> annotationClazzs = FrameworkAnnotationResolver.getInstance().findBean(AnnotationObjectType.BEAN, AnnotationObjectType.FILTER);
        beanMap = new HashMap<>();
        if(annotationClazzs == null){
            return;
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (int i=0;i<annotationClazzs.size();i++){
            try {
                AnnotationClazz annotationClazz = annotationClazzs.get(i);
                Class clazz = classLoader.loadClass(annotationClazz.getClazzName());
                Object object = clazz.newInstance();
                beanMap.put(annotationClazz.getClazzName(), object);
                System.out.println("class :"+annotationClazz.getClazzName()+" is created as a bean");
            }catch (Exception e){
                throw new FrameworkException("class load fail name:"+annotationClazzs.get(i).getClazzName(), e);
            }
        }
    }
    private void initializationField() throws FrameworkException {
        List<AnnotationField> annotationFields = FrameworkAnnotationResolver.getInstance().findField(AnnotationObjectType.FIELD);
        if (annotationFields == null
                || annotationFields.size()==0){
            return;
        }
        if(beanMap.isEmpty()){
            throw new FrameworkException("bean object is empty");
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (int i=0;i<annotationFields.size();i++){
            String variableClassName = annotationFields.get(i).getVariableClassName();
            String belongClassName = annotationFields.get(i).getBelongClassName();
            try {
                Object variableObject = getBean(variableClassName);
                Object belongObject = getBean(belongClassName);
                Class clazz = classLoader.loadClass(belongClassName);
                Field field = clazz.getDeclaredField(annotationFields.get(i).getVariableName());
                field.setAccessible(true);
                field.set(belongObject, variableObject);
            }catch (InterruptedException e){
               e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
