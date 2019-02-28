package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TPoster;
import com.luoran.zzbird.service.ITPosterService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tposter")
public class TPosterAction  implements BaseAction<TPoster> {

	@Autowired
	private ITPosterService service;
	
	@RequestMapping
	public String index() {
		return "tposter";
	}

	@Override
	public IBaseService<TPoster> getService() {
		return service;
	}

}
