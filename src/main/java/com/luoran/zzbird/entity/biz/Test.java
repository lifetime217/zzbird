package com.luoran.zzbird.entity.biz;

import java.io.Serializable;

import org.beetl.sql.core.annotatoin.AutoID;

import com.luoran.zzbird.core.BaseInfo;


/**
 * 
 */
public class Test extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public Test() {
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
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		set("name", name);
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
	public String getNick() {
		return getString("nick");
	}

	public void setNick(String nick) {
		set("nick", nick);
	}

}