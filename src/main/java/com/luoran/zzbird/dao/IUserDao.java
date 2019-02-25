package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.UserInfo;

public interface IUserDao extends BaseDao<UserInfo>{

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param loginNo
	 *            登录账号
	 * @return
	 */
	public UserInfo getInfoByNumber(@Param("loginNo") String loginNo);
	
	public UserInfo getInfo(@Param("id") String id);

	/**
	 * 修改账号密码
	 * 
	 * @param loginNo
	 *            登录账号
	 * @param loginPwd
	 *            密码(已经加密处理过)
	 */
	public void modifyPwd(@Param("loginNo") String loginNo, @Param("loginPwd") String loginPwd);

	/**
	 * 登录验证账号和密码
	 * 
	 * @param loginNo
	 *            登录账号
	 * @param loginPwd
	 *            密码
	 * @return
	 */
	public UserInfo userLogin(@Param("loginNo") String loginNo, @Param("loginPwd") String loginPwd);

	//用户名唯一性校验
	public UserInfo selectUserByLoginNo(@Param("loginNo")String loginNo);

	//邮箱
	public UserInfo selectUserByEmail(@Param("email")String email);

	//根据当前登录账号查询公司成员
	public List<UserInfo> getProjectMembers(@Param("companyId")String companyId);
	/*//根据当前登录账号查询公司管理员
	public List<UserInfo> getProjectManagers(@Param("companyId")String companyId);*/
}
