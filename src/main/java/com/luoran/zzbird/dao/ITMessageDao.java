package com.luoran.zzbird.dao;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TMessage;

/**
 * @author lifetime
 *
 */
public interface ITMessageDao extends BaseDao<TMessage> {

	public void queryPage(PageQuery<TMessage> pageQuery);

}
