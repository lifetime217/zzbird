package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author lifetime
 *
 */
public interface ITXcxUserRoleDao extends BaseDao<TXcxUserRole> {

	public void queryPage(PageQuery<TXcxUserRole> pageQuery);

}
