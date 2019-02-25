package com.luoran.zzbird.utils;

/**
 * @author lifetime
 *
 */
public final class UUID {
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * 产生uuid
	 * 
	 * @return
	 */
	public static String get() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 产生短字符串，测试再千万级别时基本不重复，如果是重要数据结构且需要短字符，请数据库做一次唯一键的约束
	 * 
	 * @return
	 */
	public static String shortString() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
}
