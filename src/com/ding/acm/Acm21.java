package com.ding.acm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * 三个水杯
时间限制：1000 ms  |  内存限制：65535 KB
难度：4
描述
给出三个水杯，大小不一，并且只有最大的水杯的水是装满的，其余两个为空杯子。三个水杯之间相互倒水，并且水杯没有标识，只能根据给出的水杯体积来计算。现在要求你写出一个程序，使其输出使初始状态到达目标状态的最少次数。
输入
第一行一个整数N(0<N<50)表示N组测试数据
接下来每组测试数据有两行，第一行给出三个整数V1 V2 V3 (V1>V2>V3 V1<100 V3>0)表示三个水杯的体积。
第二行给出三个整数E1 E2 E3 （体积小于等于相应水杯体积）表示我们需要的最终状态
输出
每行输出相应测试数据最少的倒水次数。如果达不到目标状态输出-1
样例输入
2
6 3 1
4 1 1
9 3 2
7 1 1
样例输出
3
-1
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-17 上午10:20:59
 */
public class Acm21 {

	public static boolean[][][] visited = new boolean[100][100][100];
	public static Queue<CupNode> nodeQueue;
	public static int[] cupCapacity = new int[3];
	public static int[] targetState = new int[3];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int testnum = input.nextInt();
		for (int i = 0; i < testnum; i++) {
			for (int j = 0; j < 3; j++) {
				cupCapacity[j] = input.nextInt();
			}
			for (int j = 0; j < 3; j++) {
				targetState[j] = input.nextInt();
			}
			for (int j = 0; j < 100; j++)
				for (int k = 0; k < 100; k++)
					for (int l = 0; l < 100; l++)
						visited[j][k][l] = false;

			System.out.println(BFS());
		}
		input.close();
	}

	public static class CupNode {
		int[] state = new int[3];
		int stepNum;
	}

	public static boolean isAchieveTarget(CupNode currentNode) {
		for (int i = 0; i < 3; i++) {
			if (currentNode.state[i] != targetState[i]) {
				return false;
			}
		}
		return true;
	}

	public static void pourWater(int destination, int source, CupNode newNode) {
		int leftWaterFiled = cupCapacity[destination] - newNode.state[destination];
		if (newNode.state[source] >= leftWaterFiled) {
			newNode.state[destination] += leftWaterFiled;
			newNode.state[source] -= leftWaterFiled;
		} else {
			newNode.state[destination] += newNode.state[source];
			newNode.state[source] = 0;
		}
	}

	public static int BFS() {
		nodeQueue = new LinkedList<CupNode>();
		CupNode currentNode = new CupNode();
		currentNode.state[0] = cupCapacity[0];
		currentNode.state[1] = currentNode.state[2] = 0;
		nodeQueue.add(currentNode);
		visited[currentNode.state[0]][currentNode.state[1]][currentNode.state[2]] = true;
		while (!nodeQueue.isEmpty()) {
			currentNode = nodeQueue.poll();
			if (isAchieveTarget(currentNode)) {
				return currentNode.stepNum;
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 1; j < 3; j++) {
					int k = (i + j) % 3;
					if (currentNode.state[i] != 0 && cupCapacity[k] > currentNode.state[k]) {
						CupNode newNode = new CupNode();
						for (int l = 0; l < 3; l++) {
							newNode.state[l] = currentNode.state[l];
						}
						newNode.stepNum = currentNode.stepNum;
						pourWater(k, i, newNode);
						newNode.stepNum = currentNode.stepNum + 1;
						if (!visited[newNode.state[0]][newNode.state[1]][newNode.state[2]]) {
							nodeQueue.add(newNode);
							visited[newNode.state[0]][newNode.state[1]][newNode.state[2]] = true;
						}
					}
				}
			}
		}
		return -1;
	}
}