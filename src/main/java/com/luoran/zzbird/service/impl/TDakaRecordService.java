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
	public PageQuery<DakaDetailByMonthVo> getPunchList(Map<String, String> map) {
		PageQuery<DakaDetailByMonthVo> pageQuery = new PageQuery<DakaDetailByMonthVo>();
		pageQuery.setPageSize(10);
		pageQuery.setPageNumber(Integer.valueOf(map.get("page")));
		pageQuery.setParas(map);
		dao.queryDatePage(pageQuery);
		
		return pageQuery;
	}
	

}