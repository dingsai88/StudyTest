package com.ding.algorithm.study1;

import java.util.ArrayList;
import java.util.List;

public class Zmain {
	/**
	 * @author daniel
	 * @time 2016-5-17 обнГ4:31:45
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( ""+ones(1));
		System.out.println( ""+ones(2));
		System.out.println( ""+ones(3));
		System.out.println( ""+ones(4));
		System.out.println( ""+ones(5));
		System.out.println( ""+ones(6));		
		Math.hypot(3, 5);		
		List list=new ArrayList();		
	}

	public static int ones(int n) {
		if (n < 2)
			return n;
		return n % 2 + ones(n / 2);
	}
}
