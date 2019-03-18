package com.luoran.zzbird.entity.biz;

import java.io.Serializable;

import org.beetl.sql.core.annotatoin.AutoID;

import com.luoran.zzbird.core.BaseInfo;


/**
 * 小程序用户-角色关系表
 */
public class TXcxUserRole extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TXcxUserRole() {
	}

	/**
	 * 
	 */
	@AutoID
	public Integer getId() {
		return getInteger("id");
	}

	public void setId(Integer id) {
		set("id", id);
	}

	/**
	 * 公司管理id
	 */
	public String getCompanyId() {
		return getString("companyId");
	}

	public void setCompanyId(String companyId) {
		set("companyId", companyId);
	}

	/**
	 * 10：企业，20：老师，30：学生
	 */
	public Integer getRoleVal() {
		return getInteger("roleVal");
	}

	public void setRoleVal(Integer roleVal) {
		set("roleVal", roleVal);
	}

	/**
	 * 角色名称
	 */
	public String getRoleName() {
		return getString("roleName");
	}

	public void setRoleName(String roleName) {
		set("roleName", roleName);
	}

	/**
	 * 角色头像
	 */
	public String getRoleHeadimg() {
		return getString("roleHeadimg");
	}

	public void setRoleHeadimg(String roleHeadimg) {
		set("roleHeadimg", roleHeadimg);
	}

	/**
	 * 角色关联的小程序用户id
	 */
	public String getXcxUserId() {
		return getString("xcxUserId");
	}

	public void setXcxUserId(String xcxUserId) {
		set("xcxUserId", xcxUserId);
	}

	/**
	 * 当前正在使用的角色（1：正在使用，0：未使用）
	 */
	public Integer getCurrentActive() {
		return getInteger("currentActive");
	}

	public void setCurrentActive(Integer currentActive) {
		set("currentActive", currentActive);
	}

	/**
	 * 是否删除标记（0：未删除，1：已删除）
	 */
	public Integer getIsdelete() {
		return getInteger("isdelete");
	}

	public void setIsdelete(Integer isdelete) {
		set("isdelete", isdelete);
	}

	/**
	 * 用户角色唯一码
	 */
	public String getSign() {
		return getString("sign");
	}

	public void setSign(String sign) {
		set("sign", sign);
	}

}