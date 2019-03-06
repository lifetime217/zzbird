package com.luoran.zzbird.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
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
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.service.ITCompanyCourseUserService;

/**
 * @author tzx
 *
 */
@Controller
@RequestMapping("companycourseuser")
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
	 * 企业查课程下的学生
	 */
	@RequestMapping("queryCompanyStudent")
	@ResponseBody()
	public HttpResult getCompanyStudent(@RequestParam Map<String, String> params) {
		HttpResult hr = new HttpResult();
		JSONArray data = new JSONArray();
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setMsg("roleId未传入");
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
		PageQuery<TCompanyCourseUser> stuQueryPage = new PageQuery<TCompanyCourseUser>();
		try {
			// 返回学生的集合
			stuQueryPage = service.getComStudentByBoosRole(pageQuery);
			List<TCompanyCourseUser> stulist = stuQueryPage.getList();
			// 循环塞入jsonArr
			for (TCompanyCourseUser stu : stulist) {
				JSONArray jACourseList = new JSONArray();
				JSONObject jStu = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", stu.getString("stuRoleid"));
				jStu.putAll(stu.values());
				// 查出学生对应的课程
				List<TCompanyCourseUser> courseList = service.getCourseByStuRoleId(map);
				for (TCompanyCourseUser course : courseList) {
					// 存入课程集合
					JSONObject jCourse = new JSONObject();
					jCourse.putAll(course.values());
					jACourseList.add(jCourse);
				}
				jStu.put("courseList", jACourseList);
				data.add(jStu);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		hr.setPageSize(stuQueryPage.getPageSize());
		hr.setTotalPage(stuQueryPage.getTotalPage());
		hr.setPage(stuQueryPage.getPageNumber());
		hr.setTotalRow(stuQueryPage.getTotalRow());
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}
	
	
	
	/**
	 * 企业查课程下的老师
	 */
	@RequestMapping("queryCompanyTeacher")
	@ResponseBody()
	public HttpResult getCompanyTeacher(@RequestParam Map<String, String> params) {
		HttpResult hr = new HttpResult();
		JSONArray data = new JSONArray();
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setMsg("roleId未传入");
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
		PageQuery<TCompanyCourseUser> teaQueryPage = new PageQuery<TCompanyCourseUser>();
		try {
			// 返回学生的集合
			teaQueryPage = service.getComTeacherByBoosRole(pageQuery);
			List<TCompanyCourseUser> tealist = teaQueryPage.getList();
			// 循环塞入jsonArr
			for (TCompanyCourseUser tea : tealist) {
				JSONArray jACourseList = new JSONArray();
				JSONObject jTea = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleId", tea.getString("teaRoleid"));
				jTea.putAll(tea.values());
				// 查出学生对应的课程
				List<TCompanyCourseUser> courseList = service.getCourseByTeaRoleId(map);
				for (TCompanyCourseUser course : courseList) {
					// 存入课程集合
					JSONObject jCourse = new JSONObject();
					jCourse.putAll(course.values());
					jACourseList.add(jCourse);
				}
				jTea.put("courseList", jACourseList);
				data.add(jTea);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		hr.setPageSize(teaQueryPage.getPageSize());
		hr.setTotalPage(teaQueryPage.getTotalPage());
		hr.setPage(teaQueryPage.getPageNumber());
		hr.setTotalRow(teaQueryPage.getTotalRow());
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}
}
