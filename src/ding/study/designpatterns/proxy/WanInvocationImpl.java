package ding.study.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WanInvocationImpl implements InvocationHandler {
	private Object obj;

	public WanInvocationImpl(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("跑前准备");
		Object resultObj = method.invoke(obj, args);
		System.out.println("跑完休息");
		return resultObj;
	}

}
