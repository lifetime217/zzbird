package com.luoran.zzbird.utils;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.vo.InviteVo;

public class Validate {
	/**
	 * 
	 * @Author wsl
	 * @Description:检验用户传入的参数
	 */
	public static HttpResult XcxUser(TXcxUser xcxUser) {
		if (xcxUser.getAvatarUrl() == null || "".equals(xcxUser.getAvatarUrl())) {
			return HttpResult.fail("请传入图片!");
		}
		if (xcxUser.getCity() == null || "".equals(xcxUser.getCity())) {
			return HttpResult.fail("请传入城市!");
		}
		if (xcxUser.getCountry() == null || "".equals(xcxUser.getCountry())) {
			return HttpResult.fail("请传入国家!");
		}
		if (xcxUser.getGender() == null || "".equals(xcxUser.getGender())) {
			return HttpResult.fail("请传入性别!");
		}
		if (xcxUser.getLanguage() == null || "".equals(xcxUser.getLanguage())) {
			return HttpResult.fail("请传入语言!");
		}
		if (xcxUser.getAvatarUrl() == null || "".equals(xcxUser.getAvatarUrl())) {
			return HttpResult.fail("请传入身份!");
		}
		return null;
	}
	
	/**
	 * 
	 * @Author wsl
	 * @Description:检查公司传入的参数
	 */
	public static HttpResult Company(TCompany company) {
		if (company.getBannerImgs() == null || "".equals(company.getBannerImgs())) {
			return HttpResult.fail("请传入图片!");
		}
		if (company.getCompanyName() == null || "".equals(company.getCompanyName())) {
			return HttpResult.fail("请传入公司名字!");
		}
		if (company.getCompanyAddress() == null || "".equals(company.getCompanyAddress())) {
			return HttpResult.fail("请传入公司地址!");
		}
		if (company.getTelphone() == null || "".equals(company.getTelphone())) {
			return HttpResult.fail("请传入公司电话!");
		}
		if (company.getIndustryListId() == null || "".equals(company.getIndustryListId())) {
			return HttpResult.fail("请传入标签id!");
		}
		if (company.getIndustryListName() == null || "".equals(company.getIndustryListName())) {
			return HttpResult.fail("请传入标签名!");
		}
		if (company.getCompanySimpleAddress() == null || "".equals(company.getCompanySimpleAddress())) {
			return HttpResult.fail("请传入公司简称!");
		}
		if (company.getLng() == null) {
			return HttpResult.fail("请传入经度!");
		}
		if (company.getLat() == null) {
			return HttpResult.fail("请传入维度!");
		}
		return null;
	}
	
	/**
	 * 
	 * @Author wsl
	 * @Description:检验课程传入的参数
	 */
	public static HttpResult Course(TCompanyCourse course) {
		if (course.getCourseImg() == null || "".equals(course.getCourseImg())) {
			return HttpResult.fail("请传入课程图片!");
		}
		if (course.getCourseName() == null || "".equals(course.getCourseName())) {
			return HttpResult.fail("请传入课程名字!");
		}
		if (course.getCourseHour() == null || "".equals(course.getCourseHour())) {
			return HttpResult.fail("请传入课时选择!");
		}
		if (course.getAgeRange() == null || "".equals(course.getAgeRange())) {
			return HttpResult.fail("请传入年龄选择!");
		}
		if (course.getStartTime() == null || "".equals(course.getStartTime())) {
			return HttpResult.fail("请传入开始时间!");
		}
		if (course.getEndTime() == null || "".equals(course.getEndTime())) {
			return HttpResult.fail("请传入结束时间!");
		}
		return null;
	}
	
	/**
	 * 
	 * @Author wsl
	 * @Description:检验邀请 传入的参数
	 */
	public static HttpResult invite(InviteVo inviteVo) {
		TXcxUser tXcxUser = new TXcxUser(inviteVo);
		HttpResult xcxUser = XcxUser(tXcxUser);
		if (xcxUser != null) {
			return xcxUser;
		}
		if (inviteVo.getInviteSessionKey() == null || "".equals(inviteVo.getInviteSessionKey())) {
			return HttpResult.fail("请传入邀请人的id");
		}
		if (inviteVo.getInviteRoleVal() == null || "".equals(inviteVo.getInviteRoleVal())) {
			return HttpResult.fail("请传入邀请人的角色");
		}
		if (inviteVo.getCompanyId() == null || "".equals(inviteVo.getCompanyId())) {
			return HttpResult.fail("请传入公司id");
		}
		if (inviteVo.getCompanyName() == null || "".equals(inviteVo.getCompanyName())) {
			return HttpResult.fail("请传入公司名字");
		}
		if (inviteVo.getCourseId() == null || "".equals(inviteVo.getCourseId())) {
			return HttpResult.fail("请传入课程id");
		}
		if (inviteVo.getCourseName() == null || "".equals(inviteVo.getCourseName())) {
			return HttpResult.fail("请传入课程名字");
		}
		return null;
	}
	
	
	
}
