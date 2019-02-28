package com.luoran.zzbird.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.service.ITCompanyCourseUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tcompanycourseuser")
public class TCompanyCourseUserAction  implements BaseAction<TCompanyCourseUser> {

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

}
