package com.luoran.zzbird.service.impl;

import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.dao.ICompanyDao;
import com.luoran.zzbird.dao.IUserDao;
import com.luoran.zzbird.entity.CompanyInfo;
import com.luoran.zzbird.entity.UserInfo;
import com.luoran.zzbird.service.ICompanyService;
import com.luoran.zzbird.utils.MD5;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IUserDao userDao;
	
	//分页
	@Override
	public PageQuery<? extends BaseInfo> queryList(PageQuery<? extends BaseInfo> pageQuery) {
		 companyDao.queryPage(pageQuery);
		 return pageQuery;
	}

	//插入一条数据
	//主键是UUID自动生成
	@Transactional
	@Override
	public String insertCompany(CompanyInfo companyInfo) {
		
		companyDao.insertTemplate(companyInfo);
		//当新增公司成功后.需要默认初始化一个系统管理员
		UserInfo  adminUser = new UserInfo();
		adminUser.setCompanyId(companyInfo.getId());//设置所属公司
		adminUser.setCompanyName(companyInfo.getCompanyName());//公司名称
		adminUser.setCompanyCode(companyInfo.getCompanyCode());//编码
		adminUser.setCreateTime(new Date());
		adminUser.setLoginNo(companyInfo.getAdminAccount());//设置管理员账号
		adminUser.setLoginPwd(MD5.get("123456"));//默认密码
		adminUser.setNick(companyInfo.getContactPeople());//联系人-- 昵称
		//adminUser.setRole("系统管理员");
		adminUser.setStatus(0); 
		adminUser.setUserType(1);
		
		//新增管理员
		userDao.insertTemplate(adminUser);
		
		return adminUser.getId();//返回新增的用户主键
		
	}

	//根据主键查询
	@Transactional
	@Override
	public CompanyInfo getCompanyInfo(String id) {
		
		return companyDao.single(id);
	}

	//根据主键更新
	@Transactional
	@Override
	public int updateCompanyInfo(CompanyInfo companyInfo) {
		
		return companyDao.updateTemplateById(companyInfo);
	}

	//公司名称查询数据库
	@Transactional
	@Override
	public CompanyInfo checkCompanyInfo(String companyName) {
		
		return companyDao.selectCompanyInfoByCompanyName(companyName);
	}


}
