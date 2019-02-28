package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITMessageDao;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITMessageService;


/**
 * @author lifetime
 *
 */
@Service
public class TMessageService extends AbstractBaseService<TMessage> implements ITMessageService{
	@Autowired
	private ITMessageDao dao;

	@Override
	public BaseDao<TMessage> getDao() {
		return dao;
	}
	
	@Override
	public String add(TMessage t) {
		return super.add(t);
	}

}