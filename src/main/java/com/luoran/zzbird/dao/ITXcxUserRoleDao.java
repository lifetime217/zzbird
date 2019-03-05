package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITXcxUserRoleDao extends BaseDao<TXcxUserRole> {

	public void queryPage(PageQuery<TXcxUserRole> pageQuery);

	List<TXcxUserRole> queryCompanyUser(@Param("roleVal") Integer roleVal,@Param("companyId")String companyId);
}
