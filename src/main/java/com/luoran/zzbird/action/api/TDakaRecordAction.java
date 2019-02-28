package com.luoran.zzbird.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("tdakarecord")
public class TDakaRecordAction  implements BaseAction<TDakaRecord> {

	@Autowired
	private ITDakaRecordService service;
	
	@RequestMapping
	public String index() {
		return "tdakarecord";
	}

	@Override
	public IBaseService<TDakaRecord> getService() {
		return service;
	}

}
