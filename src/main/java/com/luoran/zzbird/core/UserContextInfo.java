package com.luoran.zzbird.core;

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
	private String sessionKey;

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
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
