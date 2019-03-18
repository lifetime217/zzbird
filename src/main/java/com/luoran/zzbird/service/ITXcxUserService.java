package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserService extends IBaseService<TXcxUser> {

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据openId查询小程序用户
	 */
	TXcxUser queryXcxUserByOpenId(String openId);

	/**
	 * 
	 * @Author wsl
	 * @Description: 添加用户
	 */
	String addUser(TXcxUser xcxUser, String zzbird_XcxSessionKey);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据用户名查询小程序用户
	 */
	List<TXcxUser> queryXcxUserByNickName(String nickName);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据sessionKey查询用户所有的角色
	 */
	List<TXcxUserRole> queryAllRoleUser(String sessionKey);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据sessionKey和currentActive查询正在使用的角色
	 */
	TXcxUserRole queryActiveUserRole(String sessionKey);

	/**
	 * 用于用户首次在登录时初始化session
	 * 
	 * @Author wsl
	 * @Description:
	 */
	void initSession(String sessionKey, String openId);

	/**
	 * 再次初始化session（本次是基于用户信息已授权且信息已入库）<br>
	 * <b>如果session存在，则本次操作为空操作。不存在session则会进行session重新构造</b>
	 * 
	 * @Author wsl
	 * @Description:
	 */
	void reinitSession(String sessionKey);

	/**
	 * 
	 * @Author wsl
	 * @Description: 新建公司、邀请和切换角色刷新session
	 */
	void reloadSession(String sessionKey);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据sessionKey、roleVal和companyId查询用户角色id
	 */
	String queryXcxUserRole(String sessionKey, String roleVal, String companyId);
}
