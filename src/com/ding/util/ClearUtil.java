package com.ding.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 比对姓名
 * 
 * @author daniel
 * 
 */
public class ClearUtil {

	/**
	 * 
	 * @param realName
	 *            已确认的真实系姓名
	 * @param currentName
	 *            传入的运营商姓名
	 * @return
	 */
	public static boolean clearName(String realName, String currentName) {
		System.out.println("比对姓名:" + realName + ";" + currentName);

		boolean bo = false;
		if (realName == null || currentName == null) {
			System.out.println("姓名有为空:" + realName + ";" + currentName);
			return bo;
		}

		// 去掉空字符
		realName = realName.replaceAll(" ", "");
		currentName = currentName.replaceAll(" ", "");

		// 长度不一样不用匹配
		if (realName.length() != currentName.length()) {
			System.out.println("姓名长度不一致:" + realName + ";" + currentName);
			return bo;
		}

		// 全部汉字
		String reg = "[\\u4e00-\\u9fa5]*";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(currentName);
		StringBuilder sb = new StringBuilder();
		while (mat.find()) {
			String t = mat.group();
			sb.append(t);
		}

		String charTmp = sb.toString();
		// 抓取姓名不包含汉字
		if (charTmp.length() < 1) {
			return bo;
		}
		int trueCount = 0;

		for (int i = 0; i < charTmp.length(); i++) {
			char t = charTmp.charAt(i);
			if (realName.charAt(currentName.indexOf(t, (i < 1 ? 0 : i - 1))) == t) {
				trueCount++;
			}
		}

		if (trueCount == charTmp.length()) {
			bo = true;
		}

		return bo;

	}

/**
 * 
 * @param realIdCard 真实身份证
 * @param currentIdCard 模糊身份证
 * @return
 */
	public static boolean clearIdCard(String realIdCard, String currentIdCard) {
		System.out.println("身份证:" + realIdCard + ";" + currentIdCard);
		boolean bo = false;

		if (realIdCard == null || currentIdCard == null) {
			System.out.println("身份证有为空:" + realIdCard + ";" + currentIdCard);
			return bo;
		}

		// 去掉空字符
		realIdCard = realIdCard.replaceAll(" ", "");
		currentIdCard = currentIdCard.replaceAll(" ", "");

		// 长度不一样不用匹配
		if (realIdCard.length() != currentIdCard.length()) {
			System.out.println("身份证长度不一致:" + realIdCard + ";" + currentIdCard);
			return bo;
		}

		int numberCount = 0;
		int tureCount=0;

	 
		for (int i = currentIdCard.length(); --i >= 0;) {	
			System.out.println("currentIdCard:" + currentIdCard.charAt(i) + ";realIdCard:" + realIdCard.charAt(i));
			if (Character.isDigit(currentIdCard.charAt(i))) {
				//是数字的个数
				numberCount++;
				if(currentIdCard.charAt(i)==realIdCard.charAt(i)){
					//匹配相等的个数
					tureCount++;
				}
			}
		}
		
		
 
        //数字部分全部相等
		if (numberCount == tureCount) {
			bo = true;
		}
		
		return bo;

	}

	public static void main(String[] args) throws Exception {
		
		System.out.println("1:" + ClearUtil.clearIdCard("52262619800410121X", "522626**800410121X") + "\n\n");
		System.out.println("2:" + ClearUtil.clearIdCard("220503198006080528", "*****3198006080528") + "\n\n");
		System.out.println("1:" + ClearUtil.clearIdCard("632824197603110117", "632824197603110***") + "\n\n");
		System.out.println("3:" + ClearUtil.clearIdCard("110107198408270939", "uu0107198408270***") + "\n\n");
		System.out.println("5:" + ClearUtil.clearIdCard("500384199104244185", "500384*******44185") + "\n\n");
		System.out.println("6:" + ClearUtil.clearIdCard("52262619800410121X", "52262619800410121X") + "\n\n\n\n");
		System.out.println("7:" + ClearUtil.clearIdCard("220503198006080528", "*****3198006080521") + "\n\n");
		System.out.println("8:" + ClearUtil.clearIdCard("632824197603110117", "632824197603111***") + "\n\n");
		System.out.println("9:" + ClearUtil.clearIdCard("110107198408270939", "uu0107198408271***") + "\n\n");
		System.out.println("10:" + ClearUtil.clearIdCard("500384199104244185", "500384*******41185") + "\n\n");

	/*	System.out.println("1:" + ClearName.clearName("丁赛", "丁*") + "\n\n");
		System.out.println("2:" + ClearName.clearName("丁赛", "*赛") + "\n\n");
		System.out.println("3:" + ClearName.clearName("丁赛", "*丁") + "\n\n");
		System.out.println("4:" + ClearName.clearName("林林林", "林林*") + "\n\n");
		System.out.println("5:" + ClearName.clearName("林林林", "*林*") + "\n\n");
		System.out.println("6:" + ClearName.clearName("林林林", "***") + "\n\n");
		System.out.println("7:" + ClearName.clearName("林林林", "林**") + "\n\n");
		System.out.println("8:" + ClearName.clearName("林林林", "**林") + "\n\n");
		System.out.println("aa1:" + ClearName.clearName("林林林", "林林林") + "\n\n");
		System.out.println("aa2:" + ClearName.clearName("林林林", "xx林") + "\n\n");
		System.out.println("aa3:" + ClearName.clearName("林林林", "x林林") + "\n\n");
		System.out.println("aa4:" + ClearName.clearName("林林林", "林x林") + "\n\n");
		System.out.println("aa5:" + ClearName.clearName("林林林", "林xz") + "\n\n");
		System.out.println("aa6:" + ClearName.clearName("林林林", "＊＊林") + "\n\n");
		System.out.println("aa7:" + ClearName.clearName("林林林", "＊＊z") + "\n\n");
		System.out.println("aa8:" + ClearName.clearName("林林林", "xxx") + "\n\n");
		System.out.println("bb1:" + ClearName.clearName("丁赛", "丁赛") + "\n\n");
		System.out.println("bb2:" + ClearName.clearName("丁赛", "丁*") + "\n\n");
		System.out.println("bb3:" + ClearName.clearName("丁赛", "*赛") + "\n\n");
		System.out.println("bb4:" + ClearName.clearName("丁赛", "*z") + "\n\n");
		System.out.println("ccc1:"
				+ ClearName.clearName("邢诗晓（首次预存款只消费不退还）", "************司")
				+ "\n\n");*/
	}

}
