package com.luoran.zzbird.utils;

import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContextInfo;

public class SessionManagerUtil {

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据用户的sessionkey来缓存用户登录的信息
	 */
	public static void putSessionManager(String sessionKey, String opid, String companyId, Integer roleVal,
			Integer XcxUserRoleId, String companyName, String roleName, String headImg) {
		UserContextInfo info = new UserContextInfo();
		info.setCompanyId(companyId);
		info.setOpenid(opid);
		info.setRoleVal(roleVal);
		info.setXcxUserRoleId(XcxUserRoleId);
		info.setCompanyName(companyName);
		info.setRoleName(roleName);
		info.setHeadImg(headImg);
		info.setSessionKey(sessionKey);
		SessionManager.put(sessionKey, info);
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据用户的sessionkey来缓存用户登录的信息
	 */
	public static void putSessionManager(String sessionKey, String companyId, Integer roleVal, Integer XcxUserRoleId,
			String companyName, String roleName, String headImg) {
		UserContextInfo info = new UserContextInfo();
		info.setCompanyId(companyId);
		info.setRoleVal(roleVal);
		info.setXcxUserRoleId(XcxUserRoleId);
		info.setCompanyName(companyName);
		info.setRoleName(roleName);
		info.setHeadImg(headImg);
		info.setSessionKey(sessionKey);
		SessionManager.put(sessionKey, info);
	}
	

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据用户的sessionkey来缓存用户openid
	 */
	public static void putSessionManager(String sessionKey, String opid) {
		UserContextInfo info = new UserContextInfo();
		info.setOpenid(opid);
		info.setSessionKey(sessionKey);
		SessionManager.put(sessionKey, info);
	}

}
