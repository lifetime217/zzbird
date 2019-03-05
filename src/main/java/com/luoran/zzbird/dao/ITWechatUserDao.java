package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TWechatUser;

/**
 * @author lifetime
 *
 */
public interface ITWechatUserDao extends BaseDao<TWechatUser> {

	public void queryPage(PageQuery<TWechatUser> pageQuery);

	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 根据openId查询公众号用户
	 */
	TWechatUser queryGzhUserByOpenId(@Param("openId") String openId);

	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO  根据用户名查询公众号用户
	 */
	List<TWechatUser> queryGzhUserByNickName(@Param("nickName") String nickName);
}
