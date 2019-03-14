package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TDakaRecord;

/**
 * @author tzx
 *
 */
public interface ITDakaRecordService extends IBaseService<TDakaRecord> {
	/**
	 * @author tzx 查询打卡的月并且分组排序
	 * @param #roleId：学生角色id #statDate：开始时间 #endDate：结束时间
	 */
	List<TDakaRecord> getPunchMonth(Map<String, String> map);

	/**
	 * @author tzx 按月查询打卡记录
	 * @param #roleId：学生角色id #statDate：开始时间 #endDate：结束时间
	 */
	List<TDakaRecord> getPunchList(Map<String, String> map);

	/**
	 * @author tzx 查询课程的列
	 * @param #roleId：学生角色id
	 */
	List<TDakaRecord> getPunchCourseList(Map<String, String> map);

	/**
	 * @author tzx 按月查询打卡记录
	 * @param #roleId：学生角色id #courseId：课程id
	 */
	List<TDakaRecord> getPunchListByCourse(Map<String, String> map);

	/**
	 * @author tzx 查询老师详情
	 * @param #roleId：老师角色id #courseId：课程id
	 */
	TDakaRecord getTeaInfo(Map<String, Object> params);

	/**
	 * @author tzx 查询老师打的卡天数
	 * @param #roleId：老师角色id #courseId：课程id
	 */
	TDakaRecord getTeaDays(Map<String, Object> params);

	/**
	 * @author tzx 查询已打卡
	 * @param #courseId：课程id
	 */
	List<TDakaRecord> getYidaka(Map<String, Object> params);

	/**
	 * @author tzx 查询未打卡
	 * @param #courseId：课程id #list：学生的id list集合中的Map<id,...>
	 */
	List<TDakaRecord> getWeidaka(Map<String, Object> params);

	/**
	 * @author tzx 打卡
	 * @param #courseId：课程id #roleId：老师的角色id #list：学生的id list集合中的Map<"id","...">
	 */
	boolean daka(List<Map> javaList, Map<String, Object> params);

	/**
	 * @author tzx 取消打卡
	 * @param #id：打卡记录表的id
	 */
	boolean quXiaoDaka(Map<String, Object> params);

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
	Integer queryUserClassHour();
}
