package com.luoran.zzbird.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.dao.ITestDao;
import com.luoran.zzbird.entity.biz.Test;
import com.luoran.zzbird.service.ITestService;

/**
 * @author lifetime
 *
 */
@RestController
@RequestMapping("test")
public class TestAction  implements BaseAction<Test> {

	@Autowired
	private ITestService service;
	
	@Autowired
	private ITestDao testDao;
	
	
	@RequestMapping("get")
	public HttpResult get(){
		
		return HttpResult.success("查询成功", testDao.all());
	}
	
	@RequestMapping("get1")
	public HttpResult get1(){
		
		return HttpResult.success("查询成功", testDao.findNames(30));
	}
	
	@RequestMapping("get2")
	public HttpResult get2(){
		
		return HttpResult.success("查询成功", testDao.findNick(""));
	}

	@Override
	public IBaseService<Test> getService() {
		return service;
	}

}
