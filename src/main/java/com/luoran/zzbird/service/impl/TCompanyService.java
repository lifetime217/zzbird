package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyDao;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.service.ITCompanyService;


/**
 * @author lifetime
 *
 */
@Service
public class TCompanyService extends AbstractBaseService<TCompany> implements ITCompanyService{
	@Autowired
	private ITCompanyDao dao;

	@Override
	public BaseDao<TCompany> getDao() {
		return dao;
	}
	
	@Override
	public String add(TCompany t) {
		return super.add(t);
	}

}