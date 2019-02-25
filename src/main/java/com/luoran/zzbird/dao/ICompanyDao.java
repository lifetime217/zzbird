package com.luoran.zzbird.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.entity.CompanyInfo;

public interface ICompanyDao extends BaseMapper<CompanyInfo> {

	void queryPage(PageQuery<? extends BaseInfo> pageQuery);

	CompanyInfo selectCompanyInfoByCompanyName(@Param("companyName")String companyName);

}
