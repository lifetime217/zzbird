package com.luoran.zzbird.service;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TMessage;

/**
 * @author lifetime
 *
 */
public interface ITMessageService extends IBaseService<TMessage>{
	/**
	 * @author tzx
	 * @param #roleId:接收人的角色Id 
	 * @param #page:需要查询的页号
	 */
	PageQuery<TMessage> getPageMessageList(PageQuery<TMessage> pageQuery);
	/**
	 * @author tzx 根据id获取消息
	 * @param id ： 消息的id
	 */
	TMessage getMsgById(String id);
	
	
	/**
	 * @author tzx  查询就收人的未读消息数量
	 * @param roleId ： 接收人的角色Id
	 */
	public Integer getUnreadMessageCountByRoleId(String roleId);
}
