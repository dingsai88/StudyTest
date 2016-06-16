package com.ding.report.general.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.ding.util.ReadExcel;

import java.util.List;
import java.util.Map;

/**
 * 内存溢出d -server -Xms800m -Xmx800m -XX:PermSize=64M -XX:MaxNewSize=256m
 * -XX:MaxPermSize=128m -Djava.awt.headless=true 发送文件到总部。导出sql 到excel。excel;
 * excel用2003的老格式
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2015-5-19 上午11:35:12
 */
public class MainReportZongBu {
	public static final String EXCEL_PATH = "lib/wantSee20150501_lte_201506101137.xls";
	/**
	 * 要生成的文件名
	 */
	public static String fileName = "transaction";
	/**
	 * 日期注意格式
	 */
	public static String date = "20150501";

	// public static final String EXCEL_PATH = "lib/student_info.xls";
	/**
	 * @author daniel
	 * @time 2015-5-19 上午11:35:03
	 * @param args

	public static void main(String[] args) throws Exception {
		ReadExcel xlsMain = new ReadExcel(EXCEL_PATH);
		writeFile(printList(xlsMain.readXls()));
		System.out.println("end"); 
	}	 */

	/**
	 * 写入txt文件
	 * 
	 * @author ghost
	 * @time 2015-5-19 上午11:48:47
	 * @param result

	public static void writeFile(String result) {

		try {
			File file = new File("c://" + fileName + "_" + date + System.currentTimeMillis() + ".txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(result);
				output.flush();
				output.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * 将list内容以文本形式输出
	 * 
	 * @param list

	public static String printList(List<Map<String, String>> list) {
		StringBuilder result = new StringBuilder();
		try {

			for (Map<String, String> map : list) {
				int keys = map.size();
				for (int index = 1; index <= keys; index++) {
					result.append((map.get("A" + index) != null ? map.get("A" + index).trim().replace('\n', '￠').replace('\r', '￠').replace('|', '€') : "")).append("|");
				}
				result.append("\n");
			}

		} catch (Exception e) {
			System.out.println(":error:" + e.getMessage());
		}

		return result.toString();
	}
	 */
}
