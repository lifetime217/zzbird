package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyDao;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITCompanyService;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.utils.Convert;
import com.luoran.zzbird.utils.GeohashUtil;
import com.luoran.zzbird.utils.ShortUuid;

/**
 * @author wsl
 *
 */
@Service
public class TCompanyService extends AbstractBaseService<TCompany> implements ITCompanyService {

	@Autowired
	private ITCompanyDao companyDao;

	@Autowired
	private ITXcxUserRoleService xcxUserRoleService;

	@Autowired
	private ITXcxUserService xcxUserService;

	@Autowired
	Environment env;

	@Override
	public BaseDao<TCompany> getDao() {
		return companyDao;
	}

	@Override
	public String add(TCompany t) {
		return super.add(t);
	}

	public PageQuery<TCompany> getQueryList(PageQuery<TCompany> pageQuery) {
		companyDao.queryPage(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompany> queryPointUser(String url) {
		List<TCompany> pointUser = companyDao.queryPointUser();
//		JSONArray array = new JSONArray();
//		for (TCompany tCompany : pointUser) {
//			JSONObject obj = new JSONObject();
//			obj.putAll(tCompany.values());
//			obj.put("aaa", tCompany.get("aaa"));
//			array.add(obj);
//		}
		// 拼接url数据
		for (int i = 0; i < pointUser.size(); i++) {
			String companyImg = pointUser.get(i).getBannerImgs();
			pointUser.get(i).setBannerImgs(Convert.convertImgString(companyImg, url));
		}
		return pointUser;
	}

	@Override
	public TCompany queryCompanyDetail(String companyId) {
		return companyDao.queryCompanyDetail(companyId);
	}

	public List<TXcxUserRole> queryCompanyTeacher(String companyId) {
		return xcxUserRoleService.queryCompanyUser(20, companyId);
	}

	@Override
	@Transactional
	public TXcxUserRole addCompany(TCompany company, String sessionKey) {
		UserContextInfo user = UserContext.get();
		// 根据openid查询出用户的信息
		TXcxUser xcxUser = xcxUserService.queryXcxUserByOpenId(user.getOpenid());
		// 添加公司表
		company.set("addTime", new Date());
		company.set("lookCount", 0);
		company.set("shareCount", 0);
		company.set("loveCount", 0);
		company.set("showV1", 0);
		company.set("v1", 0);
		company.set("teacherCount", 0);
		company.set("geohash", GeohashUtil.encode(company.getLat().doubleValue(), company.getLng().doubleValue()));
		company.set("studentCount", 0);
		company.set("sign", ShortUuid.generateShortUuid());
		company.set("xcx_user_id", xcxUser.getId());
		String companyId = add(company);

		// 修改用户上次登录的为0
		xcxUserRoleService.updateCurrentActiveByZero(xcxUser.getId());

		// 添加角色用户表（默认微信的头像和名字 ）
		TXcxUserRole tXcxUserRole = new TXcxUserRole();
		tXcxUserRole.setCompanyId(companyId);
		tXcxUserRole.setRoleVal(10);
		tXcxUserRole.setRoleName(xcxUser.getNickName());
		tXcxUserRole.setRoleHeadimg(xcxUser.getAvatarUrl());
		tXcxUserRole.setXcxUserId(xcxUser.getId());
		tXcxUserRole.setCurrentActive(1);
		tXcxUserRole.setSign(ShortUuid.generateShortUuid());
		tXcxUserRole.setIsdelete(0);
		String xcxUserRoleId = xcxUserRoleService.add(tXcxUserRole);
		// 返回给action重新赋值sesion
		tXcxUserRole.set("openId", xcxUser.getOpenId());
		tXcxUserRole.setId(Integer.parseInt(xcxUserRoleId));;
		tXcxUserRole.set("companyName", company.getCompanyName());
		return tXcxUserRole;
	}

	@Override
	public TCompany getCompanyInfo(Map<String, String> params) {
		TCompany company = companyDao.unique(params.get("companyId"));
		return company;
	}

}