package com.luoran.zzbird.dao;

import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TMessage;

/**
 * @author tzx
 *
 */
public interface ITMessageDao extends BaseDao<TMessage> {

	public void queryPage(PageQuery<TMessage> pageQuery);
	
	/**
	 * @author tzx
	 * @param #roleId:接收人的角色Id
	 */
	public void queryPageMessageList(PageQuery<TMessage> pageQuery);
	/**
	 * @author tzx 根据id获取消息
	 * @param id ： 消息的id
	 */
	public TMessage queryMsgById(String roleId);
	
	/**
	 * @author tzx  查询就收人的未读消息数量
	 * @param roleId ： 接收人的角色Id
	 */
	public Integer queryUnreadMessageCountByRoleId(String roleId);
	
	/**
	 * @author tzx 打卡取消后打卡消息 isdelete= 1
	 * @param id ： 学生id，
	 * @param date ： 打卡时间，
	 */
	public Integer quXiaoDakaMessage(String id,String date);
}
