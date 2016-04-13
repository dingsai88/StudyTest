package com.ding.acm;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 描述 现有一块草坪，长为20米，宽为2米，要在横中心线上放置半径为Ri的喷水装置，每个喷水装置的效果都会让以它为中心的半径为实数Ri(0<Ri<15)
 * 的圆被湿润，这有充足的喷水装置i（1<i<600)个，并且一定能把草坪全部湿润，你要做的是：选择尽量少的喷水装置，把整个草坪的全部湿润。 输入
 * 第一行m表示有m组测试数据 每一组测试数据的第一行有一个整数数n，n表示共有n个喷水装置，随后的一行，有n个实数ri，ri表示该喷水装置能覆盖的圆的半径。
 * 输出 输出所用装置的个数 样例输入 2 5 2 3.2 4 4.5 6 10 1 2 3 1 2 1.2 3 1.1 1 2 样例输出 2 5
 * 
 * 思路：求出三角形另一侧边的长度。半径平方-（宽/2）平方
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-13 下午6:09:14
 */
public class Acm6 {

	/**
	 * 长度
	 */
	private final static double width = 20;
	/**
	 * 高度宽度
	 */
	private final static double high = 2;

	/**
	 * @author daniel
	 * @time 2016-4-13 下午5:40:50
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入测试次数：");
		int n = Integer.parseInt(strin.readLine());

		while (n-- > 0) {
			System.out.print("请输入龙头个数：");
			// 输入有几个喷水装置
			int num = Integer.parseInt(strin.readLine());

			// 计算用
			Double[] rArray = new Double[num];
			// 输入的字符串截取成数组
			String[] split = strin.readLine().split(" ");

			// 接收一个字符串，变成一个字符串数组
			for (int i = 0; i < num; i++) {
				rArray[i] = Double.parseDouble(split[i]);
			}
			// 升序排列
			Arrays.sort(rArray);

			/*
			 * //确认输出 StringBuffer sb=new StringBuffer(); for(Double
			 * str:rArray){ sb.append(str); sb.append(" "); }
			 * System.out.println("您输入的是:"+sb);
			 */

			// 合计长度
			double sumWidth = 0;
			for (int j = rArray.length - 1; j > 0; j--) {
				
				//半径小于宽度的一半，没有用
				if(rArray[j]<(high/2)){
					continue;
				}
				
				// 取出该数组中的最大值
				sumWidth = sumWidth + 2 * Math.sqrt(rArray[j] * rArray[j] - ((high / 2) * (high / 2)));

				if (sumWidth > width) {
					// 说明
					System.out.println("需要:" + (rArray.length - j) + " 个");
					// 说明找到了尽量少的喷水装置
					break;
				}
			}

		}

	}

}
