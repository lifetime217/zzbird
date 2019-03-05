package com.luoran.zzbird.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITCompanyDao extends BaseDao<TCompany> {

	public void queryPage(PageQuery<TCompany> pageQuery);

	List<TCompany> queryPointUser();

	TCompany queryCompanyDetail(@Param("companyId") String companyId);

	TXcxUserRole queryCompanyUser(@Param("roleVal") Integer roleVal);
}
