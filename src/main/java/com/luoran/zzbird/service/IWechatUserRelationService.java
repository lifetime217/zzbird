package com.luoran.zzbird.service;

/**
 * <b>在新增微信用户 或者 新增小程序用户时 ，需要调用对应方法即可</b><br>
 * 主要用于微信用于与小程序用户关联匹配的工作
 * 
 * @author lifetime
 *
 */
public interface IWechatUserRelationService {
	/**
	 * 有新的微信用户加入，通知进行关联服务
	 * 
	 * @param wxOpenId
	 */
	public void notifyAddWxUser(String wxOpenId);

	/**
	 * 有新的小程序用户加入，通知进行关联服务
	 * 
	 * @param xcxOpenId
	 */
	public void notifyAddXcxUser(String xcxOpenId);
}
