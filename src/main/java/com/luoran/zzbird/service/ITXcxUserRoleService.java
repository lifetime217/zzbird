package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserRoleService extends IBaseService<TXcxUserRole> {

	/**
	 * @author wsl
	 * @Description: 根据传入的角色参数来查询公司的用户和老师
	 */
	List<TXcxUserRole> queryCompanyUser(Integer roleVal, String companyId);

	/**
	 * 
	 * @Author wsl
	 * @Description:  修改正在使用的角色为0
	 */
	boolean updateCurrentActiveByZero(String xcxUserId);
	/**
	 * 
	 * @Author tzx
	 * @param roleId 用户的角色ID
	 * @param imgUrl 用户的头像
	 * @param nickName 用户的名称
	 */
	TXcxUserRole updataUserRole(Map<String, String> params);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:根据id修改正在使用
	 */
	boolean updateActive(Integer id);
	
}
