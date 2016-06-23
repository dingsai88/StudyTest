package ding.study.designpatterns.proxy;

import java.lang.reflect.Method;

/**
 * 
 * 切面接口
 * 代理类的切面方法，
 * 在代理方法执行前和执行后执行的类
 * advice建议
 * @author hpo
 *
 */
public interface Advice {
/**
 * 在代理方法执行前执行的方法
 * @param method
 */
 public void beforMethod(Method method);
 /**
  * 在代理方法执行后执行的方法
  * @param method
  */
 public void afterMethod(Method method);
}

 