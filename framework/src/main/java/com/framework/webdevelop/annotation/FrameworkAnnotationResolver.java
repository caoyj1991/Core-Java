package com.framework.webdevelop.annotation;

import com.framework.webdevelop.annotation.entity.AnnotationClazz;
import com.framework.webdevelop.annotation.entity.AnnotationField;
import com.framework.webdevelop.annotation.entity.AnnotationObjectType;
import com.framework.webdevelop.scanner.PackageScanner;
import com.network.protocol.http.HttpMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Author Jun
 * Email
 * Date   6/25/17
 * Time   3:10 PM
 */
public class FrameworkAnnotationResolver<T> {

    private static volatile FrameworkAnnotationResolver instance = null;
    private static Map<AnnotationObjectType, Object> annotationObjectMap;

    public FrameworkAnnotationResolver(){
        annotationObjectMap = new HashMap<>();
    }

    public static FrameworkAnnotationResolver getInstance(){
        if(instance == null){
            synchronized (FrameworkAnnotationResolver.class){
                if(instance == null){
                    instance = new FrameworkAnnotationResolver();
                }
            }
        }
        return instance;
    }

    public AnnotationClazz findAnnotationClazz(String uri, HttpMethod.Type type){
        String URI = getURIKey(type, uri);
        Map<String, AnnotationClazz> urlMap = (Map<String, AnnotationClazz>) annotationObjectMap.get(AnnotationObjectType.URL);
        if(urlMap == null || urlMap.isEmpty()){
            return null;
        }
        return urlMap.get(URI);
    }

    public List<AnnotationClazz> findBean(AnnotationObjectType...types){
        List<AnnotationClazz> list = new ArrayList<>();
        for (int i=0;i<types.length;i++){
            list.addAll((List<AnnotationClazz>)getBeanFactory(types[i]));
        }
        return list;
    }

    public List<AnnotationField> findField(AnnotationObjectType...types){
        List<AnnotationField> list = new ArrayList<>();
        for (int i=0;i<types.length;i++){
            list.addAll((List<AnnotationField>)getBeanFactory(types[i]));
        }
        return list;
    }

    public void initialization(String ...paths){
        PackageScanner scanner = new PackageScanner();
        List<String> fileNames = scanner.getClassNames(paths);
        for (int i=0;i<fileNames.size();i++){
            try {
                Class clazz = Class.forName(fileNames.get(i));
                resolver(clazz);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(annotationObjectMap);
    }

    private void resolver(Class clazz){
        if(clazz == null){
            return;
        }
        Annotation[] clazzAnnotations = clazz.getAnnotations();
        if(clazzAnnotations == null || clazzAnnotations.length<=0){
            return;
        }
        for (int i=0;i<clazzAnnotations.length;i++){
            Class<? extends Annotation> annotationType = clazzAnnotations[i].annotationType();
            if(annotationType.equals(Controller.class)){
                Method[] methods = clazz.getMethods();
                for (int a=0;a<methods.length;a++){
                    checkMethodAnnotation(clazz, methods[a]);
                }
                setBeanFactory(clazz);
            }else if(annotationType.equals(FilterDispatcher.class)){
                setFilterDispatcher(clazz);
            }else if(annotationType.equals(Service.class)
                        ||annotationType.equals(Component.class)){
                setBeanFactory(clazz);
            }
        }
    }

    private void checkVariable(Class clazz) {
        List<AnnotationField> annotationFields = (List<AnnotationField>) annotationObjectMap.get(AnnotationObjectType.FIELD);
        if(annotationFields == null){
            annotationFields = new ArrayList<>();
        }
        Field[] fields = clazz.getDeclaredFields();
        if(fields== null || fields.length == 0){
            return;
        }
        for (Field field : fields){
            Annotation[] annotations = field.getDeclaredAnnotations();
            if(annotations== null || annotations.length==0)
                continue;
            for (int i=0;i<annotations.length;i++){
                Class<? extends Annotation> annotationType = annotations[i].annotationType();
                if(annotationType.equals(Autowired.class)){
                    AnnotationField annotationField = AnnotationField.builder()
                                                        .belongClassName(clazz.getName())
                                                        .variableClassName(field.getType().getName())
                                                        .variableName(field.getName())
                                                        .build();
                    annotationFields.add(annotationField);
                }
            }
        }
        annotationObjectMap.put(AnnotationObjectType.FIELD, annotationFields);
    }

    private void setBeanFactory(Class clazz){
        addNewBeanFactory(AnnotationObjectType.BEAN, clazz);
    }

    private void setFilterDispatcher(Class clazz) {
        addNewBeanFactory(AnnotationObjectType.FILTER, clazz);
    }

    private Object getBeanFactory(AnnotationObjectType type){
        return annotationObjectMap.get(type);
    }

    private void addNewBeanFactory(AnnotationObjectType type, Class clazz){
        List<AnnotationClazz> componentClazzes = (List<AnnotationClazz>) annotationObjectMap.get(type);
        if(componentClazzes == null){
            componentClazzes = new ArrayList<>();
        }
        AnnotationClazz annotationClazz =  AnnotationClazz.builder()
                .clazzName(clazz.getName())
                .implementValue(clazz.getInterfaces())
                .parentClassName(clazz.getSuperclass().getName())
                .build();
        componentClazzes.add(annotationClazz);
        annotationObjectMap.put(type, componentClazzes);
        checkVariable(clazz);
    }

    private void checkMethodAnnotation(Class clazz, Method method){
        Map<String, AnnotationClazz> urlMap = (Map<String, AnnotationClazz>) annotationObjectMap.get(AnnotationObjectType.URL);
        if(urlMap == null){
            urlMap = new HashMap<>();
        }
        Annotation[] annotations = method.getAnnotations();
        for (int i=0;i<annotations.length;i++){
            if (annotations[i].annotationType().equals(RequestMapping.class)){
                String uri = ((RequestMapping)annotations[i]).uri();
                HttpMethod.Type type = ((RequestMapping)annotations[i]).method();
                String URI = getURIKey(type, uri);
                AnnotationClazz annotationClazz = AnnotationClazz.builder().clazzName(clazz.getName()).methodName(method.getName()).build();
                urlMap.put(URI, annotationClazz);
            }
        }
        annotationObjectMap.put(AnnotationObjectType.URL, urlMap);
    }

    private String getURIKey(HttpMethod.Type type, String uri){
        return "method:"+type.name()+"_uri:"+uri;
    }

    public static void main(String[] args){
        new FrameworkAnnotationResolver().initialization("com.sample.project.controller");
    }

}
