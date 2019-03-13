package com.luoran.zzbird.core;

import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author lifetime
 *
 */
public class UserContextInfo {
	private Integer xcxUserRoleId;
	private String companyId;
	private String companyName;
	private Integer roleVal;
	private String roleName;
	private String HeadImg;
	private String openid;

	public UserContextInfo() {
	}

	public UserContextInfo(TXcxUserRole userRole) {
		if (userRole.get("openid") != null) {
			this.setOpenid((String) userRole.get("openid"));
		}
		this.setCompanyId(userRole.getCompanyId());
		this.setRoleVal(userRole.getRoleVal());
		this.setXcxUserRoleId(userRole.getId());
		if (userRole.get("companyname") != null) {
			this.setCompanyName((String) userRole.get("companyname"));
		}
		this.setRoleName(userRole.getRoleName());
		this.setHeadImg(userRole.getRoleHeadimg());
	}

	/**
	 * 是否是普通用户
	 * 
	 * @Author wsl
	 * @Description:
	 */
	public boolean isNormalUser() {
		if (getRoleVal() == null) {
			return true;
		}
		return false;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getHeadImg() {
		return HeadImg;
	}

	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}

	public Integer getXcxUserRoleId() {
		return xcxUserRoleId;
	}

	public void setXcxUserRoleId(Integer xcxUserRoleId) {
		this.xcxUserRoleId = xcxUserRoleId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getRoleVal() {
		return roleVal;
	}

	public void setRoleVal(Integer roleVal) {
		this.roleVal = roleVal;
	}

	@Override
	public String toString() {
		return "UserContextInfo [xcxUserRoleId=" + xcxUserRoleId + ", companyId=" + companyId + ", openid=" + openid
				+ ", companyName=" + companyName + ", roleVal=" + roleVal + "]";
	}

}
