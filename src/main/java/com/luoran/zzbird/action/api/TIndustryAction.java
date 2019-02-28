package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TIndustry;
import com.luoran.zzbird.service.ITIndustryService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tindustry")
public class TIndustryAction  implements BaseAction<TIndustry> {

	@Autowired
	private ITIndustryService service;
	
	@RequestMapping
	public String index() {
		return "tindustry";
	}

	@Override
	public IBaseService<TIndustry> getService() {
		return service;
	}

}
