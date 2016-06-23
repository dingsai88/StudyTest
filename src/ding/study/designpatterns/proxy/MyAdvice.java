package ding.study.designpatterns.proxy;

import java.lang.reflect.Method;
/*
 * 切面接口的实现类
 */
public class MyAdvice implements Advice { 
 public void beforMethod(Method method) {
  System.out.println("方法开始:马上执行的方法叫:"+method.getName());
 }

 public void afterMethod(Method method) {   
  System.out.println("方法结束:马上执行的方法叫:"+method.getName());
 }

}