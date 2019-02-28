package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 打卡记录表
 */
public class TDakaRecord extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TDakaRecord() {
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
	 * 公司课程id
	 */
	public String getCompanyCourseId() {
		return getString("companyCourseId");
	}

	public void setCompanyCourseId(String companyCourseId) {
		set("companyCourseId", companyCourseId);
	}

	/**
	 * 老师的角色id（t_xcx_user_role）
	 */
	public String getTeacherId() {
		return getString("teacherId");
	}

	public void setTeacherId(String teacherId) {
		set("teacherId", teacherId);
	}

	/**
	 * 学生的角色id（t_xcx_user_role）
	 */
	public String getStudentId() {
		return getString("studentId");
	}

	public void setStudentId(String studentId) {
		set("studentId", studentId);
	}

	/**
	 * 打卡时间
	 */
	public Date getDakaTime() {
		return getDate("dakaTime");
	}

	public void setDakaTime(Date dakaTime) {
		set("dakaTime", dakaTime);
	}

	/**
	 * 1：已删除，0：未删除
	 */
	public Integer getIsdelete() {
		return getInteger("isdelete");
	}

	public void setIsdelete(Integer isdelete) {
		set("isdelete", isdelete);
	}

}