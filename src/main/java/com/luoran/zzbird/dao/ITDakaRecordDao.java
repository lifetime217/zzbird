package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.vo.DakaDetailByMonthVo;

/**
 * @author lifetime
 *
 */
public interface ITDakaRecordDao extends BaseDao<TDakaRecord> {

	public void queryPage(PageQuery<TDakaRecord> pageQuery);
	public List<TDakaRecord> queryPunchMonth(Map<String, String> map);
	public List<TDakaRecord> queryPunchList(Map<String, String> map);
	public List<TDakaRecord> queryPunchCourseList(Map<String, String> map);
	public List<TDakaRecord> queryPunchListByCourse(Map<String, String> map);
	
}
