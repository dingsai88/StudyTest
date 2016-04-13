/**
 * 
 */
package com.ding.util;

import java.util.HashMap;

import java.util.Map;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
  

/**
 * @author daniel
 * @created 2014-5-18
 */
public class ReadExcel { 
	/**
	 * excel路径
	 */
	public   static  String EXCEL_PATH = "lib/student_info.xls";
//	public static final String EXCEL_PATH = "lib/transactionTest.xlsx";
	
	 
	/**
	 * 要读取的excel路径
	 * @param excelPath
	 */
	public ReadExcel (String excelPath){
		
		EXCEL_PATH=excelPath;
	}
	
	/**
	 * 读取excel 生成List<Map<String, String>>
	 * @author daniel
	 * @time 2015-5-19 上午11:38:48
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, String>> readXls() throws IOException {
		InputStream is = new FileInputStream(ReadExcel.EXCEL_PATH);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	 
      
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {

				 
					Map<String, String> map = new HashMap<String, String>();
					int sum = hssfRow.getLastCellNum();
					System.out.println("sum:" + sum);
					for (int i = 0; i < sum; i++) {

						String key = "A" + (i + 1);
						System.out.println("key:" + key);
						String value = getValue(hssfRow.getCell(i));
						System.out.println("value:" + value + " \n\n");
						map.put(key, value);

					}

					list.add(map);
				}
			}
		}
		return list;
	}

	/**
	 * 格式化数据类型
	 * @author daniel
	 * @time 2015-5-19 上午11:39:31
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {

		try {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				// 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
