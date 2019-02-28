package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TWechatUser;
import com.luoran.zzbird.service.ITWechatUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("twechatuser")
public class TWechatUserAction  implements BaseAction<TWechatUser> {

	@Autowired
	private ITWechatUserService service;
	
	@RequestMapping
	public String index() {
		return "twechatuser";
	}

	@Override
	public IBaseService<TWechatUser> getService() {
		return service;
	}

}
