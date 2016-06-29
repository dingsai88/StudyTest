package com.ding.acm;
/**
 * 历史:
 * 百鸡问题是一个数学问题，出自中国古代约5—6世纪成书的《张邱建算经》，是原书卷下第38题，也是全书的最后一题，该问题导致三元不定方程组，其重要之处在于开创“一问多答”的先例。
 * 
 * 问题描述:
 * 百钱买百鸡的问题算是一套非常经典的不定方程的问题，题目很简单：公鸡5文钱一只，母鸡3文钱一只，小鸡3只一文钱，
         用100文钱买一百只鸡,其中公鸡，母鸡，小鸡都必须要有，问公鸡，母鸡，小鸡要买多少只刚好凑足100文钱。
 * 576699909@qq.com
 * @author Daniel
 *
 */
public class BaiJiWenTiMain {

	public static void main(String[] args) {
		// 可买公鸡上限
		for (int x = 0; x <= 19; x++) {
			// 可买母鸡上限
			for (int y = 0; y <= 33; y++) {
				// 剩余小鸡
				int z = 100 - x - y;
				if ((x * 5 + y * 3 + z / 3 == 100) && z % 3 == 0) {
					System.out.println("可买鸡翁只数:" + x);
					System.out.println("可买鸡母只数:" + y);
					System.out.println("可买鸡雏只数:" + z+"\n");
				}
			}
		}
	}

}