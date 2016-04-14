package com.ding.acm;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 描述
一个街区有很多住户，街区的街道只能为东西、南北两种方向。

住户只可以沿着街道行走。

各个街道之间的间隔相等。

用(x,y)来表示住户坐在的街区。

例如（4,20），表示用户在东西方向第4个街道，南北方向第20个街道。

现在要建一个邮局，使得各个住户到邮局的距离之和最少。

求现在这个邮局应该建在那个地方使得所有住户距离之和最小；

输入
第一行一个整数n<20，表示有n组测试数据，下面是n组数据;
每组第一行一个整数m<20,表示本组有m个住户，下面的m行每行有两个整数0<x,y<100，表示某个用户所在街区的坐标。
m行后是新一组的数据；
输出
每组数据输出到邮局最小的距离和，回车结束；
样例输入
2
3
1 1
2 1
1 2
5
2 9 
5 20
11 9
1 1
1 20
样例输出
2
44





分析:
每个住户分左边X,Y。穷举法
1.计算出一个坐标点分别到每个住户的距离，然后相加。
2.计算出所有坐标点到输入住户的距离，保留最小距离
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-14 下午3:56:29
 */
public class Acm7 {

	// 横左边最大值
	private static int maxX = 20;
	// 纵坐标最大值
	private static int maxY = 100;
	// 最短距离
	private static int minF = 0;

	private static int currentX = 0;
	private static int currentY = 0;

	/**
	 * @author daniel
	 * @time 2016-4-14 下午3:09:34
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入测试次数：");
		int n = Integer.parseInt(strin.readLine());

		for (int testCount = 0; testCount < n; testCount++) {
			// 初始化最短距离
			minF = 0;
			System.out.print("请输入几户人家：");
			// 几户人家
			int users = Integer.parseInt(strin.readLine());
			Scanner input = new Scanner(System.in);
			int[][] u = new int[users][2];
			for (int user = 0; user < users; user++) {

				int x = input.nextInt();
				int y = input.nextInt();
				u[user][0] = x;
				u[user][1] = y;

			}
			count(u);

		}
	}

	/**
	 * 计算最短距离
	 * 
	 * @author daniel
	 * @time 2016-4-14 下午3:52:01
	 * @param user
	 */
	public static void count(int[][] user) {

		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				//当前坐标距离
				int currentDistance = 0;
				
				//计算当前节点到所有住户的距离和
				for (int k = 0; k < user.length; k++) {
					currentDistance +=  Math.abs(i - user[k][0]) + Math.abs(j - user[k][1]);
				}

				// 首次进来赋初始值
				if ((i == 0) && (j == 0)) {
					minF = currentDistance;
				}
				if (currentDistance < minF) {
					currentX = i;
					currentY = j;
					minF = currentDistance;
				}

			}

		}

		System.out.print("最短距离：" + minF);
		System.out.print("坐标：" + currentX + "、" + currentY);
	}

}
