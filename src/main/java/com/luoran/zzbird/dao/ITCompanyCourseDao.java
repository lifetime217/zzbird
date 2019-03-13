package com.luoran.zzbird.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseDao extends BaseDao<TCompanyCourse> {

	public void queryPage(PageQuery<TCompanyCourse> pageQuery);

	/**
	 * 
	 * @Author wsl
	 * @Description:  根据课程id查询课程的详情
	 */
	TCompanyCourse queryCourseDetail(@Param(value = "courseId") String courseId);

}
