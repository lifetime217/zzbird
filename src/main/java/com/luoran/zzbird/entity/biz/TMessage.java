package com.luoran.zzbird.entity.biz;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import com.luoran.zzbird.core.BaseInfo;


/**
 * 消息表
 */
public class TMessage extends BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TMessage() {
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
	 * 消息标题
	 */
	public String getTitle() {
		return getString("title");
	}

	public void setTitle(String title) {
		set("title", title);
	}

	/**
	 * 消息内容（不能超过200字）
	 */
	public String getContent() {
		return getString("content");
	}

	public void setContent(String content) {
		set("content", content);
	}

	/**
	 * 发送者id（t_xcx_user_role的id）
	 */
	public String getFromUser() {
		return getString("fromUser");
	}

	public void setFromUser(String fromUser) {
		set("fromUser", fromUser);
	}

	/**
	 * 接受者id（t_xcx_user_role的id）
	 */
	public String getToUser() {
		return getString("toUser");
	}

	public void setToUser(String toUser) {
		set("toUser", toUser);
	}

	/**
	 * 0：未读，1：已读
	 */
	public Integer getRead() {
		return getInteger("read");
	}

	public void setRead(Integer read) {
		set("read", read);
	}

	/**
	 * 0：未删除，1：已删除
	 */
	public Integer getIsdelete() {
		return getInteger("isdelete");
	}

	public void setIsdelete(Integer isdelete) {
		set("isdelete", isdelete);
	}
	
	
	/**
	 * 发送消息的时间
	 *
	 */
	public Date getAddTime() {
		return getDate("addTime");
	}

	public void setAddTime(Date addTime) {
		set("addTime", addTime);
	}
}