package com.ding.util.aes;
/**
 * 测试类
 * 输出:
 * 原:yuanwen
加密后:pF5lucWGXeeFwmendXQyVA==
解密后:yuanwen

 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-11 下午7:51:47
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-11 下午7:48:12
	 * @param args
	 */

 

	public static void main(String[] args) {
		// 加密原文
		String plaintext = "yuanwen";
		// 密钥
		String key = "123456";
		//密文
		String ciphertext;
		try {
			ciphertext = AES.encrypt(plaintext.getBytes("UTF-8"), key);
			String de = AES.decrypt(ciphertext, key);
			System.out.println("原:" + plaintext);
			System.out.println("加密后:" + ciphertext);
			System.out.println("解密后:" + de);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
