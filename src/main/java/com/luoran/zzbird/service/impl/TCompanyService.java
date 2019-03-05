package com.luoran.zzbird.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyDao;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.entity.vo.CompanyDetailVo;
import com.luoran.zzbird.service.ITCompanyService;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.utils.Convert;

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
	Environment env;

	@Override
	public BaseDao<TCompany> getDao() {
		return companyDao;
	}

	@Override
	public String add(TCompany t) {
		return super.add(t);
	}

	/**
	 * 查询公司重点用户
	 * <p>
	 * Title: queryPointUser
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see com.luoran.zzbird.service.ITCompanyService#queryPointUser()
	 */
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

	/**
	 * 查询公司的详情
	 * <p>
	 * Title: queryCompanyDetail
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see com.luoran.zzbird.service.ITCompanyService#queryCompanyDetail()
	 */
	@Override
	public JSONObject queryCompanyDetail(String companyId) {
		JSONObject res = new JSONObject();
		// 拿到图片的访问地址
		String url = env.getProperty("file.path.url");
		TCompany companyDetail = companyDao.queryCompanyDetail(companyId);
		// 拼接图片转换成list
		res.put("bannerList", Convert.convertImgList(companyDetail.getBannerImgs(), url));
		res.put("industry", Arrays.asList(companyDetail.getIndustryListName().split(",")));
		res.put("companyDetail", companyDetail);
		return res;
	}

	/**
	 * 
	 * @Author wsl @Title: queryCompanyTeacher @Description: TODO
	 * 查询公司的老师 @param: @param companyId @param: @return @return:
	 * List<TXcxUserRole> @throws
	 */
	public List<TXcxUserRole> queryCompanyTeacher(String companyId) {
		return xcxUserRoleService.queryCompanyUser(20, companyId);
	}

}