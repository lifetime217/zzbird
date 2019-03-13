package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TWechatUser;

/**
 * @author lifetime
 *
 */
public interface ITWechatUserService extends IBaseService<TWechatUser> {
	boolean saveUserInfo(String openId);

	String sendSubscribeMsg(Map<String, String> params, Map<String, String> parseXml);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:  根据openId查询公众号用户
	 */
	TWechatUser queryGzhUserByOpenId(@Param("openId") String openId);

	/**
	 * 
	 * @Author wsl  
	 * @Description:   根据用户名查询公众号用户
	 */
	List<TWechatUser> queryGzhUserByNickName(@Param("nickName") String nickName);
}
