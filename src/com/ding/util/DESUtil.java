package com.ding.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密工具类
 * @author 沈志辉
 */
public class DESUtil {

	private static final String PASSWORD_CRYPT_KEY = "12345678"; 
	private final static String DES = "DES"; 

	/** 
	* 加密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回加密后的数据 
	* @throws Exception 
	*/ 
	private static byte[] encrypt(byte[] src, byte[] key)throws Exception { 
		
		SecureRandom sr = new SecureRandom(); 
		DESKeySpec dks = new DESKeySpec(key); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		Cipher cipher = Cipher.getInstance(DES); 
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr); 
		return cipher.doFinal(src); 
	} 

	/** 
	* 解密 
	* @param src 数据源 
	* @param key 密钥，长度必须是8的倍数 
	* @return 返回解密后的原始数据 
	* @throws Exception 
	*/ 
	private static byte[] decrypt(byte[] src, byte[] key)throws Exception { 
		
		SecureRandom sr = new SecureRandom(); 
		DESKeySpec dks = new DESKeySpec(key); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES); 
		SecretKey securekey = keyFactory.generateSecret(dks); 
		Cipher cipher = Cipher.getInstance(DES); 
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr); 
		return cipher.doFinal(src); 
	} 
	
	
	/** 
	* 密码加密 
	* @param password 
	* @return 
	* @throws Exception 
	*/ 
	public final static String encrypt(String data, String key){ 		
		try {
			return byte2hex(encrypt(data.getBytes(),key.getBytes())); 
		}catch(Exception e) {			
		} 
		return null; 
	} 
	
	/** 
	* 密码解密 
	* @param data 
	* @return 
	* @throws Exception 
	*/ 
	public final static String decrypt(String data, String key){ 		
		try {			
			return new String(decrypt(hex2byte(data),key.getBytes())); 
		}catch(Exception e) { 			
		} 
		return null; 
	} 
	
	//字节码转换成16进制字符串
	private static String byte2hex(byte bytes[]){
	   StringBuffer retString = new StringBuffer();
	   for (int i = 0; i < bytes.length; ++i)
	   {	    
		   retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1).toUpperCase());
	   }
	   return retString.toString();
	}
	
	//将16进制字符串转换成字节码
	private static byte[] hex2byte(String hex) {
	   byte[] bts = new byte[hex.length() / 2];
	   for (int i = 0; i < bts.length; i++) {
	      bts[i] = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
	   }
	   return bts;
	}  
}
