package com.luoran.zzbird.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseUserDao extends BaseDao<TCompanyCourseUser> {

	public void queryPage(PageQuery<TCompanyCourseUser> pageQuery);

	public void queryComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	public List<TCompanyCourseUser> queryCourseByStuRoleId(Map<String, String> map);

	public void queryComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	public List<TCompanyCourseUser> queryCourseByTeaRoleId(Map<String, String> map);

}
