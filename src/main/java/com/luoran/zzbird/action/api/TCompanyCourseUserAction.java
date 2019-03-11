package com.luoran.zzbird.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.service.ITCompanyCourseUserService;

/**
 * @author tzx
 *
 */
@Controller
@RequestMapping("api/companycourseuser")
public class TCompanyCourseUserAction implements BaseAction<TCompanyCourseUser> {

	@Autowired
	private ITCompanyCourseUserService service;

	@RequestMapping
	public String index() {
		return "tcompanycourseuser";
	}

	@Override
	public IBaseService<TCompanyCourseUser> getService() {
		return service;
	}

	/**
	 * @author tzx
	 * @Description: TODO企业查课程下的用户
	 */
	@RequestMapping("queryCompanyUser")
	@ResponseBody()
	public HttpResult getCompanyUser(@RequestParam Map<String, String> params) {
		HttpResult hr = new HttpResult();
		JSONArray data = new JSONArray();
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setMsg("后台roleId未传入");
			hr.setStatusCode(100);
			return hr;
		}
		if (StringUtils.isEmpty(params.get("page"))) {
			hr.setMsg("page未传入");
			hr.setStatusCode(100);
			return hr;
		}
		// 分页查询
		PageQuery<TCompanyCourseUser> pageQuery = new PageQuery<TCompanyCourseUser>();
		// 存入分页参数
		pageQuery.setPageNumber(Integer.parseInt(params.get("page")));
		pageQuery.setPageSize(10);
		pageQuery.setParas(params);
		PageQuery<TCompanyCourseUser> userQueryPage = new PageQuery<TCompanyCourseUser>();
		try {
			// 返回学生的集合
			userQueryPage = service.getComUserByBoosRole(pageQuery);
			List<TCompanyCourseUser> userlist = userQueryPage.getList();
			// 循环塞入jsonArr
			for (TCompanyCourseUser user : userlist) {
				JSONArray jACourseList = new JSONArray();
				JSONObject jUser = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", user.getString("userRoleid"));
				jUser.putAll(user.values());
				// 查出学生对应的课程
				List<TCompanyCourseUser> courseList = service.getCourseByUserRoleId(map);
				for (TCompanyCourseUser course : courseList) {
					// 存入课程集合
					JSONObject jCourse = new JSONObject();
					jCourse.putAll(course.values());
					jACourseList.add(jCourse);
				}
				jUser.put("courseList", jACourseList);
				data.add(jUser);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			hr.setMsg("服务器错误");
			hr.setStatusCode(500);
			return hr;
		}
		//存入分页参数
		hr.setPageSize(userQueryPage.getPageSize());
		hr.setTotalPage(userQueryPage.getTotalPage());
		hr.setPage(userQueryPage.getPageNumber());
		hr.setTotalRow(userQueryPage.getTotalRow());
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}

}
