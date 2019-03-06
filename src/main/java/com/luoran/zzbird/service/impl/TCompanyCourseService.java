package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.service.ITCompanyCourseService;


/**
 * @author wsl
 *
 */
@Service
public class TCompanyCourseService extends AbstractBaseService<TCompanyCourse> implements ITCompanyCourseService{
	@Autowired
	private ITCompanyCourseDao courseDao;

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

}