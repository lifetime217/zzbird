package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TPoster;

/**
 * @author lifetime
 *
 */
public interface ITPosterDao extends BaseDao<TPoster> {

	public void queryPage(PageQuery<TPoster> pageQuery);

}
