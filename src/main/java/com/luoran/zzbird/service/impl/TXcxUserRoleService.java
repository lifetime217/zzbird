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
public class TXcxUserRoleService extends AbstractBaseService<TXcxUserRole> implements ITXcxUserRoleService{
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

	/**
	 * 根据传入的角色参数来查询公司的用户和老师
	 * <p>Title: queryCompanyUser</p>   
	 * <p>Description: </p>   
	 * @param roleVal
	 * @return   
	 * @see com.luoran.zzbird.service.ITXcxUserRoleService#queryCompanyUser(java.lang.String)
	 */
	@Override
	public List<TXcxUserRole> queryCompanyUser(Integer roleVal,String companyId) {
		// TODO Auto-generated method stub
		return xcxUserRoleDao.queryCompanyUser(roleVal,companyId);
	}

}