package com.luoran.zzbird.service.impl;

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

}