package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;

import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.entity.vo.InviteVo;

/**
 * 小程序用户基本信息表
 */
public class TXcxUser extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TXcxUser() {
	}

	public TXcxUser(InviteVo inviteVo) {
		setAvatarUrl(inviteVo.getAvatarUrl());
		setCity(inviteVo.getCity());
		setCountry(inviteVo.getCountry());
		setGender(inviteVo.getGender());
		setLanguage(inviteVo.getLanguage());
		setNickName(inviteVo.getNickName());
		setProvince(inviteVo.getProvince());
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
	 * 用户唯一标识
	 */
	public String getOpenId() {
		return getString("openId");
	}

	public void setOpenId(String openId) {
		set("openId", openId);
	}

	/**
	 * 会话密钥
	 */
	public String getSessionKey() {
		return getString("sessionKey");
	}

	public void setSessionKey(String sessionKey) {
		set("sessionKey", sessionKey);
	}

	/**
	 * 昵称
	 */
	public String getNickName() {
		return getString("nickName");
	}

	public void setNickName(String nickName) {
		set("nickName", nickName);
	}

	/**
	 * 头像
	 */
	public String getAvatarUrl() {
		return getString("avatarUrl");
	}

	public void setAvatarUrl(String avatarUrl) {
		set("avatarUrl", avatarUrl);
	}

	/**
	 * 头像的md5值
	 */
	public String getAvatarUrlMd5() {
		return getString("avatarUrlMd5");
	}

	public void setAvatarUrlMd5(String avatarUrlMd5) {
		set("avatarUrlMd5", avatarUrlMd5);
	}

	/**
	 * 性别
	 */
	public Integer getGender() {
		return getInteger("gender");
	}

	public void setGender(Integer gender) {
		set("gender", gender);
	}

	/**
	 * 国家
	 */
	public String getCountry() {
		return getString("country");
	}

	public void setCountry(String country) {
		set("country", country);
	}

	/**
	 * 省份
	 */
	public String getProvince() {
		return getString("province");
	}

	public void setProvince(String province) {
		set("province", province);
	}

	/**
	 * 城市
	 */
	public String getCity() {
		return getString("city");
	}

	public void setCity(String city) {
		set("city", city);
	}

	/**
	 * 语言
	 */
	public String getLanguage() {
		return getString("language");
	}

	public void setLanguage(String language) {
		set("language", language);
	}

	/**
	 * 加入时间
	 */
	public Date getAddTime() {
		return getDate("addTime");
	}

	public void setAddTime(Date addTime) {
		set("addTime", addTime);
	}

}