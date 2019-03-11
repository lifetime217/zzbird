package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseListVo;
import com.luoran.zzbird.entity.vo.CourseUserVo;

/**
 * @author tzx
 *
 */
public interface ITCompanyCourseUserService extends IBaseService<TCompanyCourseUser> {
	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据Boos的角色id查询企业下的用户并且分页
	 */
	PageQuery<TCompanyCourseUser> getComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据用户的角色id查询所有课程
	 */
	List<TCompanyCourseUser> getCourseByUserRoleId(Map<String, String> map);

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	 */
	List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal);

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 查询用户所属的公司对应角色的课程
	 */
	List<CourseListVo> queryCourseList(String openId, String companyId, Integer roleVal);

}
