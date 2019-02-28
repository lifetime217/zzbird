package com.luoran.zzbird.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITMessageService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tmessage")
public class TMessageAction  implements BaseAction<TMessage> {

	@Autowired
	private ITMessageService service;
	
	@RequestMapping
	public String index() {
		return "tmessage";
	}

	@Override
	public IBaseService<TMessage> getService() {
		return service;
	}

}
