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
import com.luoran.zzbird.service.ITCompanyCourseUserService;


/**
 * @author lifetime
 *
 */
@Service
public class TCompanyCourseUserService extends AbstractBaseService<TCompanyCourseUser> implements ITCompanyCourseUserService{
	@Autowired
	private ITCompanyCourseUserDao dao;

	@Override
	public BaseDao<TCompanyCourseUser> getDao() {
		return dao;
	}
	
	@Override
	public String add(TCompanyCourseUser t) {
		return super.add(t);
	}

	@Override
	public PageQuery<TCompanyCourseUser> getComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		dao.queryComStudentByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByStuRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = dao.queryCourseByStuRoleId(map);
		return list;
	}
	
	
	@Override
	public PageQuery<TCompanyCourseUser> getComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		dao.queryComTeacherByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByTeaRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = dao.queryCourseByTeaRoleId(map);
		return list;
	}

}