package com.ding.acm;

import java.lang.reflect.Array;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
/**
 * 单调递增最长子序列
时间限制：3000 ms  |  内存限制：65535 KB
难度：4
描述
求一个字符串的最长递增子序列的长度
如：dabdbf最长递增子序列就是abdf，长度为4
输入
第一行一个整数0<n<20,表示有n个字符串要处理
随后的n行，每行有一个字符串，该字符串的长度不会超过10000
输出
输出字符串的最长递增子序列的长度
样例输入
3
aaa
ababc
abklmncdefg
样例输出
1
3
7
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-27 下午12:33:47
 */
public class Acm17 {

	private static char arr[] = new char[10001];
	private static int maxlen[] = new int[10001];

	public static void lis() {
 	Array.setInt(maxlen, maxlen.length - 1, 0);
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			maxlen[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (maxlen[i] < 1 + maxlen[j]) {
						maxlen[i] = 1 + maxlen[j];
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 0, len;
		int n = sc.nextInt();
		while (n-- > 0) {
			max = 0;
			String str = sc.next();
			arr = str.toCharArray();
 			lis();
 			len = arr.length;
			for (int i = 0; i < len; i++) {
				if (max < maxlen[i]) {
					max = maxlen[i];
 				}
			}
			System.out.println(max);
		}
		sc.close();
	}
}
