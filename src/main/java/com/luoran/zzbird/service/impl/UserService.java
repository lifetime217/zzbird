package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.IUserDao;
import com.luoran.zzbird.entity.biz.User;
import com.luoran.zzbird.service.IUserService;


/**
 * @author lifetime
 *
 */
@Service
public class UserService extends AbstractBaseService<User> implements IUserService{
	@Autowired
	private IUserDao dao;

	@Override
	public BaseDao<User> getDao() {
		return dao;
	}
	
	@Override
	public String add(User t) {
		return super.add(t);
	}

}