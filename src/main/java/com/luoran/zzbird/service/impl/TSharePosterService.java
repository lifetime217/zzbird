package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITSharePosterDao;
import com.luoran.zzbird.entity.biz.TSharePoster;
import com.luoran.zzbird.service.ITSharePosterService;


/**
 * @author lifetime
 *
 */
@Service
public class TSharePosterService extends AbstractBaseService<TSharePoster> implements ITSharePosterService{
	@Autowired
	private ITSharePosterDao dao;

	@Override
	public BaseDao<TSharePoster> getDao() {
		return dao;
	}
	
	@Override
	public String add(TSharePoster t) {
		return super.add(t);
	}

}