package com.ding.acm;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 会场安排问题
时间限制：3000 ms  |  内存限制：65535 KB
难度：4
描述
学校的小礼堂每天都会有许多活动，有时间这些活动的计划时间会发生冲突，需要选择出一些活动进行举办。小刘的工作就是安排学校小礼堂的活动，每个时间最多安排一个活动。现在小刘有一些活动计划的时间表，他想尽可能的安排更多的活动，请问他该如何安排。
输入
第一行是一个整型数m(m<100)表示共有m组测试数据。
每组测试数据的第一行是一个整数n(1<n<10000)表示该测试数据共有n个活动。
随后的n行，每行有两个正整数Bi,Ei(0<=Bi,Ei<10000),分别表示第i个活动的起始与结束时间（Bi<=Ei)
输出
对于每一组输入，输出最多能够安排的活动数量。
每组的输出占一行
样例输入
2
2
1 10
10 11
3
1 10
10 11
11 20
样例输出
1
2
提示
注意：如果上一个活动在t时间结束，下一个活动最早应该在t+1时间开始
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-22 下午4:47:50
 */
public class Acm14 {

	/**
	 * @author daniel
	 * @time 2016-4-22 下午3:49:51
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner input = new java.util.Scanner(System.in);
		int count = input.nextInt();

		List<ActiveTime> list = new ArrayList<ActiveTime>();
		for (int i = 0; i < count; i++) {

			int activeCount = input.nextInt();
			for (int j = 0; j < activeCount; j++) {
				ActiveTime obj = new ActiveTime();
				obj.setBegin(input.nextInt());
				obj.setEnd(input.nextInt());
				list.add(obj);
			}
            //按照活动结束时间升序
			Collections.sort(list);
			int current = -1;
			int activeSum = 0;
			for (ActiveTime obj : list) {

				//最早结束的活动的开始时间
				if (obj.getBegin() > current) {
					activeSum++;
					//结束时间赋值到当前时间
					current = obj.getEnd();
				}
			}
			System.out.println("可以运行:" + activeSum);

		}

	}
}

/**
 * 任务时间
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-22 下午3:56:28
 */
class ActiveTime implements Comparable<ActiveTime> {
	private int begin;
	private int end;

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * 按照结束时间从小到大排序
	 */
	@Override
	public int compareTo(ActiveTime o) {

		return this.end - o.end;
	}

}
