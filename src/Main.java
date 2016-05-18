import java.util.Arrays;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-17 上午10:20:59
 */
public class Main {

	public static void main(String[] args) {

		int[] all = new int[] { 12, 45, 1, 342, 2 };

		System.out.println("old:" + Arrays.toString(all));

		// System.out.println("new:" + Arrays.toString(insertSort(all)));
		System.out.println("new:" + Arrays.toString(bubbleSort(all)));

	}

	/**
	 * 冒泡 •冒泡排序将已排序部分定义在右端，在遍历未排序部分的过程执行交换，将最大元素交换到最右端。
	 * 
	 * @author daniel
	 * @time 2016-5-17 下午3:01:04
	 * @param arr
	 * @return
	 */
	private static int[] bubbleSort(int[] arr) {

		int temp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
					System.out.println("ing:" + Arrays.toString(arr));
				}
			}
		}

		return arr;

	}

	/**
	 * 插入 •插入排序将已排序部分定义在左端，将未排序部分元的第一个元素插入到已排序部分合适的位置。
	 * 
	 * @author daniel
	 * @time 2016-5-17 下午3:01:09
	 * @param arr
	 * @return
	 */
	private static int[] insertSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j - 1] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
					System.out.println("ing:" + Arrays.toString(arr));

				} else {
					// 接下来是无用功
					break;
				}
			}
		}
		return arr;
	}

	private static int[] insertSort2(int[] arr) {

		return arr;
	}

}