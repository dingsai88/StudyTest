package com.ding.acm;

import java.util.*;

public class DFS {

	/**
	 * @param args
	 */
	final static int MAXN = 100;
	static Scanner scan = new Scanner(System.in);

	public static class Stack {
		int Depth;
		int Dot;

		Stack() {
			Depth = -1;
			Dot = -1;
		}

		public int getTopDot() {
			return Dot;
		}

		public int getDepth() {
			return Depth;
		}

		public void PushDate(int dep, int dot) {
			Depth = dep;
			Dot = dot;
			System.out.println(dot + " The depth is:" + dep);
		}
	}

	public static void main(String[] args) {
		int[][] Graph = new int[MAXN][MAXN];
		boolean[] vis = new boolean[MAXN];
		for (int i = 0; i < MAXN; i++) {
			//将指定的值分割数组每个元素
			Arrays.fill(Graph[i], 0);
		}
		Arrays.fill(vis, false);
		int base = 0;
		int top = 0;
		Stack[] stack = new Stack[MAXN];
		int n, m;// n为点数，m为边数
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i = 0; i < 2 * n; i++) {
			stack[i] = new Stack();
		}
		for (int i = 0; i < m; i++) {
			int a, b;
			a = scan.nextInt();
			b = scan.nextInt();
			Graph[a][b] = Graph[b][a] = 1;
		}
		for (int i = 0; i < n; i++) {
			if (vis[i] == false) {
				int dep = -1;
				int dot = -1;
				stack[top].PushDate(1, i);
				top++;
				vis[i] = true;
				while (base != top) {
					dot = stack[top - 1].getTopDot();
					for (int j = 0; j < n; j++) {
						if (Graph[dot][j] == 1 && vis[j] == false) {
							dep = stack[top - 1].getDepth() + 1;
							stack[top].PushDate(dep, j);
							top++;
							vis[j] = true;
							break;
						}
						if (j == n - 1)// 如果无路可走，出栈
							top--;
					}

				}
			}
		}
	}
}