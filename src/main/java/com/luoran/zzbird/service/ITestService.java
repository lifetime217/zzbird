package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.Test;

/**
 * @author lifetime
 *
 */
public interface ITestService extends IBaseService<Test>{
	public List<String> findNames(Integer age);
	public List<Test> findNick(String nick);
}
