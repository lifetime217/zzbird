package com.luoran.zzbird.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyDao;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.service.ITCompanyService;


/**
 * @author lifetime
 *
 */
@Service
public class TCompanyService extends AbstractBaseService<TCompany> implements ITCompanyService{
	
	@Autowired
	private ITCompanyDao companyDao;

	@Override
	public BaseDao<TCompany> getDao() {
		return companyDao;
	}
	
	@Override
	public String add(TCompany t) {
		return super.add(t);
	}
//	/**
//	 * 公司列表分页查询数据
//	 * <p>Title: getQueryList</p>   
//	 * <p>Description: </p>   
//	 * @param pageQuery
//	 * @return   
//	 * @see com.luoran.zzbird.core.ext.AbstractBaseService#getQueryList(org.beetl.sql.core.engine.PageQuery)
//	 */
//	public PageQuery<TCompany> getQueryList(PageQuery<TCompany> pageQuery){
//		dao.queryCompanyPage(pageQuery);
//		return pageQuery;
//	}

	/**
	 * 查询公司重点用户
	 * <p>Title: queryPointUser</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.luoran.zzbird.service.ITCompanyService#queryPointUser()
	 */
	@Override
	public List<TCompany> queryPointUser() {
		return companyDao.queryPointUser();
	}

}