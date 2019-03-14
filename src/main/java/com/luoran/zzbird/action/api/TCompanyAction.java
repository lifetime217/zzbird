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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.entity.vo.UserRoleVo;
import com.luoran.zzbird.service.ITCompanyCourseService;
import com.luoran.zzbird.service.ITCompanyService;
import com.luoran.zzbird.utils.Convert;
import com.luoran.zzbird.utils.GeohashUtil;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("api/company")
public class TCompanyAction implements BaseAction<TCompany> {

	private final static Logger log = LoggerFactory.getLogger(TCompanyAction.class);

	@Autowired
	private ITCompanyService companyService;
	
	@Autowired
	private ITCompanyCourseService companyCourseService;

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
	 * @Description:   查询公司列表的分页 利用showV1 来区分查询 （1代表重点客户 0代表普通用户）
	 * @params lat 纬度 lng经度
	 */
	@RequestMapping("/queryCompanyPage")
	@ResponseBody()
	public HttpResult queryCompanyPage(@RequestParam(value = "search") String search,
			@RequestParam(value = "page") String page,
			@RequestParam(value = "latitude", required = false, defaultValue = "0.0d") double latitude,
			@RequestParam(value = "longitude", required = false, defaultValue = "0.0d") double longitude) {
		JSONObject res = new JSONObject();
		//   定位查询
		try {

			// 拿到图片的访问地址
			String url = env.getProperty("file.path.url");
			// 查询重点客户
			List<TCompany> pointUser = companyService.queryPointUser(url);
			res.put("pointUser", pointUser);

			// 设置分页的查询条件
			// 查询普通用户
			Map<String, Object> queryParams = new HashMap<>();
			// 如果没有传递搜索条件就查询普通用户，如果有搜索条件就查询全部的数据
			if (!StringUtils.isEmpty(search)) {
				queryParams.put("search", search);
			} else {
				queryParams.put("showV1", "0");
			}
			if (latitude != 0.0d && longitude != 0.0d) {
				List<String> geohashList = GeohashUtil.encodes(latitude, longitude, 6);
				queryParams.put("geohashList", geohashList);
			}

			// 分页查询
			PageQuery<TCompany> pageQuery = new PageQuery<TCompany>();
			pageQuery.setPageNumber(StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page));
			pageQuery.setPageSize(10);
			pageQuery.setParas(queryParams);
			JSONObject ordinaryUser = pager(companyService.getQueryList(pageQuery));
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
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);
	}

	/**
	 * 
	 * @Author wsl
	 * @Description:   新建企业
	 */
	@RequestMapping("/addCompany")
	@ResponseBody()
	public HttpResult addCompany(TCompany company, String zzbird_XcxSessionKey) {
		JSONObject res = new JSONObject();
		try {
			//添加公司
			TXcxUserRole userRole = companyService.addCompany(company, zzbird_XcxSessionKey);
			// 重新赋值sessionmanager
			SessionManager.init(zzbird_XcxSessionKey, new UserContextInfo(userRole));
			res.put("companyId",userRole.getCompanyId());
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("新增失败");
		}
		return HttpResult.success("新增成功", res);
	}


	/**
	 * 
	 * @Author wsl
	 * @Description:   查詢公司详情页
	 */
	@RequestMapping("/queryCompanyDetail")
	@ResponseBody()
	public HttpResult queryCompanyDetail(@RequestParam(value = "companyId") String companyId) {
		JSONObject res = new JSONObject();
		try {
			String url = env.getProperty("file.path.url");
			TCompany companyDetail = companyService.queryCompanyDetail(companyId);
			// 拼接图片转换成list
			res.put("bannerList", Convert.convertImgList(companyDetail.getBannerImgs(), url));
			res.put("industry", Arrays.asList(companyDetail.getIndustryListName().split(",")));
			res.put("companyDetail", companyDetail);
			// 查询老师的数组
			List<TXcxUserRole> queryCompanyTeacher = companyService.queryCompanyTeacher(companyId);
			res.put("companyTeacher", queryCompanyTeacher);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);

	}

	/**
	 * 
	 * @Author wsl
	 * @Description:   根据企业id查询企业的基本信息
	 */
	@RequestMapping(value = "/queryCompanyByCompanyId", method = RequestMethod.GET)
	@ResponseBody()
	public HttpResult queryCompanyDetailByCompanyId() {
		JSONObject res = new JSONObject();
		try {
			UserContextInfo user = UserContext.get();
			String companyId = user.getCompanyId();
			TCompany company = companyService.queryCompanyDetail(companyId);
			List<String> industryListId = Arrays.asList(company.getIndustryListId().split(",")); // 标签的id
			List<String> industryListName = Arrays.asList(company.getIndustryListName().split(","));// 标签的name
			// 拼接标签的集合
			JSONArray industry = new JSONArray();
			for (int i = 0; i < industryListId.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", industryListId.get(i));
				obj.put("tagName", industryListName.get(i));
				industry.add(obj);
			}
			res.put("company", company);
			res.put("industry", industry);
			// 拿到图片的访问地址
			String url = env.getProperty("file.path.url");
			List<String> bannerImgsUrl = Arrays
					.asList(Convert.convertImgString(company.getBannerImgs(), url).split(","));
			List<String> bannerImgsName = Arrays.asList(company.getBannerImgs().split(","));
			// 拼接图片的集合
			JSONArray bannerList = new JSONArray();
			for (int i = 0; i < bannerImgsUrl.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("url", bannerImgsUrl.get(i));
				obj.put("name", bannerImgsName.get(i));
				bannerList.add(obj);
			}
			res.put("banner", bannerList);// banner图片集合
			res.put("bannerImgsName", bannerImgsName);// banner图片名称
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);
	}

	/**
	 * 
	 * @Author wsl
	 * @Description:   修改企业
	 */
	@RequestMapping("/updateCompany")
	@ResponseBody()
	public HttpResult updateCompany(TCompany company) {
		JSONObject res = new JSONObject();
		try {
			UserContextInfo user = UserContext.get();
			company.setId(user.getCompanyId());
			companyService.save(company);
			res.put("companyId", user.getCompanyId());
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("修改失败");
		}
		return HttpResult.success("修改成功", res);

	}
	/**
	 * @author tzx
	 * @Description 查询个人中心企业相关信息
	 * 
	 */
	@RequestMapping("/selectCompany")
	@ResponseBody()
	public HttpResult selectCompany(@RequestParam Map<String, String> params) {
		HttpResult hr = new HttpResult();
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		params.put("companyId", userContextInfo.getCompanyId());
		JSONObject data = new JSONObject();
		try {
			//封装角色信息
			data.put("role", userContextInfo);
			TCompany companyInfo = companyService.getCompanyInfo(params);
			data.put("lookCount", companyInfo.getLookCount());
			data.put("shareCount", companyInfo.getShareCount());
			Integer courseCount = companyCourseService.getCourseCount(params);
			data.put("courseCount", courseCount);
			System.out.println("------------------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr ;
		
	}
	
}
