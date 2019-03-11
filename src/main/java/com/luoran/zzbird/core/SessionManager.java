package com.luoran.zzbird.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lifetime
 *
 */
public final class SessionManager {
	private static final ConcurrentHashMap<String, UserContextInfo> SessionCache = new ConcurrentHashMap<String, UserContextInfo>(50);
	
	/**
	 * @param number
	 * @param userInfo
	 */
	public static String put(String sessionKey,UserContextInfo userInfo){
		SessionCache.put(sessionKey, userInfo);
		return sessionKey;
	}
	
	public static boolean isValid(String sessionKey) {
		return SessionCache.containsKey(sessionKey);
	}
	
	
	public static void clear(String number){
		SessionCache.remove(number);
	}
	
	public static UserContextInfo get(String number){
		return SessionCache.get(number);
	}
}
