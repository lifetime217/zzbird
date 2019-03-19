package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserRoleDao extends BaseDao<TXcxUserRole> {

	public void queryPage(PageQuery<TXcxUserRole> pageQuery);

	/**
	 * 查询公司的用户 （根据传入的参数来判断查询公司老师，还是公司学生）
	 * @Author wsl  
	 * @Description:
	 */
	List<TXcxUserRole> queryCompanyUser(@Param("roleVal") Integer roleVal,@Param("companyId")String companyId);
	
	/**
	 * 
	 * @Author wsl
	 * @Description:  修改正在使用的角色为0
	 */
	Integer updateCurrentActiveByZero(String sessionKey);
	
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:根据id修改正在使用
	 */
	Integer updateActive(Integer id);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description: 用户收邀请进来查询该公司和该课程下是否是老师或者是学生
	 */
	List<TXcxUserRole> queryUserRoleExist(String sessionKey,String courseId);
}
