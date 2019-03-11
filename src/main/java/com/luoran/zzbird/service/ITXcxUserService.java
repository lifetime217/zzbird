package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.vo.UserRoleVo;

/**
 * @author wsl
 *
 */
public interface ITXcxUserService extends IBaseService<TXcxUser>{
	
	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 根据openId查询小程序用户
	 */
	TXcxUser queryXcxUserByOpenId(String openId);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 根据用户名查询小程序用户
	 */
	List<TXcxUser> queryXcxUserByNickName(String nickName);
	
	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据sessionkey查询用户是否是新老用户
	 */
	List<UserRoleVo> queryNewOrOldUser(String sessionKey);
}
