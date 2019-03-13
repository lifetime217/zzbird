package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;

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
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:查询学生单个课程的课时  
	 */
	Integer queryUserClassHour(@Param("courseId")String courseId,@Param("roleId")Integer roleId,@Param("roleVal")Integer roleVal);
	
	/**
	 * 
	 * @Author wsl  
	 * @Description:查询学生的总课时（个人 中心） roleVal来区分是用户(30)、老师(20)、企业(10)
	 */
	Integer queryUserClassHour(@Param("roleId")Integer roleId,@Param("companyId")String companyId,@Param("roleVal")Integer roleVal);
	
}
