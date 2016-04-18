package com.ding.acm;

import java.util.Scanner;

/**
 * 奇偶数分离 时间限制：3000 ms | 内存限制：65535 KB 难度：1 描述 有一个整型偶数n(2<= n
 * <=10000),你要做的是：先把1到n中的所有奇数从小到大输出，再把所有的偶数从小到大输出。 输入 第一行有一个整数i（2<=i<30)表示有 i
 * 组测试数据； 每组有一个整型偶数n。 输出 第一行输出所有的奇数 第二行输出所有的偶数 样例输入 2 10 14 样例输出 1 3 5 7 9 2 4 6
 * 8 10
 * 
 * 1 3 5 7 9 11 13 2 4 6 8 10 12 14
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-18 下午2:10:55
 */
public class Acm11 {

	/**
	 * @author daniel
	 * @time 2016-4-18 下午1:49:12
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("请输入测试次数：");
		int count = input.nextInt();

		for (int testCount = 0; testCount < count; testCount++) {

			System.out.print("请输入几组数据：");
			int num = input.nextInt();
			StringBuffer odd = new StringBuffer();
			StringBuffer even = new StringBuffer();
			for (int i = 1; i <= num; i++) {
				if (i % 2 != 0) {
					odd.append(i);
					odd.append(" ");
				} else {
					even.append(i);
					even.append(" ");
				}

			}
			System.out.println(odd.toString() + " \n\n" + even);
		}

	}

}
