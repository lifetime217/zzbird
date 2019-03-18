package com.luoran.zzbird.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITSharePosterDao;
import com.luoran.zzbird.entity.biz.TSharePoster;
import com.luoran.zzbird.service.ITSharePosterService;

/**
 * @author wsl
 *
 */
@Service
public class TSharePosterService extends AbstractBaseService<TSharePoster> implements ITSharePosterService {
	@Autowired
	private ITSharePosterDao sahreDao;

	@Override
	public BaseDao<TSharePoster> getDao() {
		return sahreDao;
	}

	@Override
	public String add(TSharePoster t) {
		return super.add(t);
	}

	@Override
	public List<TSharePoster> querySharePoster(String posterId) {

		return sahreDao.querySharePoster(posterId);
	}

}