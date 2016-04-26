package com.ding.acm;
import java.util.ArrayList;
import java.util.Collections;
 import java.util.Scanner;
/**
 * 
 * 
 * 
 * 矩形嵌套
时间限制：3000 ms  |  内存限制：65535 KB
难度：4
描述
有n个矩形，每个矩形可以用a,b来描述，表示长和宽。矩形X(a,b)可以嵌套在矩形Y(c,d)中当且仅当a<c,b<d或者b<c,a<d（相当于旋转X90度）。例如（1,5）可以嵌套在（6,2）内，但不能嵌套在（3,4）中。你的任务是选出尽可能多的矩形排成一行，使得除最后一个外，每一个矩形都可以嵌套在下一个矩形内。
输入
第一行是一个正正数N(0<N<10)，表示测试数据组数，
每组测试数据的第一行是一个正正数n，表示该组测试数据中含有矩形的个数(n<=1000)
随后的n行，每行有两个数a,b(0<a,b<100)，表示矩形的长和宽
输出
每组测试数据都输出一个数，表示最多符合条件的矩形数目，每组输出占一行
样例输入
1
10
1 2
2 4
5 8
6 10
7 9
3 1
5 8
12 10
9 7
2 2
样例输出
5
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-25 下午1:46:22
 */
public class Acm16 {

	/**
	 * @author daniel
	 * @time 2016-4-25 下午12:18:52
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int count = input.nextInt();

		for (int testCount = 0; testCount < count; testCount++) {

			int num = input.nextInt();

			ArrayList<Rectangle2> list = new ArrayList<Rectangle2>();
			boolean isSave = true;
			// 接收数据组输入
			for (int i = 0; i < num; i++) {
				Rectangle2 obj = new Rectangle2();

				obj.setLength(input.nextInt());
				obj.setWidth(input.nextInt());

				/**
				 * 替换长边和宽
				 */
				if (obj.getWidth() > obj.getLength()) {
					int j = obj.getWidth();
					obj.setWidth(obj.getLength());
					obj.setLength(j);
				}

				isSave = true;
				// 判断是否存在
				for (Rectangle2 o : list) {
					if (o.compareTo(obj) == 0) {
						isSave = false;
					}
				}
				// 不存在就保存
				if (isSave) {
					list.add(obj);
				}

			}
			// 排序
			Collections.sort(list);
			int sum = 0;

			int x = list.size();
			boolean isPlus = true;

			for (int i = 0; i < x;) {
				isPlus = true;
				Rectangle2 one = list.get(i);
				for (int j = 1; j < list.size() - i; j++) {
					Rectangle2 two = list.get(i + j);
					if ((two.getLength() > one.getLength()) && two.getWidth() > one.getWidth()) {
						sum++;
						i = i + j;
						isPlus = false;
						break;

					}

				}

				if (isPlus) {
					i++;
				}

			}

			System.out.println(returnAnsNum(list));

		}

	}

	public static int returnAnsNum(ArrayList<Rectangle2> recList) {
		int[] dp = new int[1001];
		int totalCount = 1;
		for (int i = 0; i < recList.size(); i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (recList.get(j).getWidth() < recList.get(i).getWidth() && recList.get(j).getLength() < recList.get(i).getLength() && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			if (dp[i] > totalCount) {
				totalCount = dp[i];
			}
		}
		return totalCount;

	}

}

/**
 * ID,长宽对象
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-15 上午11:47:27
 */
class Rectangle2 implements Comparable<Rectangle2> {

	private int length;
	private int width;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		// return id + " " + length + " " + width ;
		return "Rectangle [ length=" + length + ", width=" + width + "]";
	}

	@Override
	public int compareTo(Rectangle2 o) {

		// 长度排序
		if (this.length > o.length) {
			return 1;
		} else if (this.length < o.length) {
			return -1;
		}

		// 宽度排序
		if (this.width > o.width) {
			return 1;
		} else if (this.width < o.width) {
			return -1;
		}

		// 相同值
		if (this.length == o.length && this.width == o.width) {
			return 0;
		}
		return 404;
	}

}
