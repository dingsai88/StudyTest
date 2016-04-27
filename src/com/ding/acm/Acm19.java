package com.ding.acm;
import java.util.Scanner;
/**
 擅长排列的小明
时间限制：1000 ms  |  内存限制：65535 KB
难度：4
描述
小明十分聪明，而且十分擅长排列计算。比如给小明一个数字5，他能立刻给出1-5按字典序的全排列，如果你想为难他，在这5个数字中选出几个数字让他继续全排列，那么你就错了，他同样的很擅长。现在需要你写一个程序来验证擅长排列的小明到底对不对。
输入
第一行输入整数N（1<N<10）表示多少组测试数据，
每组测试数据第一行两个整数 n m (1<n<9,0<m<=n)
输出
在1-n中选取m个字符进行全排列，按字典序全部输出,每种排列占一行，每组数据间不需分界。如样例
样例输入
2
3 1
4 2
样例输出
1
2
3
12
13
14
21
23
24
31
32
34
41
42
43
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-27 下午12:32:37
 */
public class Acm19 {
	public static int[][] prev(int n, int m) {
		if (m == 1) {
			int a[][] = new int[n][1];
			for (int i = 0; i < n; i++) {
				a[i][0] = i + 1;
			}
			return a;
		} else {
			int a[][] = prev(n, m - 1);
			int b[][] = new int[a.length * (n - m + 1)][m];
			for (int i = 0; i < a.length * (n - m + 1); i++) {
				for (int j = 0; j < m - 1; j++) {
					b[i][j] = a[i / (n - m + 1)][j];
				}
				int c[] = new int[n];
				int k = 0;
				for (int j = 0; j < n; j++) {
					boolean flag = true;
					for (int l = 0; l < m - 1; l++) {
						if (b[i][l] == j + 1) {
							flag = false;
							break;
						}
					}
					if (flag) {
						c[k] = j + 1;
						k++;
					}
				}
				b[i][m - 1] = c[i % (n - m + 1)];
			}
			return b;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int a[][] = prev(n, m);
			for (int j = 0; j < a.length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					System.out.print(a[j][k]);
				}
				System.out.println();
			}
		}
	}


}
