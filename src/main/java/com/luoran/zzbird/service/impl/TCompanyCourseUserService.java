package com.luoran.zzbird.service.impl;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseUserDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;
import com.luoran.zzbird.service.ITCompanyCourseUserService;

/**
 * @author tzx
 *
 */
@Service
public class TCompanyCourseUserService extends AbstractBaseService<TCompanyCourseUser>
		implements ITCompanyCourseUserService {
	@Autowired
	private ITCompanyCourseUserDao companyCourseUserDao;


	@Override
	public BaseDao<TCompanyCourseUser> getDao() {
		return companyCourseUserDao;
	}

	@Override
	public String add(TCompanyCourseUser t) {
		return super.add(t);
	}

	@Override
	public PageQuery<TCompanyCourseUser> getComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryComUserByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByUserRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = companyCourseUserDao.queryCourseByUserRoleId(map);
		return list;
	}

	@Override
	public List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal) {
		return companyCourseUserDao.queryCourseUserByCourseId(courseId, roleVal);
	}

	@Override
	public List<TCompanyCourse> queryCourseList() {
		UserContextInfo user = UserContext.get();
		List<TCompanyCourse> courseList = companyCourseUserDao.queryCourseByUserList(user.getOpenid(), user.getCompanyId(), user.getRoleVal());
		return courseList;
	}

	@Override
	public Integer getUserCourseCount(Map<String, String> params) {
		Integer userCourseCount = companyCourseUserDao.queryUserCourseCount(params);
		return userCourseCount;
	}

	@Override
	public Integer getTeaCourseStuCount(Map<String, String> params) {
		Integer queryTeaCourseStuCount = companyCourseUserDao.queryTeaCourseStuCount(params);
		return queryTeaCourseStuCount;
	}

	@Override
	public PageQuery<TCompanyCourseUser> getTeaCourseStu(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryTeaCourseStu(pageQuery);
		return pageQuery;
	}


}