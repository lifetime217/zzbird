package com.luoran.zzbird.entity;

import java.io.Serializable;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import com.luoran.zzbird.core.BaseInfo;

@Table(name="bt_user")
public class UserInfo extends BaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserInfo() {
	}

	/**
	 * 
	 */
	@AssignID("uuid")
	public String getId() {
		return getString("id");
	}

	public void setId(String id) {
		set("id", id);
	}

	/**
	 * 登录账号
	 */
	public String getLoginNo() {
		return getString("loginNo");
	}

	public void setLoginNo(String loginNo) {
		set("loginNo", loginNo);
	}

	/**
	 * 登录密码
	 */
	public String getLoginPwd() {
		return getString("loginPwd");
	}

	public void setLoginPwd(String loginPwd) {
		set("loginPwd", loginPwd);
	}

	/**
	 * 公司id
	 */
	public String getCompanyId() {
		return getString("companyId");
	}

	public void setCompanyId(String companyId) {
		set("companyId", companyId);
	}

	/**
	 * 公司名称
	 */
	public String getCompanyName() {
		return getString("companyName");
	}

	public void setCompanyName(String companyName) {
		set("companyName", companyName);
	}

	/**
	 * 公司编码
	 */
	public String getCompanyCode() {
		return getString("companyCode");
	}

	public void setCompanyCode(String companyCode) {
		set("companyCode", companyCode);
	}

	/**
	 * 用户昵称
	 */
	public String getNick() {
		return getString("nick");
	}

	public void setNick(String nick) {
		set("nick", nick);
	}

	/**
	 * 上一次登录时间
	 */
	public Date getPreLoginTime() {
		return getDate("preLoginTime");
	}

	public void setPreLoginTime(Date preLoginTime) {
		set("preLoginTime", preLoginTime);
	}

	/**
	 * 上一次登录地点
	 */
	public String getPreLoginAddress() {
		return getString("preLoginAddress");
	}

	public void setPreLoginAddress(String preLoginAddress) {
		set("preLoginAddress", preLoginAddress);
	}

	/**
	 * 
	 */
	public String getPreLoginIp() {
		return getString("preLoginIp");
	}

	public void setPreLoginIp(String preLoginIp) {
		set("preLoginIp", preLoginIp);
	}

	/**
	 * 最后登录时间
	 */
	public Date getLastLoginTime() {
		return getDate("lastLoginTime");
	}

	public void setLastLoginTime(Date lastLoginTime) {
		set("lastLoginTime", lastLoginTime);
	}

	/**
	 * 最后一次登录地点
	 */
	public String getLastLoginAddress() {
		return getString("lastLoginAddress");
	}

	public void setLastLoginAddress(String lastLoginAddress) {
		set("lastLoginAddress", lastLoginAddress);
	}

	/**
	 * 最后登录时的ip
	 */
	public String getLastLoginIp() {
		return getString("lastLoginIp");
	}

	public void setLastLoginIp(String lastLoginIp) {
		set("lastLoginIp", lastLoginIp);
	}

	/**
	 * 用户邮箱
	 */
	public String getEmail() {
		return getString("email");
	}

	public void setEmail(String email) {
		set("email", email);
	}

	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return getDate("createTime");
	}

	public void setCreateTime(Date createTime) {
		set("createTime", createTime);
	}

	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return getDate("updateTime");
	}

	public void setUpdateTime(Date updateTime) {
		set("updateTime", updateTime);
	}

	/**
	 * 0正常,1删除
	 */
	public Integer getStatus() {
		return getInteger("status");
	}

	public void setStatus(Integer status) {
		set("status", status);
	}

	/**
	 * 用户拥有的权限
	 */
	public String getRole() {
		return getString("role");
	}

	public void setRole(String role) {
		set("role", role);
	}

	/**
	 * 用户类型:0超级管理员.1系统管理员.2普通用户
	 */
	public Integer getUserType() {
		return getInteger("userType");
	}

	public void setUserType(Integer userType) {
		set("userType", userType);
	}

}
