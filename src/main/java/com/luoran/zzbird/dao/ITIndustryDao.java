package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TIndustry;
import com.luoran.zzbird.entity.vo.IndustryVo;

/**
 * @author lifetime
 *
 */
public interface ITIndustryDao extends BaseDao<TIndustry> {

	public void queryPage(PageQuery<TIndustry> pageQuery);
	
	List<TIndustry> queryParentIndustry();
	
	List<IndustryVo> queryAllIndustry();
}
