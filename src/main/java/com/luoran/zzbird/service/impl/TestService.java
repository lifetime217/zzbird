package com.luoran.zzbird.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITestDao;
import com.luoran.zzbird.entity.biz.Test;
import com.luoran.zzbird.service.ITestService;

/**
 * @author lifetime
 *
 */
@Service
public class TestService extends AbstractBaseService<Test> implements ITestService{
	@Autowired
	private ITestDao dao;

	public List<String> findNames(Integer age) {
		return dao.findNames(age);
	}

	public List<Test> findNick(String nick) {
		return dao.findNick(nick);
	}

	@Override
	public BaseDao<Test> getDao() {
		return dao;
	}

	@Override
	public String add(Test t) {
		return super.add(t);
	}

}