package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TImgDelete;
import com.luoran.zzbird.service.ITImgDeleteService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("timgdelete")
public class TImgDeleteAction  implements BaseAction<TImgDelete> {

	@Autowired
	private ITImgDeleteService service;
	
	@RequestMapping
	public String index() {
		return "timgdelete";
	}

	@Override
	public IBaseService<TImgDelete> getService() {
		return service;
	}

}
