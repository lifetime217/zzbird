package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.service.ITXcxUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("txcxuser")
public class TXcxUserAction  implements BaseAction<TXcxUser> {

	@Autowired
	private ITXcxUserService service;
	
	@RequestMapping
	public String index() {
		return "txcxuser";
	}

	@Override
	public IBaseService<TXcxUser> getService() {
		return service;
	}

}
