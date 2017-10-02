package com.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author Mr.Pro
 * Date   9/24/17 = 4:48 PM
 */
public class JavaCompilerUtils {

    public JavaCompilerUtils(String name) throws ClassNotFoundException {
        Class clazz = this.getClass().getClassLoader().loadClass(name);

        Method[] methods = clazz.getDeclaredMethods();
        List<String> methodList = new ArrayList<>();
        for (Method method : methods){
            String param = new String();
            for (Class paramClass : method.getParameterTypes()){
                param += paramClass.getName()+", ";
            }
            String temp = "";
            if(param.length()>0){
                temp = method.getName()+" ("+param.substring(0, param.length()-2)+")<br>";
            }else{
                temp = method.getName()+" ()<br>";
            }
            if(method.getDeclaredAnnotations().length>0){
                Annotation[] t = method.getDeclaredAnnotations();
                for (Annotation tt : t){
                    if (tt.annotationType().equals(java.lang.Deprecated.class)){
                        temp = "@Deprecated "+temp;
                    }
                }
            }
            methodList.add(temp);
        }
        methodList = methodList.stream().sorted().collect(Collectors.toList());
        methodList.forEach(item ->{
            System.out.println(item);
        });

    }

    public static void main(String[] args) throws ClassNotFoundException {
        new JavaCompilerUtils("java.lang.Thread");
    }

}
