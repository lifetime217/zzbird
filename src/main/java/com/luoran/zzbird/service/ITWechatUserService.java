package com.luoran.zzbird.service;

import java.util.Map;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TWechatUser;

/**
 * @author lifetime
 *
 */
public interface ITWechatUserService extends IBaseService<TWechatUser> {
	boolean saveUserInfo(String openId);

	String sendSubscribeMsg(Map<String, String> params, Map<String, String> parseXml);
}
