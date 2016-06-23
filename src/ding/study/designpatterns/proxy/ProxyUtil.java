package ding.study.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 生成代理对象的类
 * 
 * @author hpo
 * 
 */
public class ProxyUtil {
	/**
	 * 获得对象的代理
	 * 
	 * @param target
	 *            需要被代理的对象
	 * @param advice
	 *            被代理对象执行方法的切面接口
	 * @return
	 */
	public static Object newProxyInstance(final Object target,
			final Advice advice) {
		Object returnObj = (Object) Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						advice.beforMethod(method);
						Object relObj = method.invoke(target, args);
						advice.afterMethod(method);
						return relObj;
					}
				});
		return returnObj;
	}
}