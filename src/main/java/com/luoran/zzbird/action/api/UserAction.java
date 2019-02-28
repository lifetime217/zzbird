package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.User;
import com.luoran.zzbird.service.IUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("user")
public class UserAction  implements BaseAction<User> {

	@Autowired
	private IUserService service;
	
	@RequestMapping
	public String index() {
		return "user";
	}

	@Override
	public IBaseService<User> getService() {
		return service;
	}

}
