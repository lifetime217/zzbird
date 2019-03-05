package com.luoran.zzbird.service;

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
