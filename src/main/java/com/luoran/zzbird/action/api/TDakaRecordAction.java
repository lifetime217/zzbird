package com.luoran.zzbird.action.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.service.ITDakaRecordService;
import com.luoran.zzbird.service.ITMessageService;
import com.luoran.zzbird.service.ITWechatUserService;

/**
 * 打卡操作接口
 * 
 * @author tzx statusCode ：100 接口参数获取失败，200 成功 ，500 后台错误 ，400 找不到对应的值
 */

@Controller
@RequestMapping("api/dakarecord")
public class TDakaRecordAction implements BaseAction<TDakaRecord> {
	private final static Logger log = LoggerFactory.getLogger(TCompanyCourseUserAction.class);
	@Autowired
	private ITDakaRecordService dakaRecordService;
	@Autowired
	private ITMessageService messageService;
	@Autowired
	ITWechatUserService wechatUserService;
	@RequestMapping
	public String index() {
		return "tdakarecord";
	}

	@Override
	public IBaseService<TDakaRecord> getService() {
		return dakaRecordService;
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
		// 获取用户roleId
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setStatusCode(100);
			hr.setMsg("后台roleId传入为空");
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
			List<TDakaRecord> punchMonth = dakaRecordService.getPunchMonth(params);

			for (TDakaRecord monthList : punchMonth) {
				// 查询具体每月的月份
				JSONArray punchListArr = new JSONArray();
				JSONObject obj = new JSONObject();
				Map<String, String> map = new HashMap<String, String>();
				obj.put("monthDtae", monthList.get("monthdate"));
				obj.put("punCount", monthList.get("puncount"));
				map.put("roleId", params.get("roleId"));
				map.put("statDate", monthList.get("statdate").toString());
				map.put("endDate", monthList.get("enddate").toString());
				List<TDakaRecord> punchList = dakaRecordService.getPunchList(map);
				// 封装具体打卡记录
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
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("服务器错误");
			hr.setStatusCode(500);
			return hr;
		}
		hr.setData(jarr);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}

	/**
	 * 用于查询按课程查询的接口
	 * 
	 * @param params
	 * @param req
	 * @return
	 */

	@RequestMapping("queryPunchListByCourse")
	@ResponseBody()
	public HttpResult getPunchListByCourse(@RequestParam Map<String, String> params, HttpServletRequest req) {
		HttpResult hr = new HttpResult();
		// 获取用户roleId
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setStatusCode(100);
			hr.setMsg("后台roleId传入为空");
			return hr;
		}
		JSONArray data = new JSONArray();
		try {
			List<TDakaRecord> punchCourseList = dakaRecordService.getPunchCourseList(params);
			// 封装抬头信息
			for (TDakaRecord courseGroup : punchCourseList) {
				JSONObject course = new JSONObject();
				JSONArray punchList = new JSONArray();
				Map<String, String> map = new HashMap<String, String>();
				map.put("courseId", courseGroup.get("courseid").toString());
				map.put("roleId", params.get("roleId"));
				course.put("courseName", courseGroup.get("coursename"));
				course.put("courseId", courseGroup.get("courseid"));
				course.put("punCount", courseGroup.get("puncount"));
				List<TDakaRecord> punchListByCourse = dakaRecordService.getPunchListByCourse(map);
				// 封装具体打卡记录
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
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("服务器错误");
			hr.setStatusCode(500);
			return hr;
		}
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}

