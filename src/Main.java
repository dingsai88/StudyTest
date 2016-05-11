import java.math.BigInteger;

import java.util.Arrays;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static boolean[][][] visited = new boolean[100][100][100];
	public static Queue<CupNode> nodeQueue;
	public static int[] cupCapacity = new int[3];
	public static int[] targetState = new int[3];

	public static void main(String[] args) { 
		
		BigInteger bi=new BigInteger("");
				
		
		
		
	}

	public static class CupNode {
		int[] state = new int[3];
		int stepNum;
		@Override
		public String toString() {
			return "CupNode [state=" + Arrays.toString(state) + ", stepNum=" + stepNum + "]";
		}
		
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
		 System.out.println("currentNode:"+currentNode);

		
		
		while (!nodeQueue.isEmpty()) {
			 System.out.println("---------while-----------\n");

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
						
						 System.out.println("------newNode:"+newNode);
						if (!visited[newNode.state[0]][newNode.state[1]][newNode.state[2]]) {
							nodeQueue.add(newNode);
							visited[newNode.state[0]][newNode.state[1]][newNode.state[2]] = true;
							System.out.println("newNode_ok:"+newNode);
						}
					}
				}
			}
		}
		return -1;
	}
}