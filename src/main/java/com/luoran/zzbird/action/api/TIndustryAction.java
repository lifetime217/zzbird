package com.luoran.zzbird.action.api;

import java.util.List;

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
@RequestMapping("industry")
public class TIndustryAction implements BaseAction<TIndustry> {

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
	 * @Description: TODO   查询出所有的标签类型
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", obj);
	}

	@Override
	public IBaseService<TIndustry> getService() {
		return industryService;
	}

}
