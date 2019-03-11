package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;

/**
 * @author tzx
 *
 */
public interface ITCompanyCourseUserService extends IBaseService<TCompanyCourseUser> {
	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据Boos的角色id查询企业下的用户并且分页
	 */
	PageQuery<TCompanyCourseUser> getComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	/**
	 * 
	 * @Author tzx
	 * @Description: TODO 根据用户的角色id查询所有课程
	 */
	List<TCompanyCourseUser> getCourseByUserRoleId(Map<String, String> map);

	

}
