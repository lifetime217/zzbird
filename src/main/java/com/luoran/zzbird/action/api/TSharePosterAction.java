package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TSharePoster;
import com.luoran.zzbird.service.ITSharePosterService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("shareposter")
public class TSharePosterAction  implements BaseAction<TSharePoster> {

	@Autowired
	private ITSharePosterService service;
	
	@RequestMapping
	public String index() {
		return "tshareposter";
	}

	@Override
	public IBaseService<TSharePoster> getService() {
		return service;
	}

}
