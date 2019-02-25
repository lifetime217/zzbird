package com.luoran.zzbird.entity;

import java.io.Serializable;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import com.luoran.zzbird.core.BaseInfo;

/**
 * 
 */
@Table(name="bt_company")
public class CompanyInfo extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public CompanyInfo() {
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
	 * 联系人
	 */
	public String getContactPeople() {
		return getString("contactPeople");
	}

	public void setContactPeople(String contactPeople) {
		set("contactPeople", contactPeople);
	}

	/**
	 * 联系电话
	 */
	public String getContactNumber() {
		return getString("contactNumber");
	}

	public void setContactNumber(String contactNumber) {
		set("contactNumber", contactNumber);
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
	 * 公司系统管理员账号
	 */
	public String getAdminAccount() {
		return getString("adminAccount");
	}

	public void setAdminAccount(String adminAccount) {
		set("adminAccount", adminAccount);
	}

}