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
	 * ���� get ����
	 * 
	 * @param
	 * @return {@link JsonObject}
	 *//*

	public static void main(String[] args) {

		String url = "http://localhost:8080/TDM/eBank/queryClearData";
		System.out.println(url);
		// �����ǲ�����
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
     * ׷���ļ���ʹ��FileWriter  
     *   
     * @param fileName  
     * @param content  
     *//*

    public static void method2(String fileName, String content) { 
    	FileWriter writer = null;
        try {   
            // ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�   
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
		    //��������
		    URL url=new URL(urlPath);
		    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
		    //���ò���
		    httpConn.setDoOutput(true);   //��Ҫ���
		    httpConn.setDoInput(true);   //��Ҫ����
		    httpConn.setUseCaches(false);  //��������
		    httpConn.setRequestMethod("POST");   //����POST��ʽ����
		    //������������
		    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    httpConn.setRequestProperty("Connection", "Keep-Alive");// ά�ֳ�����
		    httpConn.setRequestProperty("Charset", "UTF-8");
		    //����,Ҳ���Բ�������connect��ʹ�������httpConn.getOutputStream()���Զ�connect
		    httpConn.connect();
		    //��������������ָ���URL�������
		    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
		    dos.writeBytes(param);
		    dos.flush();
		    dos.close();
		    //�����Ӧ״̬
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
