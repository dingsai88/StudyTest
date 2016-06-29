 
 
import java.util.Scanner;

/**
描述
小明被一个问题给难住了，现在需要你帮帮忙。问题是：给出两个正整数，求出它们的最大公约数和最小公倍数。
输入
第一行输入一个整数n（0<n<=10000)，表示有n组测试数据;
随后的n行输入两个整数i,j（0<i,j<=32767)。
输出
输出每组测试数据的最大公约数和最小公倍数
样例输入
3
6 6
12 11
33 22
样例输出
6 6
1 132
11 66


设两个数是a,b最大公约数是p,最小公倍数是q
那么有这样的关系:ab=pq
所以q=ab/p

 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-15 上午11:43:12
 */
public class Main {
	public static void main(String[] atg) throws Exception {
		Scanner s = new Scanner(System.in);
		int i = s.nextInt();
 		int n = 0;
		int row[] = new int[i];
		int col[] = new int[i];
		while (n < i) {
			int one = s.nextInt();
			int two = s.nextInt();
			row[n] = one;
			col[n] = two;
			n++;
			int maxGongYue=gongYueShu(one,two);
			//a*b=p*q
			System.out.println(maxGongYue + " " + (one * two) / maxGongYue);
		}
		s.close();


	}
	/**
	 设两个数是a,b最大公约数是p,最小公倍数是q
那么有这样的关系:ab=pq
所以q=ab/p
	 */
	private static int gongYueShu(int i ,int j){
		int t;
		while(i%j!=0){
			t=i%j;
			i=j;
			j=t;
		}	
		return j;
	}
	
	 
}
