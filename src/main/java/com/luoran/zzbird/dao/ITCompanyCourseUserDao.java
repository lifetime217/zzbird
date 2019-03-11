package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;

/**
 * @author tzx
 *
 */
public interface ITCompanyCourseUserDao extends BaseDao<TCompanyCourseUser> {

	public void queryPage(PageQuery<TCompanyCourseUser> pageQuery);
	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据Boos的角色id查询企业下的用户并且分页
	 */
	public void queryComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);
	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据用户的角色id查询所有课程
	 */
	public List<TCompanyCourseUser> queryCourseByUserRoleId(Map<String, String> map);
}
