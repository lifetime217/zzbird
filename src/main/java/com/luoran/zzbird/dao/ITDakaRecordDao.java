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

	/**
	 * @author tzx 查询打卡的月并且分组排序
	 * @param #roleId：学生角色id #statDate：开始时间 #endDate：结束时间
	 */
	public List<TDakaRecord> queryPunchMonth(Map<String, String> map);

	/**
	 * @author tzx 按月查询打卡记录
	 * @param #roleId：学生角色id #statDate：开始时间 #endDate：结束时间
	 */
	public List<TDakaRecord> queryPunchList(Map<String, String> map);

	/**
	 * @author tzx 查询课程的列
	 * @param #roleId：学生角色id
	 */
	public List<TDakaRecord> queryPunchCourseList(Map<String, String> map);

	/**
	 * @author tzx 按月查询打卡记录
	 * @param #roleId：学生角色id #courseId：课程id
	 */
	public List<TDakaRecord> queryPunchListByCourse(Map<String, String> map);

	/**
	 * @author tzx 查询老师详情
	 * @param #roleId：老师角色id #courseId：课程id
	 */
	public TDakaRecord queryTeaInfo(Map<String, Object> params);

	/**
	 * @author tzx 查询老师打的卡天数
	 * @param #roleId：老师角色id #courseId：课程id
	 */
	public TDakaRecord queryTeaDakaTime(Map<String, Object> params);

	/**
	 * @author tzx 查询已打卡
	 * @param #courseId：课程id
	 */
	public List<TDakaRecord> queryYidaka(Map<String, Object> params);

	/**
	 * @author tzx 打卡
	 * @param #courseId：课程id #roleId：老师的角色id #list：学生的id list集合中的Map<"id","...">
	 */
	public List<TDakaRecord> queryWeidaka(Map<String, Object> params);

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
