package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompany;

/**
 * @author lifetime
 *
 */
public interface ITCompanyDao extends BaseDao<TCompany> {

	public void queryPage(PageQuery<TCompany> pageQuery);

}
