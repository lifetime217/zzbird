package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITXcxUserDao;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.service.ITXcxUserService;


/**
 * @author lifetime
 *
 */
@Service
public class TXcxUserService extends AbstractBaseService<TXcxUser> implements ITXcxUserService{
	@Autowired
	private ITXcxUserDao dao;

	@Override
	public BaseDao<TXcxUser> getDao() {
		return dao;
	}
	
	@Override
	public String add(TXcxUser t) {
		return super.add(t);
	}

}