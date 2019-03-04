package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserRoleService extends IBaseService<TXcxUserRole> {

	List<TXcxUserRole> queryCompanyUser(Integer roleVal,String companyId);
}
