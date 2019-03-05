package com.luoran.zzbird.action.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.vo.DakaDetailByMonthVo;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * @author lifetime
 * statusCode ：100 接口参数获取失败，200 成功 ，500 后台错误 ，400 找不到对应的值
 */

@Controller
@RequestMapping("dakarecord")
public class TDakaRecordAction  implements BaseAction<TDakaRecord> {

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
	
	@RequestMapping("queryPunchList")
	@ResponseBody
	public HttpResult getPunchList(@RequestParam Map<String, String> params,HttpServletRequest req) {
		HttpResult hr = new HttpResult();
		if(StringUtils.isEmpty(params.get("page"))) {
			hr.setStatusCode(100);
			hr.setMsg("page传入为空");
			return hr;
		}
		if(StringUtils.isEmpty(params.get("openId"))) {
			hr.setStatusCode(100);
			hr.setMsg("openId传入为空");
			return hr;
		}
		if(StringUtils.isEmpty(params.get("companyId"))) {
			hr.setStatusCode(100);
			hr.setMsg("companyId传入为空");
			return hr;
		}
		if(StringUtils.isEmpty(params.get("statDate"))) {
			hr.setStatusCode(100);
			hr.setMsg("statDate传入为空");
			return hr;
		}
		if(StringUtils.isEmpty(params.get("endDate"))) {
			hr.setStatusCode(100);
			hr.setMsg("endDate传入为空");
			return hr;
		}
		
		
		 PageQuery<DakaDetailByMonthVo> punchList = service.getPunchList(params);
		 
		return null;
	}

}
