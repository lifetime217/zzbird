package com.luoran.zzbird.service.impl;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITDakaRecordDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.vo.DakaDetailByMonthVo;
import com.luoran.zzbird.service.ITDakaRecordService;


/**
 * @author lifetime
 *
 */
@Service
public class TDakaRecordService extends AbstractBaseService<TDakaRecord> implements ITDakaRecordService{
	@Autowired
	private ITDakaRecordDao dao;
	
	@Override
	public BaseDao<TDakaRecord> getDao() {
		return dao;
	}
	
	@Override
	public String add(TDakaRecord t) {
		return super.add(t);
	}

	@Override
	public List<TDakaRecord> getPunchMonth(Map<String, String> map) {
		List<TDakaRecord> queryPunchMonth = dao.queryPunchMonth(map);
		return queryPunchMonth;
	}

	@Override
	public List<TDakaRecord> getPunchList(Map<String, String> map) {
		List<TDakaRecord> queryPunchList = dao.queryPunchList(map);
		return queryPunchList;
	}

	@Override
	public List<TDakaRecord> getPunchCourseList(Map<String, String> map) {
		List<TDakaRecord> queryPunchCourseList = dao.queryPunchCourseList(map);
		return queryPunchCourseList;
	}

	@Override
	public List<TDakaRecord> getPunchListByCourse(Map<String, String> map) {
		List<TDakaRecord> queryPunchListByCourse = dao.queryPunchListByCourse(map);
		return queryPunchListByCourse;
	}


	
	

}