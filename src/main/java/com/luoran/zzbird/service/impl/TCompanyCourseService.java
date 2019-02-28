package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.service.ITCompanyCourseService;


/**
 * @author lifetime
 *
 */
@Service
public class TCompanyCourseService extends AbstractBaseService<TCompanyCourse> implements ITCompanyCourseService{
	@Autowired
	private ITCompanyCourseDao dao;

	@Override
	public BaseDao<TCompanyCourse> getDao() {
		return dao;
	}
	
	@Override
	public String add(TCompanyCourse t) {
		return super.add(t);
	}

}