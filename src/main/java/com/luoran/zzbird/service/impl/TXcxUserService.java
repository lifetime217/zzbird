package com.luoran.zzbird.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITXcxUserDao;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.service.ITXcxUserService;


/**
 * @author wsl
 *
 */
@Service
public class TXcxUserService extends AbstractBaseService<TXcxUser> implements ITXcxUserService{
	@Autowired
	private ITXcxUserDao userDao;

	@Override
	public BaseDao<TXcxUser> getDao() {
		return userDao;
	}
	
	@Override
	public String add(TXcxUser t) {
		return super.add(t);
	}

	@Override
	public TXcxUser queryXcxUserByOpenId(String openId) {
		return userDao.queryXcxUserByOpenId(openId);
	}

	@Override
	public List<TXcxUser> queryXcxUserByNickName(String nickName) {
		return userDao.queryXcxUserByNickName(nickName);
	}


}