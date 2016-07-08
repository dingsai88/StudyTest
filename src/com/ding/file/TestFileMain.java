package com.ding.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
/**
 * 读取本地文件
 * @author Administrator
 *
 */
public class TestFileMain {

	public static void main(String[] args) {

		System.out.println(readFile());
	}

	/**
	 * 读取文件内容
	 * @return
	 */
	public static String readFile() {

		String resultMessage = "";
		Reader reader = null;
		BufferedReader br = null;
		try {
			//D:\\zmScopt.txt 读取这个文件成string
			reader = new FileReader("D:\\zmScopt.txt");
			br = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String data = null;
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
			resultMessage = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultMessage;
	}

	
	
	
	
	
	
	


    /**
     * 读取文件D:\test.txt
     * HttpGetUtil.readFile("D:\test.txt");
     * @return
     */
    public static String readFile(String address){
        String resultInfo = "";
        BufferedReader br = null;
        try {
            br=new BufferedReader(new InputStreamReader(new FileInputStream(address),"UTF-8"));
            StringBuffer sb = new StringBuffer();
            String data = null;
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            resultInfo = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

}
