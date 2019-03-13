package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TDakaRecord;

/**
 * @author lifetime
 *
 */
public interface ITDakaRecordService extends IBaseService<TDakaRecord> {
	List<TDakaRecord> getPunchMonth(Map<String, String> map);

	List<TDakaRecord> getPunchList(Map<String, String> map);

	List<TDakaRecord> getPunchCourseList(Map<String, String> map);

	List<TDakaRecord> getPunchListByCourse(Map<String, String> map);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:查询学生的课程上课的总课时
	 */
	Integer queryStuClassHourByCourseId(String courseId);
	
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:查询学生、老师、企业的上课总课时
	 */
	Integer queryUserClassHour(String companyId);
}
