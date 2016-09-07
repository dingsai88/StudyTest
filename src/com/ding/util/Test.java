package com.ding.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;


public class Test {
private static char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();  
	
	/**
	 * 将10进制转化为62进制 
	 * @param number 
	 * @param length 转化成的62进制长度，不足length长度的话高位补0，否则不改变什么
	 * @return
	 */
	public static String _10_to_62(long number, int length){
         Long rest=number;
         Stack<Character> stack=new Stack<Character>();
         StringBuilder result=new StringBuilder(0);
         while(rest!=0){
             stack.add(charSet[new Long((rest-(rest/62)*62)).intValue()]);
             rest=rest/62;
         }
         for(;!stack.isEmpty();){
             result.append(stack.pop());
         }
         int result_length = result.length();
         StringBuilder temp0 = new StringBuilder();
         for(int i = 0; i < length - result_length; i++){
        	 temp0.append('0');
         }
         
         return temp0.toString() + result.toString();

	}
	
	
	/**
	 * 将62进制转换成10进制数
	 * 
	 * @param ident62
	 * @return
	 */
	private static String convertBase62ToDecimal( String ident62 ) {
		Long decimal = 0L;
		int base = 62;
		Long keisu = 0L;
		int cnt = 0;

		byte ident[] = ident62.getBytes();
		for ( int i = ident.length - 1; i >= 0; i-- ) {
			int num = 0;
			if ( ident[i] > 48 && ident[i] <= 57 ) {
				num = ident[i] - 48;
			}
			else if ( ident[i] >= 65 && ident[i] <= 90 ) {
				num = ident[i] - 65 + 10;
			}
			else if ( ident[i] >= 97 && ident[i] <= 122 ) {
				num = ident[i] - 97 + 10 + 26;
			}
			keisu = Math.round(java.lang.Math.pow( (double) base, (double) cnt ));
			decimal += num * keisu;
			cnt++;
		} 
		return String.format( "%08d", decimal );
	}
	public static String compress(String orderid){
		if(orderid==null){
			return "";
		}
		 
		String  strNew1=_10_to_62( Long.parseLong(orderid.substring(2, 12)), 6);
     	String  strNew2 =_10_to_62( Long.parseLong(orderid.substring(12, orderid.length())), 6);
  
		return strNew1+strNew2;
	}
	
	
	public static String unCompress(String orderid){
		if(orderid==null){
			return "";
		}
 
		return "20"+convertBase62ToDecimal(orderid.substring(0, 6))+""+ convertBase62ToDecimal(orderid.substring(6, orderid.length()));
		
		
	}
	
	
	
	
	
	
	
	 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

	
	/*
	      SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
 

	        String d = "Sep 29, 2016 4:46:45 PM";
	        Date date = sdf.parse(d);
	        System.out.println(date.getTime());
	 
	*/
	
	
	        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a",Locale.ENGLISH);
	        Date d2 = null;
	      
	        d2 = sdf.parse("Sep 29, 2012 1:00:01 AM");
	        
	        Date d3 = new Date();
	        System.out.println("d2 ====== "+d2.getTime());
	        System.out.println("d3 ====== "+sdf.format(d3));
	
	        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	        System.out.println("d4 ====== "+myFmt1.format(d2));
	
	}
	public static void temp (String str){
		String temp=compress(str);	
	
		System.out.println("code=" +temp);
		System.out.println("uncode=" +unCompress(temp));
		System.out.println("aayuan=" +str);
	}
	

}