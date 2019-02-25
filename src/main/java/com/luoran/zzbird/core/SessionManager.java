package com.luoran.zzbird.core;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

import com.luoran.zzbird.entity.UserInfo;
import com.luoran.zzbird.utils.MD5;

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
	public static String put(String number,UserInfo userInfo){
		UserContextInfo info = new UserContextInfo();
		info.setId(userInfo.getString("id"));
		info.setLoginTime(System.currentTimeMillis());
		info.setNumber(userInfo.getLoginNo());
		info.setPwdMd5(userInfo.getLoginPwd());
		info.setCompanyId(userInfo.getCompanyId());
		info.setCompanyCode(userInfo.getCompanyCode());
		info.setCompanyName(userInfo.getCompanyName());
		info.setUserType(userInfo.getUserType());
		info.setAlias(userInfo.getNick());
		info.setAccessToken(MD5.get(info.getNumber() + info.getLoginTime() + info.getPwdMd5()));
		SessionCache.put(number, info);
		return info.getAccessToken();
	}
	
	/**
	 * @param number
	 * @param accessToken
	 * @return
	 */
	public static boolean isValid(String number,String accessToken){
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(accessToken)){
			return false;
		}
		UserContextInfo info = get(number);
		if(info != null){
			return accessToken.equals(info.getAccessToken());
		}
		return false;
	}
	
	public static void clear(String number){
		SessionCache.remove(number);
	}
	
	public static UserContextInfo get(String number){
		return SessionCache.get(number);
	}
}
