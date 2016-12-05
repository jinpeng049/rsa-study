package com.easy.study.rsa;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author shy
 * @date 2012-1-18 下午04:05:24
 */
public class Md5Encrypt {
	
	private static final Logger logger = Logger.getLogger(Md5Encrypt.class);
	/** 用来将字节转换成 16 进制表示的字符 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * MD5加密
	 * @param text
	 * @return
	 */
	public static String md5Enc (String text) {
		MessageDigest msgDigest = null;
	    try {
	    	msgDigest  = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("NoSuchAlgorithmException@md5Enc", e);
	    }
	    try {
	    	 //TODO 如果接口编码格式有变，此处应指定
			msgDigest.update(text.getBytes("UTF-8"));
 
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException@md5Enc", e);
		}
	        byte[] tmp = msgDigest.digest();
	        // MD5 的计算结果是一个 128 位的长整数，
	        int l = tmp.length;
			char[] out = new char[l << 1];
			// two characters form the hex value.
			for (int i = 0, j = 0; i < l; i++) {
				out[j++] = DIGITS[(0xF0 & tmp[i]) >>> 4];
				out[j++] = DIGITS[0x0F & tmp[i]];
			}
	    return new String(out);
	}
	
	/**
	 * MD5加密
	 * @param text
	 * @return
	 */
	public static String md5Enc (byte[] text) {
		MessageDigest msgDigest = null;
	    try {
	    	msgDigest  = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("NoSuchAlgorithmException@md5Enc", e);
	    }
	    //TODO 如果接口编码格式有变，此处应指定
		msgDigest.update(text);
	        byte[] tmp = msgDigest.digest();
	        // MD5 的计算结果是一个 128 位的长整数，
	        int l = tmp.length;
			char[] out = new char[l << 1];
			// two characters form the hex value.
			for (int i = 0, j = 0; i < l; i++) {
				out[j++] = DIGITS[(0xF0 & tmp[i]) >>> 4];
				out[j++] = DIGITS[0x0F & tmp[i]];
			}
	    return new String(out);
	}
	
	/**
	 * 按照指定的算法进行MD5运算
	 * @param text
	 * @return
	 */
	public static String encWithDefAlgthm(String text, String algorithm) {
		MessageDigest msgDigest = null;
	    try {
	    	msgDigest  = MessageDigest.getInstance(algorithm);
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("NoSuchAlgorithmException@md5Enc", e);
	    }
	    try {
	    	 //TODO 如果接口编码格式有变，此处应指定
			msgDigest.update(text.getBytes("UTF-8"));
 
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException@md5Enc", e);
		}
	        byte[] tmp = msgDigest.digest();
	        // MD5 的计算结果是一个 128 位的长整数，
	        int l = tmp.length;
			char[] out = new char[l << 1];
			// two characters form the hex value.
			for (int i = 0, j = 0; i < l; i++) {
				out[j++] = DIGITS[(0xF0 & tmp[i]) >>> 4];
				out[j++] = DIGITS[0x0F & tmp[i]];
			}
	    return new String(out);
	}
}
