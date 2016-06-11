package com.ding.util.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-11 下午7:32:57
 */
public class AES {
	public static final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
	public static final int HASH_ITERATIONS = 10000;
	public static final int KEY_LENGTH = 256;
	public static byte[] salt = { 1, 3, 9, 6, 9, 4, 4, 4, 0, 2, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };
	public static final String CIPHERMODEPADDING = "AES/CBC/PKCS5Padding";
	public static SecretKeyFactory keyfactory = null;
	public static IvParameterSpec ivSpec = new IvParameterSpec(new byte[] { 0xA, 1, 0xB, 5, 4, 0xF, 7, 9, 0x17, 3, 1, 6, 8, 0xC, 0xD, 91 });


	public synchronized static void createSecretKeyFactory() {
		if (keyfactory == null) {
			try {
				keyfactory = SecretKeyFactory.getInstance(KEY_GENERATION_ALG);
			} catch (NoSuchAlgorithmException e) {
				System.out.println("no key factory support for PBEWITHSHAANDTWOFISH-CBC");
			}
		}
	}

	public static SecretKeySpec aesKeyConvert(String key) {
		try {
			PBEKeySpec myKeyspec = new PBEKeySpec(key.toCharArray(), salt, HASH_ITERATIONS, KEY_LENGTH);
			if (keyfactory == null)
				createSecretKeyFactory();
			SecretKey sk = keyfactory.generateSecret(myKeyspec);
			byte[] skAsByteArray = sk.getEncoded();
			SecretKeySpec skforAES = new SecretKeySpec(skAsByteArray, "AES");
			return skforAES;
		} catch (InvalidKeySpecException ikse) {
			System.out.println("invalid key spec for PBEWITHSHAANDTWOFISH-CBC");
		}
		return null;
	}

	public static String encrypt(byte[] plaintext, String password) {
		SecretKeySpec skforAES = aesKeyConvert(password);
		byte[] ciphertext = encrypt(CIPHERMODEPADDING, skforAES, ivSpec, plaintext);
		String base64_ciphertext = Base64Encoder.encode(ciphertext);
		return base64_ciphertext;
	}

	public static String decrypt(String ciphertext_base64, String password) {
		byte[] s = Base64Decoder.decodeToBytes(ciphertext_base64);
		SecretKeySpec skforAES = aesKeyConvert(password);
		String decrypted = new String(decrypt(CIPHERMODEPADDING, skforAES, ivSpec, s));
		return decrypted;
	}

	public static String decrypt(byte[] data, String password) {
		SecretKeySpec skforAES = aesKeyConvert(password);
		String decrypted = new String(decrypt(CIPHERMODEPADDING, skforAES, ivSpec, data));
		return decrypted;
	}

	public static byte[] encrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] msg) {
		try {
			Cipher c = Cipher.getInstance(cmp);
			c.init(Cipher.ENCRYPT_MODE, sk, IV);
			return c.doFinal(msg);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		} catch (NoSuchPaddingException e) {
			System.out.println(e.getMessage());
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			System.out.println(e.getMessage());
		} catch (BadPaddingException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static byte[] decrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] ciphertext) {
		try {
			Cipher c = Cipher.getInstance(cmp);
			c.init(Cipher.DECRYPT_MODE, sk, IV);
			return c.doFinal(ciphertext);
		} catch (NoSuchAlgorithmException nsae) {
			System.out.println(nsae.getMessage());
		} catch (NoSuchPaddingException nspe) {
			System.out.println(nspe.getMessage());
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			System.out.println(e.getMessage());
		} catch (BadPaddingException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
