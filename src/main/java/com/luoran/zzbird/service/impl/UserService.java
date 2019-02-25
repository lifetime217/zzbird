package com.luoran.zzbird.service.impl;

import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.IUserDao;
import com.luoran.zzbird.entity.UserInfo;
import com.luoran.zzbird.service.IUserService;
import com.luoran.zzbird.utils.MD5;

@Service
public class UserService extends AbstractBaseService<UserInfo> implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao userDao;

	public UserInfo getUser(String number, String pwd) {
		return userDao.userLogin(number, pwd);
	}

	/**
	 * 是否是有效的原始密码
	 * 
	 * @param number
	 *            账号
	 * @param pwd
	 *            未加密过的密码
	 * @return
	 */
	public boolean pwdSuccess(String number, String pwd) {
		UserInfo info = userDao.getInfoByNumber(number);
		if (info != null && !StringUtils.isEmpty(info.getLoginPwd())) {
			return info.getLoginPwd().equalsIgnoreCase(MD5.get(pwd));
		}
		return false;
	}

	public void modifyPwd(String number, String newPwd) {
		userDao.modifyPwd(number, MD5.get(newPwd));
	}

	public UserInfo getInfoByNumber(String number) {
		return userDao.getInfoByNumber(number);
	}

	public UserInfo getUser(String id) {
		return userDao.getInfo(id);
	}

	// 分页
	@Override
	public PageQuery<UserInfo> getQueryList(PageQuery<UserInfo> pageQuery) {
		Map<String, Object> map = (Map<String, Object>) pageQuery.getParas();
		map.put("company_id", UserContext.get().getCompanyId());// 添加查询参数（公司ID）
		map.put("isSystemUser", UserContext.get().isAdmin());
		if (StringUtils.isEmpty(pageQuery.getOrderBy())) {
			pageQuery.setOrderBy("create_time DESC");
		}
		userDao.queryPage(pageQuery);
		return pageQuery;
	}

	@Override
	public UserInfo checkUserName(String loginNo) {
		return userDao.selectUserByLoginNo(loginNo);
	}

	@Override
	public BaseDao<UserInfo> getDao() {
		return userDao;
	}

}
