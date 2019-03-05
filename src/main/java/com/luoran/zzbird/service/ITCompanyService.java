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

	List<TCompany> queryPointUser(String url);
	
	JSONObject queryCompanyDetail(String companyId);
	
	List<TXcxUserRole> queryCompanyTeacher(String companyId);
}
