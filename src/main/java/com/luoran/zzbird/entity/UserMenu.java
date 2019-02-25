package com.luoran.zzbird.entity;

import java.io.Serializable;

import org.beetl.sql.core.annotatoin.AssignID;

import com.luoran.zzbird.core.BaseInfo;


/**
 * 
 */
public class UserMenu extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public UserMenu() {
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
	 * 用户ID
	 */
	public String getUserId() {
		return getString("userId");
	}

	public void setUserId(String userId) {
		set("userId", userId);
	}

	/**
	 * 菜单ID
	 */
	public String getMenuId() {
		return getString("menuId");
	}

	public void setMenuId(String menuId) {
		set("menuId", menuId);
	}

}