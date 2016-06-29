package com.ding.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClass {

	// String 会被 JVM 优化
	private final String FINAL_VALUE  = "FINAL";

	//三目运算符 避免优化
	//private final String FINAL_VALUE =  null == null ? "FINAL" : null;
	
	public String getFinalValue() {
		// 会被优化为: return "FINAL" ,拭目以待吧
		return FINAL_VALUE;
	}

/*	构造函数赋值一样可以避免优化
 * public TestClass() {
		this.FINAL_VALUE = "FINAL";
	}*/

	/**
	 * 修改对象私有常量的值 为简洁代码，在方法上抛出总的异常，实际开发别这样
	 */
	private static void modifyFinalFiled() throws Exception {
		// 1. 获取 Class 类实例
		TestClass testClass = new TestClass();
		Class mClass = testClass.getClass();

		// 2. 获取私有常量
		Field finalField = mClass.getDeclaredField("FINAL_VALUE");

		// 3. 修改常量的值
		if (finalField != null) {

			// 获取私有常量的访问权
			finalField.setAccessible(true);

			// 调用 finalField 的 getter 方法
			// 输出 FINAL_VALUE 修改前的值
			System.out.println("Before Modify：FINAL_VALUE = "
					+ finalField.get(testClass));

			// 修改私有常量
			finalField.set(testClass, "Modified");

			// 调用 finalField 的 getter 方法
			// 输出 FINAL_VALUE 修改后的值
			System.out.println("After Modify：FINAL_VALUE = "
					+ finalField.get(testClass));

			// 使用对象调用类的 getter 方法
			// 获取值并输出
			System.out.println("Actually ：FINAL_VALUE = "
					+ testClass.getFinalValue());
		}
	}

	/**
	 * 
	 * 输出结果: Before Modify：FINAL_VALUE = FINAL After Modify：FINAL_VALUE =
	 * Modified Actually ：FINAL_VALUE = Modified
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TestClass.modifyFinalFiled();		
		ApplicationContext a = new ClassPathXmlApplicationContext("");  		
		StringBuffer sb=new StringBuffer();
		sb.append("1111");
		sb.append("2222");
		System.out.println(sb);
			
		Method[] methods=TestClass.class.getDeclaredMethods();
		for(Method method:methods){
			if(method.getName().equals("")){
				method.setAccessible(true);
				
			}
			
			
		}
		
		
	}

}
