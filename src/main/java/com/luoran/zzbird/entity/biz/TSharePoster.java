package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 二维码背景图
 */
public class TSharePoster extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TSharePoster() {
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
	 * 海报id
	 */
	public String getPosterId() {
		return getString("posterId");
	}

	public void setPosterId(String posterId) {
		set("posterId", posterId);
	}

	/**
	 * 0代表图片 1代表文字  2代表二维码
	 */
	public Integer getType() {
		return getInteger("type");
	}

	public void setType(Integer type) {
		set("type", type);
	}

	/**
	 * 字体颜色
	 */
	public String getColor() {
		return getString("color");
	}

	public void setColor(String color) {
		set("color", color);
	}

	/**
	 * 字体大小  只有当type为1时
	 */
	public Integer getFontSize() {
		return getInteger("fontSize");
	}

	public void setFontSize(Integer fontSize) {
		set("fontSize", fontSize);
	}

	/**
	 * 文字是否居中 0代表不居中 1代表居中
	 */
	public Integer getIsCenter() {
		return getInteger("isCenter");
	}

	public void setIsCenter(Integer isCenter) {
		set("isCenter", isCenter);
	}

	/**
	 * 距离左上角的x轴距离
	 */
	public Integer getDistanceX() {
		return getInteger("distanceX");
	}

	public void setDistanceX(Integer distanceX) {
		set("distanceX", distanceX);
	}

	/**
	 * 距离左上角的y轴距离
	 */
	public Integer getDistanceY() {
		return getInteger("distanceY");
	}

	public void setDistanceY(Integer distanceY) {
		set("distanceY", distanceY);
	}

	/**
	 * 图片url  只有当type为0时
	 */
	public String getImgUrl() {
		return getString("imgUrl");
	}

	public void setImgUrl(String imgUrl) {
		set("imgUrl", imgUrl);
	}

	/**
	 * 图片宽度 只有当type为0时
	 */
	public Integer getImgWidth() {
		return getInteger("imgWidth");
	}

	public void setImgWidth(Integer imgWidth) {
		set("imgWidth", imgWidth);
	}

	/**
	 * 图片高度 只有当type为0时
	 */
	public Integer getImgHeight() {
		return getInteger("imgHeight");
	}

	public void setImgHeight(Integer imgHeight) {
		set("imgHeight", imgHeight);
	}

	/**
	 * 变量id 只有当type为1时
	 */
	public String getVariableId() {
		return getString("variableId");
	}

	public void setVariableId(String variableId) {
		set("variableId", variableId);
	}

	/**
	 * 变量名称 只有当type为1时
	 */
	public String getVariableName() {
		return getString("variableName");
	}

	public void setVariableName(String variableName) {
		set("variableName", variableName);
	}

	/**
	 * 根据当前时间跟新
	 */
	public Date getCreatetime() {
		return getDate("createtime");
	}

	public void setCreatetime(Date createtime) {
		set("createtime", createtime);
	}

}