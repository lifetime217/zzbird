package com.luoran.zzbird.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.wechat.facade.GzhFacade;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITWechatUserDao;
import com.luoran.zzbird.entity.biz.TWechatUser;
import com.luoran.zzbird.service.ITWechatUserService;

/**
 * @author lifetime
 *
 */
@Service
public class TWechatUserService extends AbstractBaseService<TWechatUser> implements ITWechatUserService {
	@Autowired
	private ITWechatUserDao dao;
	@Resource
	private GzhFacade gzhFacade;

	@Override
	public BaseDao<TWechatUser> getDao() {
		return dao;
	}

	@Override
	public String add(TWechatUser t) {
		return super.add(t);
	}

	@Override
	public boolean saveUserInfo(String openId) {
		gzhFacade.refreshToken();
		String userInfo = gzhFacade.getUserInfo(openId);
		Object parse = JSONObject.parse(userInfo);
		
		System.out.println(userInfo);
		return false;
	}

}