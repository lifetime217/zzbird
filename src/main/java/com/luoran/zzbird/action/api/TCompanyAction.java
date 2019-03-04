package com.luoran.zzbird.action.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.service.ITCompanyService;
import com.luoran.zzbird.utils.Convert;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("company")
public class TCompanyAction implements BaseAction<TCompany> {

	private final static Logger log = LoggerFactory.getLogger(TCompanyAction.class);

	@Autowired
	private ITCompanyService companyService;

	@Autowired
	Environment env;

	@RequestMapping
	public String index() {
		return "tcompany";
	}

	@Override
	public IBaseService<TCompany> getService() {
		return companyService;
	}

	/**
	 * 
	 * @Author wsl
	 * @Title: get
	 * @Description: TODO 查询公司列表的分页 利用showV1 来区分查询 （1代表重点客户 0代表普通用户）
	 * @param: @return @return: HttpResult @throws
	 */
	@RequestMapping("/queryCompanyPage")
	@ResponseBody()
	public HttpResult queryCompanyPage(@RequestParam(value = "search") String search,
			@RequestParam(value = "page") String page) {
		JSONObject res = new JSONObject();
		// TODO 定位查询
		try {
			// 拿到图片的访问地址
			String url = env.getProperty("file.path.url");

			// 查询重点客户
			List<TCompany> pointUser = companyService.queryPointUser(url);
			res.put("pointUser", pointUser);

			// 查询普通用户
			Map<String, String> queryParams = new HashMap<>();
			if (!StringUtils.isEmpty(page)) {
				queryParams.put("page", page);
			}
			// 如果没有传递搜索条件就查询普通用户，如果有搜索条件就查询全部的数据
			if (!StringUtils.isEmpty(search)) {
				queryParams.put("search", search);
			} else {
				queryParams.put("showV1", "0");
			}
			JSONObject ordinaryUser = listQuery(queryParams);
			// 拼接图片url
			@SuppressWarnings("unchecked")
			List<TCompany> companyList = (List<TCompany>) ordinaryUser.get("list");
			for (int i = 0; i < companyList.size(); i++) {
				String companyImg = companyList.get(i).getBannerImgs();
				companyList.get(i).setBannerImgs(Convert.convertImgString(companyImg, url));
			}
			res.put("ordinaryUser", ordinaryUser);

			// 首页的头部轮播图
			List<String> bannersList = new ArrayList<String>();
			bannersList.add(url + "/fengjing.jpg");
			res.put("bannerList", bannersList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);
	}
	
	@RequestMapping("/query")
	@ResponseBody()
	public void  tets() {
		System.out.println("niaho");
	}

	

	/**
	 * 
	 * @Author wsl @Title: queryNewOrOldUser @Description: TODO
	 *         查询新老用户(根据公司用户表是否有数据) @param: @return @return: HttpResult @throws
	 */
	@RequestMapping("/queryNewOrOldUser")
	@ResponseBody()
	public HttpResult queryNewOrOldUser(@RequestParam(value = "code") String code) {
		log.info("code:" + code + ",获取用户角色id");
		return null;
	}

	/**
	 * 
	 * @Author wsl @Title: queryCompanyDetail @Description: TODO
	 *         查詢公司详情页 @param: @return @return: HttpResult @throws
	 */
	@RequestMapping("/queryCompanyDetail")
	@ResponseBody()
	public HttpResult queryCompanyDetail(@RequestParam(value = "companyId") String companyId) {
		JSONObject res = new JSONObject();
		try {
			res = companyService.queryCompanyDetail(companyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);

	}

}
