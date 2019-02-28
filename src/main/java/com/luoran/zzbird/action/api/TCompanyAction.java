package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;
import com.luoran.zzbird.service.ITCompanyService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tcompany")
public class TCompanyAction  implements BaseAction<TCompany> {

	@Autowired
	private ITCompanyService service;
	
	@RequestMapping
	public String index() {
		return "tcompany";
	}
	
	@RequestMapping("test")
	@ResponseBody()
	public HttpResult get(){
		
		return HttpResult.success("查询成功");
	}
	

	@Override
	public IBaseService<TCompany> getService() {
		return service;
	}

}
