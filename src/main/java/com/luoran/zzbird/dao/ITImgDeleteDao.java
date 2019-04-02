package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TImgDelete;

/**
 * @author lifetime
 *
 */
public interface ITImgDeleteDao extends BaseDao<TImgDelete> {

	public void queryPage(PageQuery<TImgDelete> pageQuery);

}
