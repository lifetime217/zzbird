package com.luoran.zzbird.service.impl;

import java.util.List;

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
	public boolean updateActive(Integer id) {
		return xcxUserRoleDao.updateActive(id) != 0;
	}

}