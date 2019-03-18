package com.luoran.zzbird.action.api;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.service.ITCompanyCourseUserService;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * @author tzx
 *
 */
@Controller
@RequestMapping("api/companycourseuser")
public class TCompanyCourseUserAction implements BaseAction<TCompanyCourseUser> {
	private final static Logger log = LoggerFactory.getLogger(TCompanyCourseUserAction.class);

	@Autowired
	private ITCompanyCourseUserService companyCourseUserService;

	@Autowired
	private ITDakaRecordService dakaRecordService;

	@Autowired
	Environment env;

	@RequestMapping
	public String index() {
		return "tcompanycourseuser";
	}

	@Override
	public IBaseService<TCompanyCourseUser> getService() {
		return companyCourseUserService;
	}

	/**
	 * @author tzx
	 * @Description: 企业查课程下的用户
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
			// 返回用户的集合
			userQueryPage = companyCourseUserService.getComUserByBoosRole(pageQuery);
			List<TCompanyCourseUser> userlist = userQueryPage.getList();
			// 循环塞入jsonArr
			for (TCompanyCourseUser user : userlist) {
				JSONArray jACourseList = new JSONArray();
				JSONObject jUser = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", user.getString("userRoleid"));
				jUser.putAll(user.values());
				// 查出用户对应的课程
				List<TCompanyCourseUser> courseList = companyCourseUserService.getCourseByUserRoleId(map);
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
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("服务器错误");
			hr.setStatusCode(500);
			return hr;
		}
		// 存入分页参数
		hr.setPageSize(userQueryPage.getPageSize());
		hr.setTotalPage(userQueryPage.getTotalPage());
		hr.setPage(userQueryPage.getPageNumber());
		hr.setTotalRow(userQueryPage.getTotalRow());
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: 查询用户角色下的公司课程
	 */
	@RequestMapping("queryCourseByUser")
	@ResponseBody()
	public HttpResult queryCourseByUser() {
		JSONObject res = new JSONObject();
		try {
			List<TCompanyCourse> courseList = companyCourseUserService.queryCourseList();
			String url = env.getProperty("file.path.url");
			JSONArray json = new JSONArray();
			for (TCompanyCourse course : courseList) {
				JSONObject jsonObject = new JSONObject();
				course.setCourseImg(url + "/" + course.getCourseImg());
				jsonObject.putAll(course.values());
				json.add(jsonObject);
			}
			res.put("courseList", json);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败!");
		}
		return HttpResult.success("查询成功!", res);
	}

	/**
	 * 
	 * @Author tzx
	 * @Description:查询个人中心老师相关信息
	 */
	@RequestMapping("/selectTeacher")
	@ResponseBody()
	public HttpResult selectCompany(@RequestParam Map<String, String> params) {
		HttpResult hr = new HttpResult();
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		JSONObject data = new JSONObject();
		try {
			// 封装角色信息
			data.put("role", userContextInfo);
			// 初始化课程总数，学生总数，总课时
			data.put("courseCount", 0);
			data.put("stuCount", 0);
			data.put("totalClassHour", 0);
			// 查询课程总数，学生总数，总课时
			Integer courseCount = companyCourseUserService.getUserCourseCount(params);
			Integer stuCount = companyCourseUserService.getTeaCourseStuCount(params);
			Integer userClassHour = dakaRecordService.queryUserClassHour();
			// 封装课程总数，学生总数，总课时 判断查询的数据不是空的时候
			if (courseCount != null) {
				data.put("courseCount", courseCount);
			}
			if (stuCount != null) {
				data.put("stuCount", stuCount);
			}
			if (userClassHour != null) {
				data.put("totalClassHour", userClassHour);
			}
			System.out.println("------------------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", data);
	}

	/**
	 * 
	 * @Author tzx
	 * @Description:查询个人中心学生相关信息
	 */
	@RequestMapping("/selectStudent")
	@ResponseBody()
	public HttpResult selectStudent(@RequestParam Map<String, String> params) {
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		JSONObject data = new JSONObject();
		try {
			// 封装角色信息
			data.put("role", userContextInfo);
			// 初始化课程数，本月课时，累计课时
			data.put("courseCount", 0);
			data.put("monthClassHourt", 0);
			data.put("totalClassHour", 0);
			// 查询课程数，本月课时，累计课时
			Integer courseCount = companyCourseUserService.getUserCourseCount(params);
			Integer classHourthIsMonth = dakaRecordService.getClassHourthIsMonth(params);
			Integer userClassHour = dakaRecordService.queryUserClassHour();
			// 封装课程数，本月课时，累计课时 封装时判断不为空
			if (courseCount != null) {
				data.put("courseCount", courseCount);
			}
			if (classHourthIsMonth != null) {
				data.put("monthClassHourt", classHourthIsMonth);
			}
			if (userClassHour != null) {
				data.put("totalClassHour", userClassHour);
			}
			System.out.println("------------------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", data);
	}

	/**
	 * 
	 * @Author tzx
	 * @Description:查询老师下的学生
	 */
	@RequestMapping("/selectTeaCourseStu")
	@ResponseBody()
	public HttpResult selectTeaCourseStu(@RequestParam Map<String, String> params) {
		UserContextInfo userContextInfo = UserContext.get();

		// 存入roleId到参数map
		params.put("roleId", userContextInfo.getXcxUserRoleId().toString());
		if (StringUtils.isEmpty(params.get("page"))) {
			return HttpResult.fail("前台page未传入");
		}
		// 初始化jarr
		JSONArray data = new JSONArray();
		// 放入查询的page页
		PageQuery<TCompanyCourseUser> pageQuery = new PageQuery<TCompanyCourseUser>(Integer.parseInt(params.get("page")),10,params);
		// 查询出来的Page页
		PageQuery<TCompanyCourseUser> userQueryPage = null;
		try {
			JSONArray js = new JSONArray();
			userQueryPage = companyCourseUserService.getTeaCourseStu(pageQuery);
			List<TCompanyCourseUser> userlist = userQueryPage.getList();
			for (TCompanyCourseUser user : userlist) {
				JSONArray jACourseList = new JSONArray();
				JSONObject jUser = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", user.getString("userRoleid"));
				jUser.putAll(user.values());
				List<TCompanyCourseUser> courseList = companyCourseUserService.getCourseByUserRoleId(map);
				for (TCompanyCourseUser course : courseList) {
					// 存入课程集合
					JSONObject jCourse = new JSONObject();
					jCourse.putAll(course.values());
					jACourseList.add(jCourse);
				}
				jUser.put("courseList", jACourseList);
				data.add(jUser);
			}
			System.out.println("--------------------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", data, userQueryPage.getPageNumber(), userQueryPage.getPageSize(), userQueryPage.getTotalRow(),userQueryPage.getTotalPage());
	}

}
