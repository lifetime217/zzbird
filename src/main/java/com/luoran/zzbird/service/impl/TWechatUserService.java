package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITWechatUserDao;
import com.luoran.zzbird.entity.biz.TWechatUser;
import com.luoran.zzbird.service.ITWechatUserService;


/**
 * @author lifetime
 *
 */
@Service
public class TWechatUserService extends AbstractBaseService<TWechatUser> implements ITWechatUserService{
	@Autowired
	private ITWechatUserDao dao;

	@Override
	public BaseDao<TWechatUser> getDao() {
		return dao;
	}
	
	@Override
	public String add(TWechatUser t) {
		return super.add(t);
	}

}