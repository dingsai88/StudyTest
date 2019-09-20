package com.ding.util;
/*

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
 




import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpPostUtil2 {
 
	*/
/**
	 * 发送 get 请求
	 * 
	 * @param
	 * @return {@link JsonObject}
	 *//*

	public static void main(String[] args) {

		String url = "http://localhost:8080/TDM/eBank/queryClearData";
		System.out.println(url);
		// 以下是测试用
		BufferedReader br = null;
		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"D:\\testYanLaoshi.txt"), "UTF-8"));
 
			String data = null;
			while ((data = br.readLine()) != null) {

			  
				String str="platform=jyAs&&json={\"userId\":\""+data+"\"}";
				System.out.println(str);
				String result=httpUrlConnection(url, str);
				
				method2("d:\\testAll201611151400.txt",data+"--"+result);
				
				*/
/*
				 try { 

					   File file = new File("d:\\testAll.txt");

					   // if file doesnt exists, then create it
					   if (!file.exists()) {
					    file.createNewFile();
					   }

					   FileWriter fw = new FileWriter(file.getAbsoluteFile());
					   BufferedWriter bw = new BufferedWriter(fw);
					   bw.write(data+"--"+result);
					   bw.close();

					 

					  } catch (IOException e) {
					   e.printStackTrace();
					  
					 }
*//*

			}
			// resultInfo = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(url);
		// resultInfo.replaceAll("?","");
		// System.out.println(resultInfo);
		// System.out.println(postJsonData(url,resultInfo));

	}

	

	  
    */
/**
     * 追加文件：使用FileWriter  
     *   
     * @param fileName  
     * @param content  
     *//*

    public static void method2(String fileName, String content) { 
    	FileWriter writer = null;
        try {   
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件   
            writer = new FileWriter(fileName, true);   
            writer.write(content);     
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            try {   
            	if(writer != null){
            		writer.close();   
            	}
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        } 
    }   
  
	 
	
	
	
	public static String  httpUrlConnection(String urlSTR,String requestStr) {
		String str="";
		try{
		 String urlPath = new String(urlSTR); 
 		    String param=requestStr;
		    //建立连接
		    URL url=new URL(urlPath);
		    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
		    //设置参数
		    httpConn.setDoOutput(true);   //需要输出
		    httpConn.setDoInput(true);   //需要输入
		    httpConn.setUseCaches(false);  //不允许缓存
		    httpConn.setRequestMethod("POST");   //设置POST方式连接
		    //设置请求属性
		    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		    httpConn.setRequestProperty("Charset", "UTF-8");
		    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		    httpConn.connect();
		    //建立输入流，向指向的URL传入参数
		    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
		    dos.writeBytes(param);
		    dos.flush();
		    dos.close();
		    //获得响应状态
		    int resultCode=httpConn.getResponseCode();
		    if(HttpURLConnection.HTTP_OK==resultCode){
		      StringBuffer sb=new StringBuffer();
		      String readLine=new String();
		      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
		      while((readLine=responseReader.readLine())!=null){
		        sb.append(readLine).append("\n");
		      }
		      responseReader.close();
		      System.out.println(sb.toString());
		      str=sb.toString();
		    } 
		}catch(Exception e){
			e.printStackTrace();
		}

		return str;
	}
 

 
}
*/
