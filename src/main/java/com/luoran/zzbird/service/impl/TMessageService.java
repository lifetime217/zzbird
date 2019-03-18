package com.luoran.zzbird.service.impl;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITMessageDao;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITMessageService;


/**
 * @author lifetime
 *
 */
@Service
public class TMessageService extends AbstractBaseService<TMessage> implements ITMessageService{
	@Autowired
	private ITMessageDao messageDao;

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
		return msg ;
	}

}