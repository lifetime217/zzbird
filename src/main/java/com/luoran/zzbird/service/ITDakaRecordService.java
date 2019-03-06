package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.vo.DakaDetailByMonthVo;

/**
 * @author lifetime
 *
 */
public interface ITDakaRecordService extends IBaseService<TDakaRecord> {
	List<TDakaRecord> getPunchMonth(Map<String, String> map);

	List<TDakaRecord> getPunchList(Map<String, String> map);

	List<TDakaRecord> getPunchCourseList(Map<String, String> map);

	List<TDakaRecord> getPunchListByCourse(Map<String, String> map);
}
