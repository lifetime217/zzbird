package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 企业-课程 基础信息表
 */
public class TCompanyCourse extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TCompanyCourse() {
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
	 * 企业id
	 */
	public String getCompanyId() {
		return getString("companyId");
	}

	public void setCompanyId(String companyId) {
		set("companyId", companyId);
	}

	/**
	 * 课程名称
	 */
	public String getCourseName() {
		return getString("courseName");
	}

	public void setCourseName(String courseName) {
		set("courseName", courseName);
	}

	/**
	 * 课时
	 */
	public String getCourseHour() {
		return getString("courseHour");
	}

	public void setCourseHour(String courseHour) {
		set("courseHour", courseHour);
	}

	/**
	 * 适用年龄段
	 */
	public String getAgeRange() {
		return getString("ageRange");
	}

	public void setAgeRange(String ageRange) {
		set("ageRange", ageRange);
	}

	/**
	 * 课程开始时间
	 */
	public String getStartTime() {
		return getString("startTime");
	}

	public void setStartTime(String startTime) {
		set("startTime", startTime);
	}

	/**
	 * 课程结束时间
	 */
	public String getEndTime() {
		return getString("endTime");
	}

	public void setEndTime(String endTime) {
		set("endTime", endTime);
	}

	/**
	 * 当前课程学生人数
	 */
	public Integer getPersonNumber() {
		return getInteger("personNumber");
	}

	public void setPersonNumber(Integer personNumber) {
		set("personNumber", personNumber);
	}

	/**
	 * 课程简介
	 */
	public String getCourseDesc() {
		return getString("courseDesc");
	}

	public void setCourseDesc(String courseDesc) {
		set("courseDesc", courseDesc);
	}

	/**
	 * 课程图片
	 */
	public String getCourseImg() {
		return getString("courseImg");
	}

	public void setCourseImg(String courseImg) {
		set("courseImg", courseImg);
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

}