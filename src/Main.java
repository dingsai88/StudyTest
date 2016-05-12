import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 吝啬的国度 时间限制：1000 ms | 内存限制：65535 KB 难度：3 描述
 * 在一个吝啬的国度里有N个城市，这N个城市间只有N-1条路把这个N个城市连接起来
 * 。现在，Tom在第S号城市，他有张该国地图，他想知道如果自己要去参观第T号城市，必须经过的前一个城市是几号城市（假设你不走重复的路）。 输入
 * 第一行输入一个整数M表示测试数据共有M(1<=M<=5)组
 * 每组测试数据的第一行输入一个正整数N(1<=N<=100000)和一个正整数S(1<=S<=100000)，N表示城市的总个数，S表示参观者所在城市的编号
 * 随后的N-1行，每行有两个正整数a,b(1<=a,b<=N)，表示第a号城市和第b号城市之间有一条路连通。 输出
 * 每组测试数据输N个正整数，其中，第i个数表示从S走到i号城市，必须要经过的上一个城市的编号。（其中i=S时，请输出-1） 样例输入 1 10 1 1 9
 * 1 8 8 10 10 3 8 6 1 2 10 4 9 5 3 7 样例输出 -1 1 10 10 9 8 3 1 1 8
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-12 下午2:53:46
 */
public class Main {
	public static void main(String[] args) {
		// 获得输入信息
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int[] N = new int[M];
		int[] S = new int[M];
		Queue<Integer> result = new ConcurrentLinkedQueue<Integer>();

		for (int i = 0; i < M; i++) {
			N[i] = in.nextInt();
			S[i] = in.nextInt();
			int[] a = new int[N[i] - 1];
			int[] b = new int[N[i] - 1];
			for (int j = 0; j < N[i] - 1; j++) {
				a[j] = in.nextInt();
				b[j] = in.nextInt();
			}
			// 找出第i个数字（即b[l]=i）时候a[l]的值赋给result
			for (int k = 1; k <= N[i]; k++) {
				if (S[i] == k)
					result.add(-1);
				for (int l = 0; l < N[i] - 1; l++) {
					if (b[l] == k) {
						result.add(a[l]);
						break;
					}
				}
			}
		}
		// 输出
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N[i]; j++) {
				int num = result.poll();
				System.out.print(num + " ");
			}

		}
	}
}