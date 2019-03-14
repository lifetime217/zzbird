package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TXcxUserRole;

/**
 * @author wsl
 *
 */
public interface ITCompanyService extends IBaseService<TCompany> {

	/**
	 * 
	 * @Author wsl  
	 * @Description:  查询公司重点用户
	 */
	List<TCompany> queryPointUser(String url);
	
	TXcxUserRole addCompany(TCompany company, String sessionKey);
	
	/**
	 * 
	 * @Title: getQueryList
	 * @Description:重写分页查询的servcie
	 */
	PageQuery<TCompany> getQueryList(PageQuery<TCompany> pageQuery);

	/**
	 * 
	 * @Author wsl  
	 * @Description:  查询公司的详情
	 */
	TCompany queryCompanyDetail(String companyId);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:  查询公司的老师 
	 */
	List<TXcxUserRole> queryCompanyTeacher(String companyId);
	/**
	 * 
	 * @Author tzx  
	 * @Description:  查询公司信息
	 * @param #companyId: 公司的Id
	 */
	TCompany getCompanyInfo(Map<String, String> params);
	
	
}
