package com.luoran.zzbird.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;

/**
 * @author lifetime
 *
 */
public interface ITCompanyCourseUserService extends IBaseService<TCompanyCourseUser>{

	PageQuery<TCompanyCourseUser> getComStudentByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	List<TCompanyCourseUser> getCourseByStuRoleId(Map<String, String> map);

	PageQuery<TCompanyCourseUser> getComTeacherByBoosRole(PageQuery<TCompanyCourseUser> pageQuery);

	List<TCompanyCourseUser> getCourseByTeaRoleId(Map<String, String> map);


	
}
