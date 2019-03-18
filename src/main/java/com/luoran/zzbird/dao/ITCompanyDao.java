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

	/**
	 * 
	 * @Author wsl  
	 * @Description: 查询公司详情
	 */
	TCompany queryCompanyDetail(@Param("companyId") String companyId);

	
	/**
	 * 
	 * @Author wsl  
	 * @Description: 根据flag 修改公司的老师或者学生人数  0修改老师 1修改 学生
	 */
	Integer updateCompanyPersonNumber(String id, Integer flag);
}
