package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITPosterDao;
import com.luoran.zzbird.entity.biz.TPoster;
import com.luoran.zzbird.service.ITPosterService;


/**
 * @author lifetime
 *
 */
@Service
public class TPosterService extends AbstractBaseService<TPoster> implements ITPosterService{
	@Autowired
	private ITPosterDao dao;

	@Override
	public BaseDao<TPoster> getDao() {
		return dao;
	}
	
	@Override
	public String add(TPoster t) {
		return super.add(t);
	}

}