package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 海报模板
 */
public class TPoster extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TPoster() {
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
	 * 海报名字
	 */
	public String getPosterName() {
		return getString("posterName");
	}

	public void setPosterName(String posterName) {
		set("posterName", posterName);
	}

	/**
	 * 海报宽度(原图)
	 */
	public Integer getPosterWidth() {
		return getInteger("posterWidth");
	}

	public void setPosterWidth(Integer posterWidth) {
		set("posterWidth", posterWidth);
	}

	/**
	 * 海报高度(原图)
	 */
	public Integer getPosterHeight() {
		return getInteger("posterHeight");
	}

	public void setPosterHeight(Integer posterHeight) {
		set("posterHeight", posterHeight);
	}

	/**
	 * 图片宽度(展示)
	 */
	public Integer getImgWidth() {
		return getInteger("imgWidth");
	}

	public void setImgWidth(Integer imgWidth) {
		set("imgWidth", imgWidth);
	}

	/**
	 * 图片高度(展示)
	 */
	public Integer getImgHeight() {
		return getInteger("imgHeight");
	}

	public void setImgHeight(Integer imgHeight) {
		set("imgHeight", imgHeight);
	}

	/**
	 * 海报来源
	 */
	public String getPosterSource() {
		return getString("posterSource");
	}

	public void setPosterSource(String posterSource) {
		set("posterSource", posterSource);
	}

	/**
	 * 海报url
	 */
	public String getPosterPageUrl() {
		return getString("posterPageUrl");
	}

	public void setPosterPageUrl(String posterPageUrl) {
		set("posterPageUrl", posterPageUrl);
	}

	/**
	 * 海报存储路劲
	 */
	public String getPosterDepositUrl() {
		return getString("posterDepositUrl");
	}

	public void setPosterDepositUrl(String posterDepositUrl) {
		set("posterDepositUrl", posterDepositUrl);
	}

	/**
	 * 海报内容
	 */
	public String getPosterContext() {
		return getString("posterContext");
	}

	public void setPosterContext(String posterContext) {
		set("posterContext", posterContext);
	}

	/**
	 * 根据当前时间戳来变化
	 */
	public Date getCreatetime() {
		return getDate("createtime");
	}

	public void setCreatetime(Date createtime) {
		set("createtime", createtime);
	}

}