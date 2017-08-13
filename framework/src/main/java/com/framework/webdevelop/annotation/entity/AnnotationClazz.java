package com.framework.webdevelop.annotation.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Author Jun
 * Email
 * Date   6/25/17
 * Time   4:11 PM
 */
public class AnnotationClazz {

    private String clazzName;
    private String methodName;
    private Set<String> implementSet;
    private String parentClassName;

    public void setClazzName(String clazzName){
        this.clazzName = clazzName;
    }
    public String getClazzName(){
        return this.clazzName;
    }

    public void setMethodName(String methodName){
        this.methodName = methodName;
    }
    public String getMethodName(){
        return methodName;
    }

    public Set<String> getImplementSet() {
        return implementSet;
    }

    public void setImplementSet(Set<String> implementSet) {
        this.implementSet = implementSet;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public static AnnotationClazzBuilder builder(){
        return new AnnotationClazzBuilder();
    }

    public static class AnnotationClazzBuilder{
        private String clazzName;
        private String methodName;
        private Set<String> implementSet;
        private String parentClassName;

        public AnnotationClazzBuilder(){}

        public AnnotationClazzBuilder clazzName(String clazzName){
            this.clazzName = clazzName;
            return this;
        }

        public AnnotationClazzBuilder methodName(String methodName){
            this.methodName = methodName;
            return this;
        }

        public AnnotationClazzBuilder implementValue(Class[] interfaces){
            implementSet = new HashSet<>();
            if(interfaces!=null && interfaces.length>0){
                for (int i=0;i<interfaces.length;i++){
                    implementSet.add(interfaces[i].getName());
                }
            }
            return this;
        }

        public AnnotationClazzBuilder parentClassName(String parentClassName){
            this.parentClassName = parentClassName;
            return this;
        }

        public AnnotationClazz build(){
            AnnotationClazz annotationClazz = new AnnotationClazz();
            annotationClazz.setClazzName(this.clazzName);
            annotationClazz.setMethodName(this.methodName);
            annotationClazz.setImplementSet(this.implementSet);
            annotationClazz.setParentClassName(this.parentClassName);
            return annotationClazz;
        }
    }
}
