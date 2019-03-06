package com.luoran.zzbird.action.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.vo.DakaDetailByMonthVo;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * 打卡操作接口
 * 
 * @author tzx statusCode ：100 接口参数获取失败，200 成功 ，500 后台错误 ，400 找不到对应的值
 */

@Controller
@RequestMapping("dakarecord")
public class TDakaRecordAction implements BaseAction<TDakaRecord> {

	@Autowired
	private ITDakaRecordService service;

	@RequestMapping
	public String index() {
		return "tdakarecord";
	}

	@Override
	public IBaseService<TDakaRecord> getService() {
		return service;
	}

	/**
	 * 用于查询按时间查询的
	 * 
	 * @param params
	 * @param req
	 * @return
	 */
	@RequestMapping("queryPunchListByMonth")
	@ResponseBody
	public HttpResult getPunchListByMonth(@RequestParam Map<String, String> params, HttpServletRequest req) {
		HttpResult hr = new HttpResult();
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setStatusCode(100);
			hr.setMsg("roleId传入为空");
			return hr;
		}
		if (StringUtils.isEmpty(params.get("statDate"))) {
			hr.setStatusCode(100);
			hr.setMsg("statDate传入为空");
			return hr;
		}
		if (StringUtils.isEmpty(params.get("endDate"))) {
			hr.setStatusCode(100);
			hr.setMsg("endDate传入为空");
			return hr;
		}

		JSONArray jarr = new JSONArray();
		try {
			// 查询出月份字段和月开始和月结束
			List<TDakaRecord> punchMonth = service.getPunchMonth(params);

			for (TDakaRecord monthList : punchMonth) {
				// 查询具体每月的打卡数据
				JSONArray punchListArr = new JSONArray();
				JSONObject obj = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				obj.put("monthDtae", monthList.get("monthdate"));
				obj.put("punCount", monthList.get("puncount"));
				map.put("roleId", params.get("roleId"));
				map.put("statDate", monthList.get("statdate").toString());
				map.put("endDate", monthList.get("enddate").toString());
				List<TDakaRecord> punchList = service.getPunchList(map);
				//封装具体打卡记录
				for (TDakaRecord tdr : punchList) {
					JSONObject objList = new JSONObject();
					objList.putAll(tdr.values());
					objList.put("teaName", tdr.get("teaname"));
					objList.put("courseName", tdr.get("coursename"));
					punchListArr.add(objList);
				}
				obj.put("punchList", punchListArr);
				jarr.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		hr.setData(jarr);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}
	
	/**
	 * 用于查询按课程查询的接口
	 * @param params
	 * @param req
	 * @return
	 */

	@RequestMapping("queryPunchListByCourse")
	@ResponseBody()
	public HttpResult getPunchListByCourse(@RequestParam Map<String, String> params, HttpServletRequest req) {
		HttpResult hr = new HttpResult();
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setStatusCode(100);
			hr.setMsg("roleId传入为空");
			return hr;
		}
		JSONArray data = new JSONArray();
		try {
			List<TDakaRecord> punchCourseList = service.getPunchCourseList(params);
			//封装抬头信息
			for (TDakaRecord courseGroup : punchCourseList) {
				JSONObject course = new JSONObject();
				JSONArray punchList = new JSONArray();
				Map<String, String> map = new HashMap<String, String>();
				map.put("courseId", courseGroup.get("courseid").toString());
				map.put("roleId", params.get("roleId"));
				course.put("courseName", courseGroup.get("coursename"));
				course.put("courseId", courseGroup.get("courseid"));
				course.put("punCount", courseGroup.get("puncount"));
				List<TDakaRecord> punchListByCourse = service.getPunchListByCourse(map);
				//封装具体打卡记录
				for (TDakaRecord dakaInfo : punchListByCourse) {
					JSONObject dakaJiLu = new JSONObject();
					dakaJiLu.putAll(dakaInfo.values());
					dakaJiLu.put("teaName", dakaInfo.get("teaname"));
					dakaJiLu.put("courseName", dakaInfo.get("coursename"));
					punchList.add(dakaJiLu);
				}
				course.put("punchList", punchList);
				data.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}
}
