package com.luoran.zzbird.service;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.entity.CompanyInfo;

public interface ICompanyService{

	PageQuery<? extends BaseInfo> queryList(PageQuery<? extends BaseInfo> pageQuery);

	//插入一条记录.返回主键,
	public String insertCompany(CompanyInfo companyInfo);

	//根据主键查询
	CompanyInfo getCompanyInfo(String id);

	//根据主键更新
	int updateCompanyInfo(CompanyInfo companyInfo);

	//通过companyName查询数据库
	CompanyInfo checkCompanyInfo(String companyName);



}
