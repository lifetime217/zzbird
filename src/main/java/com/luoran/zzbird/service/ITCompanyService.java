package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.UserContextInfo;
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
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:用户添加公司
	 */
	TXcxUserRole addCompany(TCompany company, String sessionKey,String imgs);
	
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:分页查询用户的数据
	 */
	PageQuery<TCompany> getQueryList(String page,Map<String, Object> queryParams);

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
	
	/**
	 * 
	 * @Author wsl  
	 * @Description: 根据flag 修改公司的老师或者学生人数  0修改老师 1修改 学生
	 */
	boolean updateCompanyPersonNumber(String id, Integer flag);
	/**
	 * 
	 * @Author tzx  
	 * @Description:根据公司 id 和userRoleId 累加公司的查看次数，如果是公司创建者查看，则累加
	 */
	void updateLookCount(Integer xcxUserRoleId, TCompany oldCompany);
	/**
	 * 
	 * @Author tzx  
	 * @Description:根据公司 id 和userRoleId 累加公司的分享次数，如果是公司创建者分享，则累加
	 */
	boolean updateShareCount(Integer roleId, String string);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:修改公司
	 */
	String updateCompany(TCompany company,String imgs);
}
