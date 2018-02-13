package com.aml.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密解密相关工具类
 * 
 * 需引入commons-codec jar包
 * 
 * @author zouwei
 * @since 2016年12月20日
 */
public class CodeUtils {

	public static final Logger logger = LoggerFactory.getLogger(CodeUtils.class);


	/**
	 * 生成用户登陆的token算法 (可能也有其他的方法引用到，不过都可以以 user: 开头) <br>
	 * 调用demo：generateToken()
	 * 
	 * @author Ding
	 * @param tokenContent
	 *            可以传来ip地址,或者设备序列号
	 * @since 2015-06-25
	 * @return 返回token字符串
	 */
	public static String generateToken(String tokenContent, String prefix) {
		// Random RANDOM = new Random();
		// 介个算法到时再优化
		// 拿现在的时间tuo / 算出其md5
		// md5(时间tuo + ip/机器序列号)
		Date nowD = new Date();
		long nowT = nowD.getTime();
		return prefix + DigestUtils.md5Hex(nowT + tokenContent); // bill:user
	}
	
	/**
	 * sha1加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeSha1(String str) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("sha1");
			digest.update(str.getBytes());
			byte[] msg = digest.digest();
			for (byte b : msg) {
				result.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("加密sha1算法发生错误", e);
		}
		return result.toString();
	}

	/**
	 * 生成短信验证码算法 <br>
	 * 调用demo：code4phone(4, 10)
	 * 
	 * @author Ding
	 * @since 2015-06-24
	 * @param bits
	 *            相当于循环几次取随机值
	 * @param to
	 *            每次在哪个整数范围内取随机值,此值有可能性是多位数
	 * @return 返回验证码
	 */
	public static String code4phone(int bits, int to) {
		Random RANDOM = new Random();
		StringBuffer randBuffer = new StringBuffer();
		for (int i = 1; i <= bits; i++) {
			randBuffer.append(RANDOM.nextInt(to));
		}
		return randBuffer.toString();
	}

	/**
	 * 后台密码加密
	 * 
	 * @param str
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static String Encrypt(String str, int pos1, int pos2) {
		int nLength;
		String strReturn = "";
		char nTemp;
		char cTemp;
		nLength = str.length();
		for (int i = 0; i <= nLength - 1; i++) {
			cTemp = str.charAt(i);
			if ((cTemp >= 48) && (cTemp <= 57)) {
				nTemp = (char) (cTemp - 48);
				nTemp = (char) ((nTemp + pos1) % 10);
				cTemp = (char) (nTemp + 48);
			} else if ((cTemp >= 65) && (cTemp <= 90)) {
				nTemp = (char) (cTemp - 65);
				nTemp = (char) ((nTemp + pos2) % 26);
				cTemp = (char) (nTemp + 97);
			} else if ((cTemp >= 97) && (cTemp <= 122)) {
				nTemp = (char) (cTemp - 97);
				nTemp = (char) ((nTemp + pos2) % 26);
				cTemp = (char) (nTemp + 65);
			}
			strReturn = strReturn + (char) ((int) (26 * Math.random()) + 65) + cTemp;
		}
		return strReturn;
	}

	/**
	 * 后台管理系统解密
	 * 
	 * @param str
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static String Decrypt(String str, int pos1, int pos2) {

		int nLength;
		String strReturn = "";
		char nTemp;
		char cTemp;
		nLength = str.length();
		for (int i = 0; i <= nLength - 2; i = i + 2) {
			cTemp = str.charAt(i + 1);
			if ((cTemp >= 48) && (cTemp <= 57)) {
				nTemp = (char) (cTemp - 48);
				nTemp = (char) ((nTemp + 10 - pos1) % 10);
				cTemp = (char) (nTemp + 48);
			} else if ((cTemp >= 65) && (cTemp <= 90)) {
				nTemp = (char) (cTemp - 65);
				nTemp = (char) ((nTemp + 26 - pos2) % 26);
				cTemp = (char) (nTemp + 97);
			} else if ((cTemp >= 97) && (cTemp <= 122)) {
				nTemp = (char) (cTemp - 97);
				nTemp = (char) ((nTemp + 26 - pos2) % 26);
				cTemp = (char) (nTemp + 65);
			}
			strReturn = strReturn + cTemp;
		}
		return strReturn;
	}

	/**
	 * base64加密
	 * 
	 * @return
	 */
	public static String encodeBase64(String src) {
		Base64 base64 = new Base64();
		try {
			return base64.encodeToString(src.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	/**
	 * base64解密
	 * 
	 * @param src
	 * @return
	 */
	public static String decodeBase64(String src) {
		Base64 base64 = new Base64();
		return new String(base64.decode(src));
	}

}
