package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TSharePoster;

/**
 * @author lifetime
 *
 */
public interface ITSharePosterDao extends BaseDao<TSharePoster> {

	public void queryPage(PageQuery<TSharePoster> pageQuery);

}
