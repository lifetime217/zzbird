package com.luoran.zzbird.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITXcxUserRoleDao;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserRoleService;

/**
 * @author wsl
 *
 */
@Service
public class TXcxUserRoleService extends AbstractBaseService<TXcxUserRole> implements ITXcxUserRoleService {
	@Autowired
	private ITXcxUserRoleDao xcxUserRoleDao;

	@Override
	public BaseDao<TXcxUserRole> getDao() {
		return xcxUserRoleDao;
	}

	@Override
	public String add(TXcxUserRole t) {
		return super.add(t);
	}

	@Override
	public Integer insert(TXcxUserRole t) {
		return super.insert(t);
	}

	@Override
	public List<TXcxUserRole> queryCompanyUser(Integer roleVal, String companyId) {
		return xcxUserRoleDao.queryCompanyUser(roleVal, companyId);
	}

	@Override
	public boolean updateCurrentActiveByZero(String sessionKey) {
		return xcxUserRoleDao.updateCurrentActiveByZero(sessionKey) != 0;
	}

	@Override
	public TXcxUserRole updataUserRole(Map<String, String> params) {
		TXcxUserRole user = new TXcxUserRole();
		user.setId(Integer.parseInt(params.get("roleId")));
		user.setRoleName(params.get("nickName"));
		user.setRoleHeadimg(params.get("imgUrl"));
		int updateTemplateById = xcxUserRoleDao.updateTemplateById(user);
		if (updateTemplateById > 0) {
			TXcxUserRole unique = xcxUserRoleDao.unique(params.get("roleId"));
			return unique;
		}
		return null;
	}
	
	@Override
	public boolean updateActive(Integer id) {
		return xcxUserRoleDao.updateActive(id) != 0;
	}

	
}