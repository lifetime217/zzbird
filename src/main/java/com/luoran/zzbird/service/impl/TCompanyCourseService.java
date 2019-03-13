package com.luoran.zzbird.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.service.ITCompanyCourseService;
import com.luoran.zzbird.service.ITCompanyCourseUserService;

/**
 * @author wsl
 *
 */
@Service
public class TCompanyCourseService extends AbstractBaseService<TCompanyCourse> implements ITCompanyCourseService {
	@Autowired
	private ITCompanyCourseDao courseDao;
	@Autowired
	private ITCompanyCourseUserService companyCourseUserService;
	
	

	@Override
	public BaseDao<TCompanyCourse> getDao() {
		return courseDao;
	}

	@Override
	public String add(TCompanyCourse t) {
		return super.add(t);
	}

	@Override
	public TCompanyCourse queryCourseDetail(String courseId) {
		return courseDao.queryCourseDetail(courseId);
	}

	@Override
	@Transactional
	public String addCourse(TCompanyCourse course) {
		UserContextInfo user = UserContext.get();
		course.setCompanyId(user.getCompanyId());
		course.setPersonNumber(0);
		String courseId = add(course);
		
		TCompanyCourseUser tCompanyCourseUser = new TCompanyCourseUser();
		tCompanyCourseUser.setCompanyCourseId(courseId);
		tCompanyCourseUser.setAddTime(new Date());
		tCompanyCourseUser.setXcxUserRoleId(user.getXcxUserRoleId().toString());
		companyCourseUserService.add(tCompanyCourseUser);
		return courseId;
	}

}