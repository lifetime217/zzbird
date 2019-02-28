package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TIndustry;

/**
 * @author lifetime
 *
 */
public interface ITIndustryDao extends BaseDao<TIndustry> {

	public void queryPage(PageQuery<TIndustry> pageQuery);

}
