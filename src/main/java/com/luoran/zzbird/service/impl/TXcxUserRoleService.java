package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITXcxUserRoleDao;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserRoleService;


/**
 * @author lifetime
 *
 */
@Service
public class TXcxUserRoleService extends AbstractBaseService<TXcxUserRole> implements ITXcxUserRoleService{
	@Autowired
	private ITXcxUserRoleDao dao;

	@Override
	public BaseDao<TXcxUserRole> getDao() {
		return dao;
	}
	
	@Override
	public String add(TXcxUserRole t) {
		return super.add(t);
	}

}