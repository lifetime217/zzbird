package com.luoran.zzbird.entity.biz;

import java.io.Serializable;

import org.beetl.sql.core.annotatoin.AutoID;

import com.luoran.zzbird.core.BaseInfo;


/**
 * 
 */
public class User extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public User() {
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
	 * 
	 */
	public Integer getAge() {
		return getInteger("age");
	}

	public void setAge(Integer age) {
		set("age", age);
	}

	/**
	 * 
	 */
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		set("name", name);
	}

}