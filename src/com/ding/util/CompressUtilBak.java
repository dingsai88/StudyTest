package com.ding.util;

import java.net.URLEncoder;
 
/** 
 * 浦发银行专用压缩订单号
 * @author daniel
 * @time 2015-8-4 下午4:13:38
 */
public class CompressUtilBak {
//	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		//	'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/', };
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',   };

	/**20150724141711685418244L
	 * 2015072414
	 * 1711685418244
	 * @param args
	 */
	public static void main(String[] args) {
	 
		 
		temp("20150805111410734412501");
		/*temp("20150724141711685418244");
 
		temp("20150724000510417213033");*/
	}
	public static void temp(String str){
		String t=compress(str);
		System.out.println(t);
		System.out.println(str);
		//System.out.println(URLEncoder.encode(t));
		System.out.println(unCompress(t));
		
	}
	
	/**
	 * 对订单号进行加密
	 * @author daniel
	 * @time 2015-8-4 下午2:31:57
	 * @param orderid
	 * @return
	 */
	public static String compress(String orderid){
		if(orderid==null){
			return "";
		}
		 
		String  strNew1=compressNumber( Long.parseLong(orderid.substring(4, 12)), 5);
     	String  strNew2 =compressNumber( Long.parseLong(orderid.substring(12, orderid.length())), 5);
 

		System.out.println(strNew1);
		System.out.println(strNew2);
     	
		return strNew1+strNew2;
	}

	/**
	 * 对订单号解密
	 * @author daniel
	 * @time 2015-8-4 下午2:56:19
	 * @param orderid
	 * @return
	 */
	public static String unCompress(String orderid){
		if(orderid==null){
			return "";
		}
		String temp=orderid.substring(0, 5);
		System.out.println(temp);
		System.out.println(unCompressNumber(orderid.substring(0, 5)));
		System.out.println(unCompressNumber(orderid.substring(5, orderid.length())));
		return   "2015"+unCompressNumber(orderid.substring(0, 5))+""+ unCompressNumber(orderid.substring(5, orderid.length()));
 	
		
		
	}
	
	
	
	
	/**
	 * 把10进制的数字转换成64进制
	 * 
	 * @param number
	 * @param shift
	 * @return
	 */
	private static String compressNumber(long number, int shift) {
		char[] buf = new char[62];
		int charPos = 62;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= shift;
		} while (number != 0);
		return new String(buf, charPos, (62 - charPos));
	}

	/**
	 * 把64进制的字符串转换成10进制
	 * 
	 * @param decompStr
	 * @return
	 */
	private static long unCompressNumber(String decompStr) {
		long result = 0;
		for (int i = decompStr.length() - 1; i >= 0; i--) {
			if (i == decompStr.length() - 1) {
				result += getCharIndexNum(decompStr.charAt(i));
				continue;
			}
			for (int j = 0; j < digits.length; j++) {
				if (decompStr.charAt(i) == digits[j]) {
					result += ((long) j) << 6 * (decompStr.length() - 1 - i);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param ch
	 * @return
	 */
	private static long getCharIndexNum(char ch) {
		int num = ((int) ch);
		if (num >= 48 && num <= 57) {
			return num - 48;
		} else if (num >= 97 && num <= 122) {
			return num - 87;
		} else if (num >= 65 && num <= 90) {
			return num - 29;
		} else if (num == 43) {
			return 62;
		} else if (num == 47) {
			return 63;
		}
		return 0;
	}

}