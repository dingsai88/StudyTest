package com.ding.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区内存溢出(所有线程共享，每个类的信息，静态变量、常量)
 * VM args:-XX:PermSize=2M -XX:MaxPermSize=2M
 * @author daniel 2018-1-8 0008.
 */
public class Study6JavaMethodAreaOOM {

    /**
     * 方法区内存溢出
     * java.lang.OutOfMemoryError: PermGen space
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
       while (true){
           Enhancer enhancer=new Enhancer();
           enhancer.setSuperclass(OOMObject.class);
           enhancer.setUseCache(false);
           enhancer.setCallback(new MethodInterceptor() {
               @Override
               public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                   return methodProxy.invokeSuper(o,objects);
               }
           });
       }
    }

static class OOMObject{

}
}
