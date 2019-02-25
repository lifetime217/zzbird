package com.luoran.zzbird.service;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.UserInfo;

public interface IUserService extends IBaseService<UserInfo> {

	public UserInfo getUser(String number, String pwd);

	/**
	 * 是否是有效的原始密码
	 * 
	 * @param number
	 * @param pwd
	 * @return
	 */
	public boolean pwdSuccess(String number, String pwd);

	/**
	 * 修改用户密码
	 * 
	 * @param number
	 * @param newPwd
	 */
	public void modifyPwd(String number, String newPwd);

	public UserInfo getInfoByNumber(String number);


	// 校验用户名是否被占用
	public UserInfo checkUserName(String loginNo);

}
