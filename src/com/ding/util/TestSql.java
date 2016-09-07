package com.ding.util;

import java.util.ArrayList;
import java.util.List;

public class TestSql {

	// 身份证
	public static List<String> idcard = new ArrayList<String>();
	// ubids
	public static List<String> ubids = new ArrayList<String>();

	public static void setUids() {
		// ubids.add("5997866");
		ubids.add("5997869");
		//
		ubids.add("6227755");
		ubids.add("6005771");
		ubids.add("6005772");

		ubids.add("5807403");
		ubids.add("5807402");

		ubids.add("5604140");
		ubids.add("5951116");
		ubids.add("5786384");
		ubids.add("5786383");

		ubids.add("5954907");
		ubids.add("6227721");
		ubids.add("5722483");
		ubids.add("5851738");
		ubids.add("5851739");
		ubids.add("5851737");

		ubids.add("6112772");
		ubids.add("5833188");
		ubids.add("5855816");
		ubids.add("6093885");
		ubids.add("6093884");
		ubids.add("6187617");

		ubids.add("5787165");
		ubids.add("5787164");

		ubids.add("6004223");
		ubids.add("6017937");
		ubids.add("6017936");

		ubids.add("6115752");
		ubids.add("6145131");
		ubids.add("6056168");
		ubids.add("5972248");
		ubids.add("5721400");
		ubids.add("6176141");
		ubids.add("6221135");
		ubids.add("5949752");
		ubids.add("5949751");

		ubids.add("5972865");
		ubids.add("6113034");
		ubids.add("5997295");
		ubids.add("5815026");
		ubids.add("6176192");
		ubids.add("6182835");
		ubids.add("5969607");
		ubids.add("6143368");
		ubids.add("5773547");
		ubids.add("5848284");
		ubids.add("5848285");

		ubids.add("6112861");
		ubids.add("5790354");
		ubids.add("5790353");
		ubids.add("5992131");
		ubids.add("5850279");
		ubids.add("5771562");
		ubids.add("5771563");
		ubids.add("5854126");

		ubids.add("5794312");
		ubids.add("5794311");
		ubids.add("5789275");
		ubids.add("6113978");
		ubids.add("6212971");
		ubids.add("5950488");
		ubids.add("6141119");
		ubids.add("6141118");
		ubids.add("6139592");

	}

	public static void setTest() {}

	public static void main(String[] args) {
	String	clientId="";
	 
		clientId=clientId.substring(0,clientId.indexOf("ddd"));
		
		System.out.println(clientId);

	}
	public static void main1(String[] args) {
		setUids();
		setTest();

		StringBuffer sql = new StringBuffer();

		for (String str : ubids) {
			// sql.append(" or  billid like '%_"+str+"%' ");
			// sql.append("\n or  billid like '%\\_"+str+"%'  \n");
			// sql.append("\n or  billid like '%\\_"+str+"%'  escape '\\' \n");
			// 最终成功带有_
			sql.append("   or  billid like '%|_" + str + "|_%'     escape '|' ");
		}
		// sql.append("   or  billid like '%|_1235214|_%'     escape '|' ");
		System.out.println(sql.append("   ;"));

		// System.out.println(sql.append("  ;"));
		//

	}
	 

}
