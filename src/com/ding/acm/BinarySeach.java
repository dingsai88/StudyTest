package com.ding.acm;

import java.util.Arrays;
/**
 * 二分法查找
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-25 下午5:18:22
 */
public class BinarySeach {

	/**
	 * @author daniel
	 * @time 2016-5-25 下午4:30:44
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = { 1, 2, 4, 6, 7, 8, 9, 12, 23, 34, 46, 68 };
		
		Arrays.sort(nums);
		
		System.out.println("" + binarySeach(nums, 12));

	}

	/**
	 * 查找值的下标
	 * 
	 * @author daniel
	 * @time 2016-5-25 下午5:13:05
	 * @param nums
	 * @param num
	 * @return
	 */
	public static int binarySeach(int[] nums, int num) {
		int high = nums.length - 1;
		int low = 0;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (num > nums[mid]) {
			    //总长度不变，下标抛弃上半段小的
				low = mid + 1;
			} else if (num < nums[mid]) {
				//长度变短
				high = mid - 1;
			} else {
				return mid;
			}

		}

		return -1;

	}

}
