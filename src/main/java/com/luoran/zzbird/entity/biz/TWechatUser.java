package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 微信用户基本信息表
 */
public class TWechatUser extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TWechatUser() {
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
	 * 用户的标识，对当前公众号唯一
	 */
	public String getOpenId() {
		return getString("openId");
	}

	public void setOpenId(String openId) {
		set("openId", openId);
	}

	/**
	 * 用户的昵称
	 */
	public String getNickName() {
		return getString("nickName");
	}

	public void setNickName(String nickName) {
		set("nickName", nickName);
	}

	/**
	 * 用户的性别
	 */
	public Integer getSex() {
		return getInteger("sex");
	}

	public void setSex(Integer sex) {
		set("sex", sex);
	}

	/**
	 * 用户所在城市
	 */
	public String getCity() {
		return getString("city");
	}

	public void setCity(String city) {
		set("city", city);
	}

	/**
	 * 用户所在国家
	 */
	public String getCountry() {
		return getString("country");
	}

	public void setCountry(String country) {
		set("country", country);
	}

	/**
	 * 用户所在省份
	 */
	public String getProvince() {
		return getString("province");
	}

	public void setProvince(String province) {
		set("province", province);
	}

	/**
	 * 用户的语言，简体中文为zh_CN
	 */
	public String getLanguage() {
		return getString("language");
	}

	public void setLanguage(String language) {
		set("language", language);
	}

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）
	 */
	public String getHeadimgUrl() {
		return getString("headimgUrl");
	}

	public void setHeadimgUrl(String headimgUrl) {
		set("headimgUrl", headimgUrl);
	}

	/**
	 * 微信头像的唯一码
	 */
	public String getHeadimgMd5() {
		return getString("headimgMd5");
	}

	public void setHeadimgMd5(String headimgMd5) {
		set("headimgMd5", headimgMd5);
	}

	/**
	 * 用户关注时间，为时间戳
	 */
	public Date getSubscribeTime() {
		return getDate("subscribeTime");
	}

	public void setSubscribeTime(Date subscribeTime) {
		set("subscribeTime", subscribeTime);
	}

	/**
	 * 公众号运营者对粉丝的备注
	 */
	public String getRemark() {
		return getString("remark");
	}

	public void setRemark(String remark) {
		set("remark", remark);
	}

	/**
	 * 用户所在的分组ID
	 */
	public String getGroupid() {
		return getString("groupid");
	}

	public void setGroupid(String groupid) {
		set("groupid", groupid);
	}

	/**
	 * 用户被打上的标签ID列表
	 */
	public String getTagidList() {
		return getString("tagidList");
	}

	public void setTagidList(String tagidList) {
		set("tagidList", tagidList);
	}

	/**
	 * 返回用户关注的渠道来源
	 */
	public String getSubscribeScene() {
		return getString("subscribeScene");
	}

	public void setSubscribeScene(String subscribeScene) {
		set("subscribeScene", subscribeScene);
	}
	
	
	/**
	 * 返回用户关注的状态
	 */
	public Integer getSubscribe() {
		return getInteger("subscribe");
	}

	public void setSubscribe(Integer subscribe) {
		set("subscribe", subscribe);
	}

	/**
	 * 二维码扫码场景
	 */
	public String getQrScene() {
		return getString("qrScene");
	}

	public void setQrScene(String qrScene) {
		set("qrScene", qrScene);
	}

	/**
	 * 二维码扫码场景描述
	 */
	public String getQrSceneStr() {
		return getString("qrSceneStr");
	}

	public void setQrSceneStr(String qrSceneStr) {
		set("qrSceneStr", qrSceneStr);
	}

}