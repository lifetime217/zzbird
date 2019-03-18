package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.entity.vo.InviteVo;


/**
 * 课程-用户 的中间表
 */
public class TCompanyCourseUser extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TCompanyCourseUser() {
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
	 * 课程id
	 */
	public String getCompanyCourseId() {
		return getString("companyCourseId");
	}

	public void setCompanyCourseId(String companyCourseId) {
		set("companyCourseId", companyCourseId);
	}

	/**
	 * 用户作为某类角色的id
	 */
	public String getXcxUserRoleId() {
		return getString("xcxUserRoleId");
	}

	public void setXcxUserRoleId(String xcxUserRoleId) {
		set("xcxUserRoleId", xcxUserRoleId);
	}

	/**
	 * 
	 */
	public Date getAddTime() {
		return getDate("addTime");
	}

	public void setAddTime(Date addTime) {
		set("addTime", addTime);
	}

}