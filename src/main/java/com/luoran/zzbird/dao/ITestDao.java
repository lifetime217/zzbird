package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.Test;

/**
 * @author lifetime
 *
 */
public interface ITestDao extends BaseDao<Test> {

	public void queryPage(PageQuery<Test> pageQuery);

	
	public List<String> findNames(@Param("age") Integer age);
	public List<Test> findNick(@Param("nick") String nick);
}
