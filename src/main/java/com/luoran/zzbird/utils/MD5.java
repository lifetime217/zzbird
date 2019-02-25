package com.luoran.zzbird.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author lifetime
 *
 */
public class MD5 {

	public static String get(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			throw new RuntimeException("MD5加密出现错误");
		}
	}
	
}
