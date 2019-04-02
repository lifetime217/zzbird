package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 删除的图片表
 */
public class TImgDelete extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TImgDelete() {
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
	 * 删除的图片名称
	 */
	public String getImgName() {
		return getString("imgName");
	}

	public void setImgName(String imgName) {
		set("imgName", imgName);
	}
	
	/**
	 * 添加时间
	 */
	public Date getAddTime() {
		return getDate("addTime");
	}

	public void setAddTime(Date addTime) {
		set("addTime", addTime);
	}

}