package com.luoran.zzbird.action.api;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.luoran.zzbird.entity.vo.CourseUserVo;
import com.luoran.zzbird.service.ITCompanyCourseService;
import com.luoran.zzbird.service.ITCompanyCourseUserService;
import com.luoran.zzbird.utils.Convert;
import com.luoran.zzbird.utils.ShortUuid;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("api/companycourse")
public class TCompanyCourseAction implements BaseAction<TCompanyCourse> {

	private final static Logger log = LoggerFactory.getLogger(TCompanyCourseAction.class);

	@Autowired
	private ITCompanyCourseService courseService;
	
	@Autowired
	private ITCompanyCourseUserService companyCourseUserService;

	@Autowired
	Environment env;

	@RequestMapping
	public String index() {
		return "tcompanycourse";
	}

	@Override
	public IBaseService<TCompanyCourse> getService() {
		return courseService;
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 新建课程
	 */
	@RequestMapping("/addCourse")
	@ResponseBody()
	public HttpResult addCourse(TCompanyCourse course) {
		JSONObject res = new JSONObject();
		try {
			UserContextInfo user = UserContext.get();
			course.setCompanyId(user.getCompanyId());
			course.setPersonNumber(0);
			String courseId = courseService.add(course);
			res.put("courseId", courseId);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.fail("新增失败");
		}
		return HttpResult.success("新增成功", res);
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 根据课程id查询课程的基本信息
	 */
	@RequestMapping(value = "/queryCourseDetailByCourseId/{courseId}", method = RequestMethod.GET)
	@ResponseBody()
	public HttpResult queryCourseDetailByCourseId(@PathVariable(value = "courseId") String courseId) {
		JSONObject res = new JSONObject();
		try {
			TCompanyCourse course = courseService.get(courseId);

			res.put("course", course);
			// 拿到图片的访问地址
			String url = env.getProperty("file.path.url");
			List<String> courseImgsUrl = Arrays.asList(Convert.convertImgString(course.getCourseImg(), url).split(","));
			List<String> courseImgsName = Arrays.asList(course.getCourseImg().split(","));
			// 拼接图片的集合
			JSONArray courseImgList = new JSONArray();
			for (int i = 0; i < courseImgsUrl.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("url", courseImgsUrl.get(i));
				obj.put("name", courseImgsName.get(i));
				courseImgList.add(obj);
			}
			res.put("courseImgsUrl", courseImgList);// courseImg图片集合
			res.put("courseImgsName", courseImgsName);// courseImg图片名称
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 修改课程
	 */
	@RequestMapping("/updateCourse")
	@ResponseBody()
	public HttpResult updateCourse(TCompanyCourse course) {
		JSONObject res = new JSONObject();
		try {
			UserContextInfo user = UserContext.get();
			course.setCompanyId(user.getCompanyId());
			courseService.save(course);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.fail("修改失败");
		}
		return HttpResult.success("修改成功", res);

	}

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 查询课程信息、公司名、课程老师的名字、如果是学生需要查询用户的学习天数和累计学习的课时
	 */
	@RequestMapping(value = "/queryCourseAndCompany/{courseId}", method = RequestMethod.GET)
	@ResponseBody()
	public HttpResult queryCourseAndCompany(@PathVariable(value = "courseId") String courseId) {
		JSONObject res = new JSONObject();
		try {
			TCompanyCourse course = courseService.queryCourseDetail(courseId);
			res.put("companyName", course.get("companyName"));
			res.put("course", course);
			// 拿到图片的访问地址
			String url = env.getProperty("file.path.url");
			List<String> courseImgsUrl = Arrays.asList(Convert.convertImgString(course.getCourseImg(), url).split(","));
			List<String> courseImgsName = Arrays.asList(course.getCourseImg().split(","));
			// 拼接图片的集合
			JSONArray courseImgList = new JSONArray();
			for (int i = 0; i < courseImgsUrl.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("url", courseImgsUrl.get(i));
				obj.put("name", courseImgsName.get(i));
				courseImgList.add(obj);
			}
			res.put("courseImgsUrl", courseImgList);// courseImg图片集合
			
			//查询课程下的老师
			List<CourseUserVo> teacher= companyCourseUserService.queryCourseUserByCourseId(courseId, 20);
			res.put("teacher", teacher);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);

	}

}
