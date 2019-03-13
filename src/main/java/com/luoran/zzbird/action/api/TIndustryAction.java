package com.luoran.zzbird.action.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TIndustry;
import com.luoran.zzbird.service.ITIndustryService;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("api/industry")
public class TIndustryAction implements BaseAction<TIndustry> {
	
	
	private static final Logger log = LoggerFactory.getLogger(TIndustryAction.class);


	@Autowired
	private ITIndustryService industryService;

	@RequestMapping
	public String index() {
		return "tindustry";
	}

	/**
	 * 
	 * @Author wsl  
	 * @Title: queryIndustry   
	 * @Description:    查询出所有的标签类型
	 * @param: @return      
	 * @return: HttpResult      
	 * @throws
	 */
	@RequestMapping("/queryIndustry")
	@ResponseBody()
	public HttpResult queryIndustry() {
		JSONObject obj = new JSONObject();
		try {
			obj = industryService.queryIndustry();
		} catch (Exception e) {
			log.error(e.getMessage(),e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", obj);
	}

	@Override
	public IBaseService<TIndustry> getService() {
		return industryService;
	}

}
