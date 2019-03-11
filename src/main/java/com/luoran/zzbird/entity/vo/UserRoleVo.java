package com.luoran.zzbird.entity.vo;

public class UserRoleVo {

	private Integer xcxUserRoleId;
	private String companyId;
	private String companyName;
	private Integer roleVal;
	private String roleName;
	private String headImg;
	private Integer currentActive;

	public Integer getXcxUserRoleId() {
		return xcxUserRoleId;
	}

	public void setXcxUserRoleId(Integer xcxUserRoleId) {
		this.xcxUserRoleId = xcxUserRoleId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getCurrentActive() {
		return currentActive;
	}

	public void setCurrentActive(Integer currentActive) {
		this.currentActive = currentActive;
	}

}
