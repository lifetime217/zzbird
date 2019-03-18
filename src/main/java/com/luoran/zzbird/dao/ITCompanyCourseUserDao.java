package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;

/**
 * @author tzx
 *
 */
public interface ITCompanyCourseUserDao extends BaseDao<TCompanyCourseUser> {

	public void queryPage(PageQuery<TCompanyCourseUser> pageQuery);

	/**
	 * 
	 * @Author tzx
	 * @Description: 根据Boos的角色id查询企业下的用户并且分页
	 */
	public void queryComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	/**
	 * 
	 * @Author tzx
	 * @Description: 根据用户的角色id查询所有课程
	 */
	public List<TCompanyCourseUser> queryCourseByUserRoleId(Map<String, String> map);

	/**
	 * 
	 * @Author wsl
	 * @Description: 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	 */
	List<CourseUserVo> queryCourseUserByCourseId(@Param("courseId") String courseId, @Param("roleVal") Integer roleVal);

	/**
	 * 
	 * @Author wsl
	 * @Description: 查询用户所属的公司对应角色的课程
	 */
	List<TCompanyCourse> queryCourseByUserList(@Param("openId") String openId, @Param("companyId") String companyId,
			@Param("roleVal") Integer roleVal);

	/**
	 * 
	 * @Author tzx
	 * @Description: 查询用户下的课程总数，仅限老师和学生
	 * @param #roleId:老师或学生的角色ID
	 */
	public Integer queryUserCourseCount(Map<String, String> params);

	/**
	 * 
	 * @Author tzx
	 * @Description: 查询老师的学生人数
	 * @param #roleId:老师的角色ID
	 */
	public Integer queryTeaCourseStuCount(Map<String, String> params);

	/**
	 * 
	 * @Author tzx
	 * @Description: 查询老师的学生，page分页查询
	 * @param #roleId:老师的角色ID
	 */
	public void queryTeaCourseStu(PageQuery<TCompanyCourseUser> pageQuery);

}
