package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseUserDao extends BaseDao<TCompanyCourseUser> {

	public void queryPage(PageQuery<TCompanyCourseUser> pageQuery);

	public void queryComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	public List<TCompanyCourseUser> queryCourseByStuRoleId(Map<String, String> map);

	public void queryComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	public List<TCompanyCourseUser> queryCourseByTeaRoleId(Map<String, String> map);

	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	 */
	List<CourseUserVo> queryCourseUserByCourseId(@Param("courseId") String courseId, @Param("roleVal") Integer roleVal);
}
