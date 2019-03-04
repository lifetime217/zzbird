package com.luoran.zzbird.entity.vo;

import java.util.List;

/**
 * @author wsl
 * @Description
 */
public class CompanyDetailVo {

	private String id;
	private String companyName;
	private String companyAddress;
	private String companySimpleAddress;
	private String telphone;
	private String companyDetailInfo;
	private Integer lookCount;
	private Integer shareCount;
	private Integer loveCount;
	private String industryListName;
	private String bannerImg;
	private Integer teaCount;
	private Integer stuCount;
	private String sign;
	private List<String> bannerList;
	private List<String> industryList;

	public CompanyDetailVo() {
		super();
	}

	public CompanyDetailVo(String id, String companyName, String companyAddress, String companySimpleAddress,
			String telphone, String companyDetailInfo, Integer lookCount, Integer shareCount, Integer loveCount,
			String industryListName, String bannerImg, Integer teaCount, Integer stuCount, String sign,
			List<String> bannerList, List<String> industryList) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companySimpleAddress = companySimpleAddress;
		this.telphone = telphone;
		this.companyDetailInfo = companyDetailInfo;
		this.lookCount = lookCount;
		this.shareCount = shareCount;
		this.loveCount = loveCount;
		this.industryListName = industryListName;
		this.bannerImg = bannerImg;
		this.teaCount = teaCount;
		this.stuCount = stuCount;
		this.sign = sign;
		this.bannerList = bannerList;
		this.industryList = industryList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanySimpleAddress() {
		return companySimpleAddress;
	}

	public void setCompanySimpleAddress(String companySimpleAddress) {
		this.companySimpleAddress = companySimpleAddress;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCompanyDetailInfo() {
		return companyDetailInfo;
	}

	public void setCompanyDetailInfo(String companyDetailInfo) {
		this.companyDetailInfo = companyDetailInfo;
	}

	public Integer getLookCount() {
		return lookCount;
	}

	public void setLookCount(Integer lookCount) {
		this.lookCount = lookCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(Integer loveCount) {
		this.loveCount = loveCount;
	}

	public String getIndustryListName() {
		return industryListName;
	}

	public void setIndustryListName(String industryListName) {
		this.industryListName = industryListName;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public Integer getTeaCount() {
		return teaCount;
	}

	public void setTeaCount(Integer teaCount) {
		this.teaCount = teaCount;
	}

	public Integer getStuCount() {
		return stuCount;
	}

	public void setStuCount(Integer stuCount) {
		this.stuCount = stuCount;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<String> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<String> bannerList) {
		this.bannerList = bannerList;
	}

	public List<String> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<String> industryList) {
		this.industryList = industryList;
	}

}
