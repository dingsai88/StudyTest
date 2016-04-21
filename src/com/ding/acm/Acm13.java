package com.ding.acm;

import java.util.Scanner;

/**
 * 描述 无穷数列1，1，2，3，5，8，13，21，34，55...称为Fibonacci数列，它可以递归地定义为 F(n)=1
 * ...........(n=1或n=2) F(n)=F(n-1)+F(n-2).....(n>2) 现要你来求第n个斐波纳奇数。（第1个、第二个都为1)
 * 输入 第一行是一个整数m(m<5)表示共有m组测试数据 每次测试数据只有一行，且只有一个整形数n(n<20) 输出
 * 对每组输入n，输出第n个Fibonacci数 样例输入 3 1 3 5 样例输出 1 2 5
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-21 上午11:28:28
 */
public class Acm13 {

	/**
	 * @author daniel
	 * @time 2016-4-21 上午10:34:13
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();

		int[] sum = new int[n];
		for (int i = 0; i < n; i++) {
			sum[i] = input.nextInt();
		}

		for (int i = 0; i < n; i++) {
			System.out.println(fibB(sum[i]));
			// System.out.println(fib(sum[i]));
		}

	}

	/**
	 * 递归实现
	 * 
	 * @author daniel
	 * @time 2016-4-21 上午11:37:33
	 * @param n
	 * @return
	 */
	public static long fib(int n) {
		if (n <= 1) {
			// System.out.println("if:" + n);
			return n;
		} else {
			// System.out.println("else:" + n);
			return fib(n - 1) + fib(n - 2);
		}
	}

	/**
	 * 循环实现 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
	 * 
	 */
	private static long fibB(int n) {
		long a = 0;
		long b = 1;
		long c = 0;
		if (n <= 1) {
			return n;
		}
		for (int i = 1; i < n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

}
