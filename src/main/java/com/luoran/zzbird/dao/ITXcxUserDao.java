package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUser;

/**
 * @author lifetime
 *
 */
public interface ITXcxUserDao extends BaseDao<TXcxUser> {

	public void queryPage(PageQuery<TXcxUser> pageQuery);

}
