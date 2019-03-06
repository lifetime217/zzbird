package com.luoran.zzbird.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
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
	 * @Description: TODO 查询公司重点用户
	 */
	List<TCompany> queryPointUser(String url);

	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 查询公司的详情
	 */
	TCompany queryCompanyDetail(String companyId);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 查询公司的老师 
	 */
	List<TXcxUserRole> queryCompanyTeacher(String companyId);
	
	
}
