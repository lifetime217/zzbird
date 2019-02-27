package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.User;

/**
 * @author lifetime
 *
 */
public interface IUserDao extends BaseDao<User> {

	public void queryPage(PageQuery<User> pageQuery);

	public List<String> findNames(Integer age);
}
