package com.ding.util;

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
	public static void main(String[] args) {
		
		String str="001312100000-1304|housingfund_shijiazhuang";
		String [] list=str.split("\\|");
 
		for(String a:list){
			System.out.println(a);
		}
		
		
		/*temp("20150805111410734412501");
		temp("20150724141711685418244");
		temp("20150724000510417213033");
		temp("20150805111410734412501");
		*/
	}
	public static void temp (String str){
		String temp=compress(str);	
	
		System.out.println("code=" +temp);
		System.out.println("uncode=" +unCompress(temp));
		System.out.println("aayuan=" +str);
	}
	

}