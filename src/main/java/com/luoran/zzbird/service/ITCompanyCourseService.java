package com.luoran.zzbird.service;

import java.util.Map;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourse;

/**
 * @author wsl
 *
 */
public interface ITCompanyCourseService extends IBaseService<TCompanyCourse> {

	/**
	 * 
	 * @Author wsl
	 * @Description:  根据课程id查询课程的详情
	 */
	TCompanyCourse queryCourseDetail(String courseId);
	
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:修改课程人数加一
	 */
	boolean updatePerson(String id);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:添加课程
	 */
	String addCourse(TCompanyCourse course);
	/**
	 * 
	 * @Author tzx
	 * @Description:查询公司下课程总数
	 * @param #companyId: 公司Id
	 */
	Integer getCourseCount(Map<String, String> params);
}
