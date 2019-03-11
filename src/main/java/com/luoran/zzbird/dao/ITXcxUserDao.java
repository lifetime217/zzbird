package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.vo.UserRoleVo;

/**
 * @author wsl
 *
 */
public interface ITXcxUserDao extends BaseDao<TXcxUser> {

	public void queryPage(PageQuery<TXcxUser> pageQuery);

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
	List<UserRoleVo> queryNewOrOldUser(@Param("sessionKey") String sessionKey);
}
