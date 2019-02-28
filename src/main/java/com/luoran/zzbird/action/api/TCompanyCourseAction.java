package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.service.ITCompanyCourseService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tcompanycourse")
public class TCompanyCourseAction  implements BaseAction<TCompanyCourse> {

	@Autowired
	private ITCompanyCourseService service;
	
	@RequestMapping
	public String index() {
		return "tcompanycourse";
	}

	@Override
	public IBaseService<TCompanyCourse> getService() {
		return service;
	}

}
