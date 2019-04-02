package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.Map;

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
import com.luoran.zzbird.service.ITImgDeleteService;

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
	
	@Autowired
	private ITImgDeleteService imgService;

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
	public String addCourse(TCompanyCourse course,String deleteImg) {
		UserContextInfo user = UserContext.get();
		course.setCompanyId(user.getCompanyId());
		course.setPersonNumber(0);
		String courseId = add(course);

		TCompanyCourseUser tCompanyCourseUser = new TCompanyCourseUser();
		tCompanyCourseUser.setCompanyCourseId(courseId);
		tCompanyCourseUser.setAddTime(new Date());
		tCompanyCourseUser.setXcxUserRoleId(user.getXcxUserRoleId().toString());
		companyCourseUserService.add(tCompanyCourseUser);
		
		imgService.addImg(deleteImg);
		
		return courseId;
	}

	@Override
	public Integer getCourseCount(Map<String, String> params) {
		Integer courseCount = courseDao.queryCourseCount(params);
		return courseCount;
	}

	@Override
	public boolean updatePerson(String id) {
		return courseDao.updatePersonNumber(id) != 0;
	}

	@Override
	@Transactional
	public void updateCourse(TCompanyCourse course,String imgs) {
		UserContextInfo user = UserContext.get();
		course.setCompanyId(user.getCompanyId());
		save(course);
		
		imgService.addImg(imgs);
	}

}