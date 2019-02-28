package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 行业基础表
 */
public class TIndustry extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TIndustry() {
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
	 * 行业名称
	 */
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	/**
	 * 行业编码
	 */
	public String getNumber() {
		return getString("number");
	}

	public void setNumber(String number) {
		set("number", number);
	}

	/**
	 * 
	 */
	public Integer getParentId() {
		return getInteger("parentId");
	}

	public void setParentId(Integer parentId) {
		set("parentId", parentId);
	}

}