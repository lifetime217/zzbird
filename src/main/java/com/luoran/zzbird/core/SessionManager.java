package com.luoran.zzbird.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lifetime
 *
 */
public final class SessionManager {
	private static final ConcurrentHashMap<String, UserContextInfo> SessionCache = new ConcurrentHashMap<String, UserContextInfo>(
			50);

	/**
	 * @param number
	 * @param userInfo
	 */
	public static String put(String sessionKey, UserContextInfo userInfo) {
		SessionCache.put(sessionKey, userInfo);
		return sessionKey;
	}

	public static boolean isValid(String sessionKey) {
		return SessionCache.containsKey(sessionKey);
	}

	public static void clear(String number) {
		SessionCache.remove(number);
	}

	public static UserContextInfo get(String number) {
		return SessionCache.get(number);
	}


	/**
	 * 
	 * @Author wsl
	 * @Description:   根据用户的sessionkey来缓存用户openid
	 */
	public static void init(String sessionKey, String opid) {
		UserContextInfo info = new UserContextInfo();
		info.setOpenid(opid);
		SessionManager.put(sessionKey, info);
	}
	

	/**
	 * 
	 * @Author wsl
	 * @Description:   根据用户的sessionkey来缓存用户openid
	 */
	public static void init(String sessionKey, UserContextInfo info) {
		SessionManager.put(sessionKey, info);
	}
}
