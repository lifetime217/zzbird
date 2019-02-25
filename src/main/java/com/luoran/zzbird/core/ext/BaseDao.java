package com.luoran.zzbird.core.ext;

import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * @author lifetime217
 *
 * @param <T>
 */
public interface BaseDao<T> extends BaseMapper<T> {

	/**
	 * @param pageQuery
	 */
	public void queryPage(PageQuery<T> pageQuery);
}
