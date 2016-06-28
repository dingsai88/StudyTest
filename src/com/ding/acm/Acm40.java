package com.ding.acm;

import java.util.HashSet;

import java.util.Set;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
一种排序
时间限制：3000 ms  |  内存限制：65535 KB
难度：3
描述
现在有很多长方形，每一个长方形都有一个编号，这个编号可以重复；还知道这个长方形的宽和长，编号、长、宽都是整数；现在要求按照一下方式排序（默认排序规则都是从小到大）；

1.按照编号从小到大排序

2.对于编号相等的长方形，按照长方形的长排序；

3.如果编号和长都相同，按照长方形的宽排序；

4.如果编号、长、宽都相同，就只保留一个长方形用于排序,删除多余的长方形；最后排好序按照指定格式显示所有的长方形；
输入
第一行有一个整数 0<n<10000,表示接下来有n组测试数据；
每一组第一行有一个整数 0<m<1000，表示有m个长方形；
接下来的m行，每一行有三个数 ，第一个数表示长方形的编号，

第二个和第三个数值大的表示长，数值小的表示宽，相等
说明这是一个正方形（数据约定长宽与编号都小于10000）；
输出
顺序输出每组数据的所有符合条件的长方形的 编号 长 宽
样例输入
1
8
1 1 1
1 1 1
1 1 2
1 2 1
1 2 2
2 1 1
2 1 2
2 2 1
样例输出
1 1 1
1 2 1
1 2 2
2 1 1
2 2 1


I.思路
II.矫正输入的数据 长宽互换
II.判断是否已存在，存在则忽略
II.对所有数据进行排序输出

 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-15 上午11:43:12
 */
public class Acm40 {

	/**
	 * @author daniel
	 * @time 2016-4-15 上午10:59:34
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);
		System.out.print("请输入测试次数：");
		int count = input.nextInt();
		
		Set set=new HashSet();
		set.add("");

		for (int testCount = 0; testCount < count; testCount++) {

			System.out.print("请输入几组数据：");
			int num = input.nextInt();

			List<Rectangle> list = new ArrayList<Rectangle>();
			boolean isSave = true;
			// 接收数据组输入
			for (int i = 0; i < num; i++) {
				Rectangle obj = new Rectangle();
				obj.setId(input.nextInt());
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
				for (Rectangle o : list) {
					if (o.compareTo(obj) == 0) {
						isSave = false;
					}
				}
				//不存在就保存
				if (isSave) {
					list.add(obj);
				}

			}
			// 排序
			Collections.sort(list);

			// 输出结果
			for (Rectangle o : list) {
				System.out.println(o);
			}
		}

	}

}