	@RequestMapping("/queryYidakaWeidakaAndTeaInfo")
	@ResponseBody()
	public HttpResult queryYidakaWeidaka(@RequestParam Map<String, Object> params) {
		HttpResult hr = new HttpResult();
		// 获取用户roleId
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		JSONObject data = new JSONObject();
		if (StringUtils.isEmpty(params.get("roleId"))) {
			hr.setStatusCode(100);
			hr.setMsg("后台roleId传入为空");
			return hr;
		}
		if (StringUtils.isEmpty(params.get("courseId"))) {
			hr.setStatusCode(100);
			hr.setMsg("courseId传入为空");
			return hr;
		}
		try {

			// 查询老师是否拥有这个课程
			TDakaRecord teaInfo = dakaRecordService.getTeaInfo(params);
			if (teaInfo == null) {
				hr.setStatusCode(100);
				hr.setMsg("您不是本课的老师");
				return hr;
			}
			data.putAll(teaInfo.values());
			// 查询老师这个课程打卡天数累计
			TDakaRecord teaDays = dakaRecordService.getTeaDays(params);
			data.putAll(teaDays.values());
			// 查询已打卡的学生 按当天的课程
			List<TDakaRecord> yiDaka = dakaRecordService.getYidaka(params);
			JSONArray yiDakaList = new JSONArray();
			for (int i = 0; i < yiDaka.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.putAll(yiDaka.get(i).values());
				yiDakaList.add(obj);
			}
			// 封装已打卡的学生
			data.put("yiDaka", yiDakaList);
			List<String> paList = new ArrayList<String>();
			// 取出已打卡的学生id集合存入另一个集合当作查询未打卡的参数使用
			for (int i = 0; i < yiDaka.size(); i++) {
				paList.add(yiDaka.get(i).get("id").toString());
			}
			params.put("list", paList);
			// 根据学生id集合not in 方法查询未打卡的学生
			List<TDakaRecord> weiDaka = dakaRecordService.getWeidaka(params);
			JSONArray weiDakaList = new JSONArray();
			for (int i = 0; i < weiDaka.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.putAll(weiDaka.get(i).values());
				weiDakaList.add(obj);
			}
			// 封装未打卡的学生
			data.put("weiDaka", weiDakaList);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("服务器错误");
			hr.setStatusCode(500);
			return hr;
		}
		hr.setData(data);
		hr.setMsg("查询成功");
		hr.setStatusCode(200);
		return hr;
	}

	/**
	 * @author tzx 给学生打卡
	 */
	@RequestMapping("/daka")
	@ResponseBody()
	public HttpResult daka(@RequestParam Map<String, Object> params) {
		HttpResult hr = new HttpResult();
		// 获取用户roleId
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		if (StringUtils.isEmpty(params.get("courseId"))) {
			hr.setMsg("课程id未传入");
			hr.setStatusCode(100);
			return hr;
		}
		// 解析前台传过来的学生id数组
		JSONArray parseArray = JSONArray.parseArray(params.get("clickList").toString());
		List<Map> studentList = parseArray.toJavaList(Map.class);
		// 打卡操作
		boolean success = dakaRecordService.daka(studentList, params);
		if (!success) {
			hr.setMsg("打卡失败，数据错误");
			hr.setStatusCode(500);
			return hr;
		}
		messageService.sendDakaMessage(userContextInfo,params,studentList);
		//wechatUserService.sendGZHMessage(userContextInfo,params,studentList);
		System.out.println("-------------------------------");
		try {
			System.out.println();
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("后台错误");
			hr.setStatusCode(500);
			return hr;
		}
		hr.setMsg("打卡成功");
		hr.setStatusCode(200);
		return hr;
	}

	/**
	 * @author tzx 删除打卡记录
	 */
	@RequestMapping("/quXiaoDaka")
	@ResponseBody()
	public HttpResult quXiaoDaka(@RequestParam Map<String, Object> params) {
		HttpResult hr = new HttpResult();
		try {
			// 设置行数据isdelete字段为1
			boolean success = dakaRecordService.quXiaoDaka(params);
			// 未成功返回信息
			if (!success) {
				hr.setMsg("取消打卡失败");
				hr.setStatusCode(500);
				return hr;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			hr.setMsg("后台错误");
			hr.setStatusCode(500);
			return hr;
		}

		hr.setMsg("取消打卡成功");
		hr.setStatusCode(200);
		return hr;

	}

}
