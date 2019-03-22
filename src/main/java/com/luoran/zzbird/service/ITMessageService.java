package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.UserContextInfo;
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
	/**
	 * @author tzx  发送打卡消息
	 * @param userContextInfo ： 发送人的个人信息
	 * @param courseId ：课程ID
	 * @param studentList ：学生id集合
	 */
	void sendDakaMessage(UserContextInfo userContextInfo, Map<String, Object> params, List<Map> studentList);
	
}
