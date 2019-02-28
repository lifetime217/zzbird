package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TWechatUser;

/**
 * @author lifetime
 *
 */
public interface ITWechatUserDao extends BaseDao<TWechatUser> {

	public void queryPage(PageQuery<TWechatUser> pageQuery);

}
