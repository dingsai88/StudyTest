package com.ding.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

 import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpPostUtil {
     @SuppressWarnings("unused")
    private static final String APPLICATION_JSON = "application/json";
    @SuppressWarnings("unused")
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    // http请求连接参数默认值
    private static int connectionTimeOut = 15000;//连接超时时长
    private static int readTimeOut = 25000;//读取超时时长
    @SuppressWarnings("unused")
    private static int retryTimes = 3;//重试次数

 


    public static JsonObject postJsonData(String url, String jsonStrData) {
    	
    //	HttpRequestUtil.sendPost(url,contet,false);
      
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
        JsonObject jsonObject = null;
        try {

            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            // HttpEntity entity = new StringEntity(jsonStrData);
            // 修复 POST json 导致中文乱码
            HttpEntity entity = new StringEntity(jsonStrData, "UTF-8");
            post.setEntity(entity);
            post.setHeader("Content-type", "application/json");
            HttpResponse resp = closeableHttpClient.execute(post);
            jsonObject = convertResponseBytes2JsonObj(resp);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    static JsonParser jsonParser = new JsonParser();

    /**
     * 发送 get 请求
     *
     * @param
     * @return {@link JsonObject}
     */
    public static void main(String[] args) throws Exception{

        String url = "http://localhost:8080/TDM/eBank/queryClearData";
        System.out.println(url);
         //以下是测试用
        String resultInfo = "";
         BufferedReader br = null;
        try {
        	
        	  br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test.txt"),"UTF-8"));  
    
            StringBuffer sb = new StringBuffer();
            String data = null;
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            resultInfo = sb.toString();
         } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(url);
       // resultInfo.replaceAll("?","");
        
        
     
        
        System.out.println(resultInfo);
        String str="http://www.juxinli.com/api/access_house_fund_raw_data?client_secret=7cc69065511140aea16c0a8974c33e5f&access_token=2d2a5ae26d654b189a806f2e67e94dd9&token=343a98fb9bbb4efba83bb116b6926e8d";

      //  System.out.println(postJsonData1(str,resultInfo));

        
        
        
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpHost proxy = new HttpHost(str, 80);
        httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY,proxy);
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),20 * 1000);
        HttpConnectionParams.setSoTimeout(httpClient.getParams(), 20 * 1000);
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = httpClient.execute(httpget);
        
        
        try {  
            HttpEntity entity = response.getEntity();  
            System.out.println("----------------------------------------");  
            System.out.println(response.getStatusLine());  
            if (entity != null) {  
                System.out.println("Response content length: " + entity.getContentLength());  
                System.out.println(EntityUtils.toString(entity));  
                EntityUtils.consume(entity);  
            }  
        } finally {  
            response.close();  
        }          
        
        
        

    }

    public static String postJsonData1(String url, String content ) {
        String result = "";
     
        try {
            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(readTimeOut);
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            connection.connect();


            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {
                result = reader.readLine();
                if (null == result) break;

            }
        }   catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Header 为 application/json POST 请求数据
     *
     * @param resp
     * @return {@link JsonObject}
     */
    private static JsonObject convertResponseBytes2JsonObj(HttpResponse resp) {

        JsonObject jsonObject = null;
        try {
            InputStream respIs = resp.getEntity().getContent();
            byte[] respBytes = IOUtils.toByteArray(respIs);
            String result = new String(respBytes, Charset.forName("UTF-8"));
            if (null == result || result.length() == 0) {
                // TODO
                System.err.println("无响应");
            } else {
                System.out.println(result);
                if (result.startsWith("{") && result.endsWith("}")) {
                    jsonObject = (JsonObject) jsonParser.parse(result);
                } else {
                    // TODO
                    System.err.println("不能转成JSON对象");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    
    
    
    
    
    
    

    /**
     * URL重定向结局302跳转
     * @param uc
     * @return
     */
    private static URLConnection reload(URLConnection uc)   {
        try {
            HttpURLConnection huc = (HttpURLConnection) uc;
            if (huc.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP || huc.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM) {
                return reload(new URL(huc.getHeaderField("location")).openConnection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uc;
    }

    
    
    
    

}
