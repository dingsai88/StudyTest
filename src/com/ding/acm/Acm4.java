package com.ding.acm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 描述
输入三个字符（可以重复）后，按各字符的ASCII码从小到大的顺序输出这三个字符。
输入
第一行输入一个数N,表示有N组测试数据。后面的N行输入多组数据，每组输入数据都是占一行，有三个字符组成，之间无空格。
输出
对于每组输入数据，输出一行，字符中间用一个空格分开。
样例输入
2
qwe
asd
样例输出
e q w
a d s
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-4-13 下午3:47:42
 */
public class Acm4 { 
	/**
	 * @author daniel
	 * @time 2016-4-13 下午3:16:33
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入测试次数：");
		String count = strin.readLine();
		int c = Integer.parseInt(count);
		while (c-- > 0) {
			System.out.print("请输入字符串：");
			String str = strin.readLine();
			System.out.println("您输入的是:" + str + ";排序后:" + sort(str));
		} 
	} 
	/**
	 * 排序
	 * 
	 * @author daniel
	 * @time 2016-4-13 下午3:20:44
	 * @param str
	 */
	public static String sort(String str) {
		char[] ch = new char[str.length()];
		char temp;
		for (int i = 0; i < str.length(); i++) {
			ch[i] = str.charAt(i);
		}
		//冒泡
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - 1; j++) {
				if (ch[j] > ch[j + 1]) {
					temp = ch[j];
					ch[j] = ch[j + 1];
					ch[j + 1] = temp;
				}
			}
		} 
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<ch.length;i++){
			sb.append(ch[i]);
			sb.append(" ");
		}		
		return sb.toString();
	}
}
