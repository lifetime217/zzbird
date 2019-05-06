package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;
import com.luoran.zzbird.entity.vo.InviteVo;

/**
 * @author tzx
 *
 */
public interface ITCompanyCourseUserService extends IBaseService<TCompanyCourseUser> {
	/**
	 * 
	 * @Author tzx
	 * @Description: 根据Boos的角色id查询企业下的用户并且分页
	 */
	PageQuery<TCompanyCourseUser> getComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	
	/**
	 * 
	 * @Author tzx
	 * @Description: 根据用户的角色id查询所有课程
	 */
	List<TCompanyCourseUser> getCourseByUserRoleId(Map<String, String> map);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	 */
	List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal);

	/**
	 * 
	 * @Author wsl
	 * @Description: 查询用户所属的公司对应角色的课程
	 */
	PageQuery<TCompanyCourse> queryCourseList(String page);

	/**
	 * 
	 * @Author tzx
	 * @Description: 查询用户下的课程总数，仅限老师和学生
	 * @param #roleId:老师或学生的角色ID
	 */
	Integer getUserCourseCount(Map<String, String> params);

	/**
	 * 
	 * @Author tzx
	 * @Description: 查询老师的学生人数
	 * @param #roleId:老师的角色ID
	 */
	Integer getTeaCourseStuCount(Map<String, String> params);
	/**
	 * 
	 * @Author tzx
	 * @Description: 查询老师的学生，page分页查询
	 * @param #roleId:老师的角色ID
	 */
	PageQuery<TCompanyCourseUser> getTeaCourseStu(PageQuery<TCompanyCourseUser> pageQuery);


	/**
	 * 
	 * @Author wsl  
	 * @Description:用户受邀请进来添加数据
	 */
	void addCourseUser(InviteVo inviteVo,String zzbird_XcxSessionKey);

	/**
	 * 
	 * @Author wsl  
	 * @Description:  删除课程  true代表删除成功     false代表 课程下有学生或者老师 不能删除
	 */
	boolean deleteCourse(String courseId);
	
}
