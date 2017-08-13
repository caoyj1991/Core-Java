package com.framework.webdevelop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Author Jun
 * Email
 * Date   6/28/17
 * Time   10:34 AM
 */
public class ProxyUtil{

    private Logger logger = LogManager.getLogManager().getLogger(ProxyUtil.class.getName());

    public Object creator(Object proxyObject, ClassLoader classLoader, Class[] interfaces){
        return creator(proxyObject, null, classLoader, interfaces);
    }

    public Object creator(Object proxyObject, ProxyInterface proxyInterface, ClassLoader classLoader, Class[] interfaces){
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(proxyObject, proxyInterface);
        Class proxyClass = Proxy.getProxyClass(classLoader, interfaces);
        try {
            return proxyClass.getConstructor(new Class[] { InvocationHandler.class }).newInstance(new Object[] { invocationHandler });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }



    private class InvocationHandlerImpl implements InvocationHandler{

        private Object object;
        private ProxyInterface proxyInterface;

        public InvocationHandlerImpl(Object object, ProxyInterface proxyInterface){
            this.object = object;
            this.proxyInterface = proxyInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            try {
                before(method, args);
                result = method.invoke(object, args);
                after(method, args, result);
            }catch (Exception exception){
                exception.printStackTrace();
            }

            return result;
        }

        private void before(Method method, Object[] args){
            if(proxyInterface!=null){
                try {
                    proxyInterface.before(method, args);
                }catch (UnsupportedOperationException e){
                    //if not
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        private void after(Method method, Object[] args, Object result){
            if(proxyInterface!=null){
                try {
                    proxyInterface.after(method, args, result);
                }catch (UnsupportedOperationException e){
                    //if not
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
