package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TSharePoster;

/**
 * @author lifetime
 *
 */
public interface ITSharePosterDao extends BaseDao<TSharePoster> {

	public void queryPage(PageQuery<TSharePoster> pageQuery);

	/**
	 * 
	 * @Author wsl
	 * @Description:根据海报模板id查询分享海报信息
	 */
	List<TSharePoster> querySharePoster(String posterId);

}
