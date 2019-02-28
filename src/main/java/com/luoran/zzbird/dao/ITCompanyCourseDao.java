package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseDao extends BaseDao<TCompanyCourse> {

	public void queryPage(PageQuery<TCompanyCourse> pageQuery);

}
