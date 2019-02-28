package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 企业信息表
 */
public class TCompany extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TCompany() {
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
	 * 企业名称
	 */
	public String getCompanyName() {
		return getString("companyName");
	}

	public void setCompanyName(String companyName) {
		set("companyName", companyName);
	}

	/**
	 * 企业详细地址
	 */
	public String getCompanyAdress() {
		return getString("companyAdress");
	}

	public void setCompanyAdress(String companyAdress) {
		set("companyAdress", companyAdress);
	}

	/**
	 * 企业地址简称
	 */
	public String getCompanySimpleAddress() {
		return getString("companySimpleAddress");
	}

	public void setCompanySimpleAddress(String companySimpleAddress) {
		set("companySimpleAddress", companySimpleAddress);
	}

	/**
	 * 企业电话
	 */
	public String getTelphone() {
		return getString("telphone");
	}

	public void setTelphone(String telphone) {
		set("telphone", telphone);
	}

	/**
	 * 纬度
	 */
	public BigDecimal getLat() {
		return getBigDecimal("lat");
	}

	public void setLat(BigDecimal lat) {
		set("lat", lat);
	}

	/**
	 * 经度
	 */
	public BigDecimal getLng() {
		return getBigDecimal("lng");
	}

	public void setLng(BigDecimal lng) {
		set("lng", lng);
	}

	/**
	 * 经纬度hash值
	 */
	public String getGeohash() {
		return getString("geohash");
	}

	public void setGeohash(String geohash) {
		set("geohash", geohash);
	}

	/**
	 * 创建企业的人员
	 */
	public String getXcxOpenId() {
		return getString("xcxOpenId");
	}

	public void setXcxOpenId(String xcxOpenId) {
		set("xcxOpenId", xcxOpenId);
	}

	/**
	 * 公司详情页
	 */
	public String getCompanyDetailInfo() {
		return getString("companyDetailInfo");
	}

	public void setCompanyDetailInfo(String companyDetailInfo) {
		set("companyDetailInfo", companyDetailInfo);
	}

	/**
	 * 企业创建时间
	 */
	public Date getAddTime() {
		return getDate("addTime");
	}

	public void setAddTime(Date addTime) {
		set("addTime", addTime);
	}

	/**
	 * 查看次数
	 */
	public Integer getLookCount() {
		return getInteger("lookCount");
	}

	public void setLookCount(Integer lookCount) {
		set("lookCount", lookCount);
	}

	/**
	 * 分享次数
	 */
	public Integer getShareCount() {
		return getInteger("shareCount");
	}

	public void setShareCount(Integer shareCount) {
		set("shareCount", shareCount);
	}

	/**
	 * 点赞次数
	 */
	public Integer getLoveCount() {
		return getInteger("loveCount");
	}

	public void setLoveCount(Integer loveCount) {
		set("loveCount", loveCount);
	}

	/**
	 * 公司的行业标签id集合
	 */
	public String getIndustryListId() {
		return getString("industryListId");
	}

	public void setIndustryListId(String industryListId) {
		set("industryListId", industryListId);
	}

	/**
	 * 公司的行业标签name集合
	 */
	public String getIndustryListName() {
		return getString("industryListName");
	}

	public void setIndustryListName(String industryListName) {
		set("industryListName", industryListName);
	}

	/**
	 * 是否重点区域展示（0：不展示，1：展示）
	 */
	public Integer getShowV1() {
		return getInteger("showV1");
	}

	public void setShowV1(Integer showV1) {
		set("showV1", showV1);
	}

	/**
	 * 重点区域展示时的优先级，数字越大排名越后
	 */
	public Integer getV1() {
		return getInteger("v1");
	}

	public void setV1(Integer v1) {
		set("v1", v1);
	}

	/**
	 * 公司头部banner图，限制在10张以内
	 */
	public String getBannerImgs() {
		return getString("bannerImgs");
	}

	public void setBannerImgs(String bannerImgs) {
		set("bannerImgs", bannerImgs);
	}

	/**
	 * 老师人数
	 */
	public Integer getTeacherCount() {
		return getInteger("teacherCount");
	}

	public void setTeacherCount(Integer teacherCount) {
		set("teacherCount", teacherCount);
	}

	/**
	 * 学生人数
	 */
	public Integer getStudentCount() {
		return getInteger("studentCount");
	}

	public void setStudentCount(Integer studentCount) {
		set("studentCount", studentCount);
	}

	/**
	 * 公司唯一码
	 */
	public String getSign() {
		return getString("sign");
	}

	public void setSign(String sign) {
		set("sign", sign);
	}

}