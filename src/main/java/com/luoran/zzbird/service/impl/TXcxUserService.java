package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITXcxUserDao;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.service.IWechatUserRelationService;

/**
 * @author wsl
 *
 */
@Service
public class TXcxUserService extends AbstractBaseService<TXcxUser> implements ITXcxUserService {
	private static final Logger logger = LoggerFactory.getLogger(TXcxUserService.class);

	@Autowired
	private ITXcxUserDao userDao;

	@Autowired
	private IWechatUserRelationService wechatUserRelationService;

	@Override
	public BaseDao<TXcxUser> getDao() {
		return userDao;
	}

	@Override
	public String add(TXcxUser t) {
		return super.add(t);
	}

	@Override
	public TXcxUser queryXcxUserByOpenId(String openId) {
		return userDao.queryXcxUserByOpenId(openId);
	}

	@Override
	public List<TXcxUser> queryXcxUserByNickName(String nickName) {
		return userDao.queryXcxUserByNickName(nickName);
	}

	@Override
	public String addUser(TXcxUser xcxUser, String zzbird_XcxSessionKey) {
		UserContextInfo user = UserContext.get();
		xcxUser.setOpenId(user.getOpenid());
		xcxUser.setSessionKey(zzbird_XcxSessionKey);
		xcxUser.setAddTime(new Date());

		TXcxUser xcxUserByOpenId = queryXcxUserByOpenId(user.getOpenid());
		if (xcxUserByOpenId != null) {
			return xcxUserByOpenId.getId();
		}
		String xcxUserId = add(xcxUser);
		wechatUserRelationService.notifyAddXcxUser(user.getOpenid());
		return xcxUserId;
	}

	@Override
	public List<TXcxUserRole> queryAllRoleUser(String sessionKey) {
		return userDao.queryAllRoleUser(sessionKey);
	}

	@Override
	public TXcxUserRole queryActiveUserRole(String sessionKey) {
		return userDao.queryAllRoleUser(sessionKey, 1);
	}

	@Override
	public void initSession(String sessionKey, String openId) {
		UserContextInfo user = SessionManager.get(sessionKey);
		if (user == null) {
			// 判断是否已授权，不为空则代表已授权，为空则为游客
			TXcxUserRole userRoleVo = queryActiveUserRole(sessionKey);
			if (userRoleVo == null) {
				// 游客只记录openid
				SessionManager.init(sessionKey, openId);
			} else {
				// 已授权的用户则初始化用户context
				SessionManager.init(sessionKey, new UserContextInfo(userRoleVo));
			}
		}

	}

	@Override
	public void reinitSession(String sessionKey) {
		// sessionKey不为空
		UserContextInfo user = SessionManager.get(sessionKey);
		// 为空 查询数据库
		if (user == null) {
			// 判断正在使用的角色
			TXcxUserRole userRoleVo = queryActiveUserRole(sessionKey);
			if (userRoleVo != null) {
				SessionManager.init(sessionKey, new UserContextInfo(userRoleVo));
			} else {
				logger.error("用户session不存在");
			}
		}

	}

	public void reloadSession(String sessionKey) {
		// 判断正在使用的角色
		TXcxUserRole userRoleVo = queryActiveUserRole(sessionKey);
		if (userRoleVo != null) {
			UserContext.clear();
			SessionManager.init(sessionKey, new UserContextInfo(userRoleVo));
			UserContext.init(SessionManager.get(sessionKey));
			System.out.println("用户更新session==============" + UserContext.get().toString());
		} else {
			logger.error("用户session不存在");
		}
	}

	@Override
	public TXcxUserRole queryXcxUserRole(String sessionKey, String roleVal, String companyId) {
		return userDao.queryXcxUserRole(sessionKey, roleVal, companyId);
	}

}