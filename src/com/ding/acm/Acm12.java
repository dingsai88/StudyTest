package com.ding.acm;

import java.util.Scanner;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-13 下午6:09:14
 */
public class Acm12 {

	/**
	 * 长度
	 */
	private   static double width = 20;
	/**
	 * 高度宽度
	 */
	private   static double high = 2;

	/**
	 * @author daniel
	 * @time 2016-4-13 下午5:40:50
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入测试次数：");
		int n = input.nextInt();

		while (n-- > 0) {
			System.out.print("三个整数n,w,：");
			// 输入有几个喷水装置
			int num = input.nextInt();

			// 计算用
			int[][] rArray = new int[num][2];

			width=input.nextInt();
			high=input.nextInt();
			
			
			

			// 接收一个字符串，变成一个字符串数组
			for (int i = 0; i < num; i++) {
				rArray[i][0] =input.nextInt();
				rArray[i][1] =input.nextInt();
			}

			
			
			
			

		}

	}

}
