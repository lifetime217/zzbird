package com.luoran.zzbird.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITDakaRecordDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;
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

}