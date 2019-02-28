package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITIndustryDao;
import com.luoran.zzbird.entity.biz.TIndustry;
import com.luoran.zzbird.service.ITIndustryService;


/**
 * @author lifetime
 *
 */
@Service
public class TIndustryService extends AbstractBaseService<TIndustry> implements ITIndustryService{
	@Autowired
	private ITIndustryDao dao;

	@Override
	public BaseDao<TIndustry> getDao() {
		return dao;
	}
	
	@Override
	public String add(TIndustry t) {
		return super.add(t);
	}

}