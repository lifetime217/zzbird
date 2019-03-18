package com.luoran.zzbird.dao;

import java.util.Map;

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
	 * @Description:修改课程人数加一
	 */
	Integer updatePersonNumber(String id);

	/**
	 * 
	 * @Author wsl
	 * @Description:  根据课程id查询课程的详情
	 */
	TCompanyCourse queryCourseDetail(@Param(value = "courseId") String courseId);
	/**
	 * 
	 * @Author tzx
	 * @Description:查询公司下课程总数
	 * @param #companyId: 公司Id
	 */
	public Integer queryCourseCount(Map<String, String> params);

}
