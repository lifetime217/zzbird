package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseUserService extends IBaseService<TCompanyCourseUser> {

	PageQuery<TCompanyCourseUser> getComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	List<TCompanyCourseUser> getCourseByStuRoleId(Map<String, String> map);

	PageQuery<TCompanyCourseUser> getComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	List<TCompanyCourseUser> getCourseByTeaRoleId(Map<String, String> map);

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	 */
	List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal);

}
