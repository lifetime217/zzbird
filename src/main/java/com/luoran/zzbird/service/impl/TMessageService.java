package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseDao;
import com.luoran.zzbird.dao.ITMessageDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITMessageService;

/**
 * @author lifetime
 *
 */
@Service
public class TMessageService extends AbstractBaseService<TMessage> implements ITMessageService {
	@Autowired
	private ITMessageDao messageDao;
	@Autowired
	private ITCompanyCourseDao companyCourseDao;
	

	@Override
	public BaseDao<TMessage> getDao() {
		return messageDao;
	}

	@Override
	public String add(TMessage t) {
		return super.add(t);
	}

	@Override
	public PageQuery<TMessage> getPageMessageList(PageQuery<TMessage> pageQuery) {
		messageDao.queryPageMessageList(pageQuery);
		return pageQuery;
	}

	@Override
	public TMessage getMsgById(String roleId) {
		TMessage msg = messageDao.queryMsgById(roleId);
		return msg;
	}

	@Override
	public Integer getUnreadMessageCountByRoleId(String roleId) {
		Integer queryUnreadMessageCountByRoleId = messageDao.queryUnreadMessageCountByRoleId(roleId);
		return queryUnreadMessageCountByRoleId;
	}

	@Override
	public void sendDakaMessage(UserContextInfo user, Map<String, Object> params, List<Map> studentList,Date date) {
		TCompanyCourse course = companyCourseDao.unique(params.get("courseId").toString());
		for (int i = 0; i < studentList.size(); i++) {
			TMessage mess = new TMessage();
			mess.setAddTime(date);
			mess.setContent(user.getRoleName()+"老师已经在 “"+course.getCourseName()+"”课程中为你打卡。");
			mess.setTitle("打卡");
			mess.setToUser(studentList.get(i).get("id").toString());
			mess.setFromUser(user.getXcxUserRoleId().toString());
			mess.setRead(0);
			mess.setIsdelete(0);
			messageDao.insert(mess);
		}
	}

}