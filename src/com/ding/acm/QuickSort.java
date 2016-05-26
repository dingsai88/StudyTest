package com.ding.acm;

import java.util.Arrays;
/**
 开始:[4, 6, 8, 3, 5, 2, 1, 7]
 
 
 
 ----------------------- 从4开始对半折开 ，左边比4小右边比4大----------------------
---low:0--high:7--key:4
a:[1, 6, 8, 3, 5, 2, 1, 7]
b:[1, 6, 8, 3, 5, 2, 6, 7]
a:[1, 2, 8, 3, 5, 2, 6, 7]
b:[1, 2, 8, 3, 5, 8, 6, 7]
a:[1, 2, 3, 3, 5, 8, 6, 7]
---[1, 2, 3, 4, 5, 8, 6, 7]  

 ----------------------- 4左边的进行排序----------------------
---low:0--high:2--key:1
---[1, 2, 3, 4, 5, 8, 6, 7]


---low:1--high:2--key:2
---[1, 2, 3, 4, 5, 8, 6, 7]

 ----------------------- 4右边的进行排序----------------------

---low:4--high:7--key:5
---[1, 2, 3, 4, 5, 8, 6, 7]


---low:5--high:7--key:8
a:[1, 2, 3, 4, 5, 7, 6, 7]
---[1, 2, 3, 4, 5, 7, 6, 8]


---low:5--high:6--key:7
a:[1, 2, 3, 4, 5, 6, 6, 8]
---[1, 2, 3, 4, 5, 6, 7, 8]

 ----------------------- 完成----------------------

结束:[1, 2, 3, 4, 5, 6, 7, 8]

 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 下午5:48:21
 */
public class QuickSort {

	/**
	 * @author daniel
	 * @time 2016-5-26 下午4:16:55
	 * @param args
	 */

	@SuppressWarnings("all")
	public static void sort(int[] data, int left, int right) {
		// 枢纽元，一般以第一个元素为基准进行划分
		int leftCurrent = left;
		int rightCurrent = right;
		if (left < right) {

			// 从数组两端交替地向中间扫描
			int pivotKey = data[left];
			System.out.println("---leftCurrent:" + leftCurrent + "--rightCurrent:" + rightCurrent + "--key:" + pivotKey);
			// leftCurrent从左往右扫描，rightCurrent从右往左扫描
			while (leftCurrent < rightCurrent) {
				// 找到数组最右边比key小的值的下标
				while (leftCurrent < rightCurrent && pivotKey < data[rightCurrent]) {
					rightCurrent--;
				}
				if (leftCurrent < rightCurrent) {
					// 把右边比key小的值往前挪
					data[leftCurrent] = data[rightCurrent];
					leftCurrent++;
					System.out.println("a:" + Arrays.toString(data));

				}
				// 找到左边比key大的值的下标
				while (leftCurrent < rightCurrent && pivotKey > data[leftCurrent]) {
					leftCurrent++;
				}

				if (leftCurrent < rightCurrent) {
					// 把左边比key大的值往右挪
					data[rightCurrent] = data[leftCurrent];
					rightCurrent--;
					System.out.println("b:" + Arrays.toString(data));
				}
			}// end while
				// 枢纽元素移动到正确位置
			data[leftCurrent] = pivotKey;
			System.out.println("---" + Arrays.toString(data) + "\n\n");
			// 前半个子表递归排序
			sort(data, left, leftCurrent - 1);
			// 后半个子表递归排序
			sort(data, leftCurrent + 1, right);
		}// end if
	}// end sort

	public static void main(String[] args) {

		int[] c = { 4, 6, 8, 3, 5, 2, 1, 7 };

		System.out.println("开始:" + Arrays.toString(c)); 
		
		sort(c, 0, c.length - 1);
		System.out.println("结束:" + Arrays.toString(c));

	}

}
