package com.luoran.zzbird.service.impl;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseUserDao;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.vo.CourseUserVo;
import com.luoran.zzbird.service.ITCompanyCourseUserService;


/**
 * @author lifetime
 *
 */
@Service
public class TCompanyCourseUserService extends AbstractBaseService<TCompanyCourseUser> implements ITCompanyCourseUserService{
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
	public PageQuery<TCompanyCourseUser> getComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryComStudentByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByStuRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = companyCourseUserDao.queryCourseByStuRoleId(map);
		return list;
	}
	
	
	@Override
	public PageQuery<TCompanyCourseUser> getComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryComTeacherByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByTeaRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = companyCourseUserDao.queryCourseByTeaRoleId(map);
		return list;
	}

	@Override
	public List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal) {
		return companyCourseUserDao.queryCourseUserByCourseId(courseId, roleVal);
	}

}