package ding.study.designpatterns.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理模式：为其他对象提供一种代理以控制对这个对象的访问。
 * 
 * 使用场合：
 * 
 * 远程代理，一个对象在不同的地址空间提供局部代表。这样可以隐藏一个对象存在于不同地址空间的事实。
 * 
 * 虚拟代理，创建对象开销很大，通过代理模式来存放实例化很长时间的真实对象。
 * 
 * 安全代理，可以控制真实对象的访问权限。
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 上午10:44:26
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-19 上午10:43:07
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Proxy proxy = new Proxy(); proxy.request();
		 */
 		Wan w=new Wan();
		WanInvocationImpl impl = new WanInvocationImpl(w);
		Wan wan = (Wan)Proxy.newProxyInstance(Wan.class.getClassLoader(),new Class[] { Wan.class }, impl);

		wan.run(); 
	
		
/*		List list=(List)ProxyUtil.newProxyInstance(new ArrayList(), new MyAdvice());
		  list.add("aa");
		  list.add("bb");
		  System.out.println(list.size());
		  list.clear();
		  System.out.println(list.size());
		  */
		  
			
		  WanImpl list=(WanImpl)ProxyUtil.newProxyInstance(new WanImpl(), new MyAdvice());
			 
 			list.run();
 		  
	}

}
