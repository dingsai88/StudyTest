package com.ding.acm;

import java.util.Scanner;

public class Acm7_temp {

	/**
	 * @author daniel
	 * @time 2016-4-14 下午2:54:40
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		// System.out.println("请输入有几组人家");
		int row = input.nextInt();
		int[][][] house = new int[row][][];
		for (int index = 0; index < row; index++) {
			// System.out.println("请输入有几户人家第" + (index+1) + "组");
			int num = input.nextInt();
			house[index] = new int[num][2];
			for (int i = 0; i < num; i++) {
				// System.out.println("请输入第" + index + "组第" + (i+1) + "户人家坐标");
				house[index][i][0] = input.nextInt();
				house[index][i][1] = input.nextInt();
			}

		}
		cal(house);

	}

	public static void cal(int a[][][]) {

		int aa = a.length;
		int max = 0;
		int ret = 0;

		for (int i = 0; i < aa; i++) {
			int maxx = a[i][0][0];
			int maxy = a[i][0][1];
			int distance = 0;
			int mindistance = 0;
            //最小坐标
			int xlast = 0;
			int ylast = 0;
			for (int i1 = 0; i1 < a[i].length; i1++) {
				if (a[i][i1][0] > maxx) {
					maxx = a[i][i1][0];
				}
			}
			for (int i1 = 0; i1 < a[i].length; i1++) {
				if (a[i][i1][1] > maxy) {
					maxy = a[i][i1][1];
				}
			}
			for (int x = 0; x < maxx + 1; x++) {
				for (int y = 0; y < maxy + 1; y++) {
					distance = 0;
					for (int i1 = 0; i1 < a[i].length; i1++) {

						distance = distance + Math.abs(x - a[i][i1][0]) + Math.abs(y - a[i][i1][1]);
						// 首次进来赋初始值
						if ((x == 0) && (y == 0)) {
							mindistance = distance;
						}

					}
					if (distance < mindistance) {
						xlast=x;
						ylast=y;
						mindistance = distance;
					}
				}

			}
			System.out.println("坐标是("+xlast+","+ylast+") 距离:"+mindistance);
		}

	}

}
