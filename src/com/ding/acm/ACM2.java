package com.ding.acm;

import java.util.Stack;

import java.io.InputStreamReader;

import java.io.BufferedReader;

/**
 * 描述
现在，有一行括号序列，请你检查这行括号是否配对。
输入
第一行输入一个数N（0<N<=100）,表示有N组测试数据。后面的N行输入多组输入数据，每组输入数据都是一个字符串S(S的长度小于10000，且S不是空串），测试数据组数少于5组。数据保证S中只含有"[","]","(",")"四种字符
输出
每组输入数据的输出占一行，如果该字符串中所含的括号是配对的，则输出Yes,如果不配对则输出No
样例输入
3
[(])
(])
([[]()])

 * Stack 类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类 Vector 进行了扩展 ，允许将向量视为堆栈。它提供了通常的 push 和 pop
 * 操作，以及取堆栈顶点的 peek 方法、测试堆栈是否为空的 empty 方法、在堆栈中查找项并确定到堆栈顶距离的 search 方法。
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-12 下午6:56:30
 */
public class ACM2 {

	/**
	 * @author daniel
	 * @time 2016-4-12 下午6:30:07
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
 		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			//System.out.print("请输入测试次数：");
			String count = strin.readLine();
			int c = Integer.parseInt(count);
			while (c-- > 0) {
				//System.out.print("请输入字符串：");
				String str = strin.readLine();
				check(str);
				//System.out.println("您输入的是:" + str + ";是否匹配:" + );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
/**
 * 实现细节
 * @author daniel
 * @time 2016-4-12 下午6:59:49
 * @param str
 * @return
 */
	public static boolean check(String str) {
		boolean bo = false;
		int length = str.length();
		Stack<Character> stack = new Stack<Character>();

	 
			for (int i = 0; i < length; i++) {
				if (stack.isEmpty()) {
					stack.push(str.charAt(i));
				} else if (stack.peek() == '[' && str.charAt(i) == ']') {
					stack.pop();
				} else if (stack.peek() == '(' && str.charAt(i) == ')') {
					stack.pop();
				} else {
					stack.push(str.charAt(i));
				}

			}
	 

		if (stack.isEmpty()) {
			// 如果栈是空的，说明括号匹配
			bo = true;
			 System.out.println("Yes");

		} else {
			bo = false;
			// 说明栈不为空，括号不匹配
		 System.out.println("No");
		}

		return bo;
	}

}
