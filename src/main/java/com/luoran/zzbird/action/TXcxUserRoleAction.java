package com.luoran.zzbird.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserRoleService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("txcxuserrole")
public class TXcxUserRoleAction  implements BaseAction<TXcxUserRole> {

	@Autowired
	private ITXcxUserRoleService service;
	
	@RequestMapping
	public String index() {
		return "txcxuserrole";
	}

	@Override
	public IBaseService<TXcxUserRole> getService() {
		return service;
	}

}
