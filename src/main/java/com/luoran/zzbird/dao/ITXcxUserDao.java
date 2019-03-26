package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserDao extends BaseDao<TXcxUser> {

	public void queryPage(PageQuery<TXcxUser> pageQuery);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据openId查询小程序用户
	 */
	TXcxUser queryXcxUserByOpenId(String openId);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据用户名查询小程序用户
	 */
	List<TXcxUser> queryXcxUserByNickName(String nickName);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据sessionKey查询用户的所有角色
	 */
	List<TXcxUserRole> queryAllRoleUser(@Param("sessionKey") String sessionKey);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据sessionKey查询正在使用的角色
	 */
	TXcxUserRole queryAllRoleUser(@Param("sessionKey") String sessionKey,
			@Param("currentActive") Integer currentActive);

	/**
	 * 
	 * @Author wsl  
	 * @Description: 根据sessionKey、roleVal和companyId查询用户角色id
	 */
	TXcxUserRole  queryXcxUserRole(String sessionKey,String roleVal,String companyId);
	/**
	 * 
	 * @Author tzx  
	 * @Description: 根据小程序用户的Id 更新xcxOpenId字段
	 */
	public void updateByUserId(String id, String xcxOpenId);
	
	/**
	 * 
	 * @Author tzx  
	 * @Description: 根据小程序角色id 查询小程序用户的公众号openId
	 */
	public String queryUserByRoleId(String id);
	

}
