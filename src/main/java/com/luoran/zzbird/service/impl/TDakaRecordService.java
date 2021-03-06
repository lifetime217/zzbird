package com.luoran.zzbird.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITDakaRecordDao;
import com.luoran.zzbird.dao.ITMessageDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * @author tzx
 *
 */
@Service
@Transactional
public class TDakaRecordService extends AbstractBaseService<TDakaRecord> implements ITDakaRecordService {
	@Autowired
	private ITDakaRecordDao dakaRecordDao;
	@Autowired
	private ITMessageDao messageDao;

	@Override
	public BaseDao<TDakaRecord> getDao() {
		return dakaRecordDao;
	}

	@Override
	public String add(TDakaRecord t) {
		return super.add(t);
	}

	@Override
	public List<TDakaRecord> getPunchMonth(Map<String, String> map) {
		List<TDakaRecord> queryPunchMonth = dakaRecordDao.queryPunchMonth(map);
		return queryPunchMonth;
	}

	@Override
	public List<TDakaRecord> getPunchList(Map<String, String> map) {
		List<TDakaRecord> queryPunchList = dakaRecordDao.queryPunchList(map);
		return queryPunchList;
	}

	@Override
	public List<TDakaRecord> getPunchCourseList(Map<String, String> map) {
		List<TDakaRecord> queryPunchCourseList = dakaRecordDao.queryPunchCourseList(map);
		return queryPunchCourseList;
	}

	@Override
	public List<TDakaRecord> getPunchListByCourse(Map<String, String> map) {
		List<TDakaRecord> queryPunchListByCourse = dakaRecordDao.queryPunchListByCourse(map);
		return queryPunchListByCourse;
	}

	@Override
	public TDakaRecord getTeaInfo(Map<String, Object> params) {
		TDakaRecord teaInfo = dakaRecordDao.queryTeaInfo(params);
		if (teaInfo == null) {
			return null;
		}

		return teaInfo;
	}

	@Override
	public TDakaRecord getTeaDays(Map<String, Object> params) {
		TDakaRecord queryTeaDakaTime = dakaRecordDao.queryTeaDakaTime(params);
		return queryTeaDakaTime;
	}

	@Override
	public List<TDakaRecord> getYidaka(Map<String, Object> params) {
		List<TDakaRecord> yiDakaList = dakaRecordDao.queryYidaka(params);
		return yiDakaList;
	}

	@Override
	public List<TDakaRecord> getWeidaka(Map<String, Object> params) {
		List<TDakaRecord> weiDakaList = dakaRecordDao.queryWeidaka(params);
		return weiDakaList;
	}

	@Override
	public boolean daka(List<Map> javaList, Map<String, Object> params , Date date) {
		for (int i = 0; i < javaList.size(); i++) {
			TDakaRecord tDakaRecord = new TDakaRecord();
			tDakaRecord.setCompanyCourseId(params.get("courseId").toString());
			tDakaRecord.setDakaTime(date);
			tDakaRecord.setIsdelete(0);
			tDakaRecord.setStudentId(javaList.get(i).get("id").toString());
			tDakaRecord.setTeacherId(params.get("roleId").toString());
			String add = add(tDakaRecord);
			if (StringUtils.isEmpty(add)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean quXiaoDaka(Map<String, Object> params) {
		TDakaRecord tDakaRecord = new TDakaRecord();
		tDakaRecord.setId(params.get("id").toString());
		tDakaRecord.setIsdelete(1);
		int updateById = dakaRecordDao.updateTemplateById(tDakaRecord);
		if (updateById > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer queryStuClassHourByCourseId(String courseId) {
		UserContextInfo user = UserContext.get();
		Integer classHour = dakaRecordDao.queryUserClassHour(courseId, user.getXcxUserRoleId(), 30);
		return classHour == null ? 0 : classHour;
	}

	@Override
	public Integer queryUserClassHour() {
		UserContextInfo user = UserContext.get();
		return dakaRecordDao.queryUserClassHour(user.getXcxUserRoleId(), user.getCompanyId(), user.getRoleVal());
	}

	@Override
	public Integer getDakaWeekCount(Map<String, String> params) {
		Integer dakaWeekCount = dakaRecordDao.queryDakaWeekCount(params);
		return dakaWeekCount;
	}

	@Override
	public Integer getClassHourthIsMonth(Map<String, String> params) {
		Integer queryClassHourthIsMonth = dakaRecordDao.queryClassHourthIsMonth(params);
		return queryClassHourthIsMonth;
	}


	@Override
	public Integer queryStuStudyWeek(String courseId) {
		UserContextInfo user = UserContext.get();
		Integer studyWeek = dakaRecordDao.queryStuStudyWeek(user.getXcxUserRoleId(), courseId);
		return studyWeek == null ? 0 : studyWeek;
	}

	@Override
	public void quXiaoDakaMessage(Map<String, Object> params) {
		TDakaRecord dakaRecord = dakaRecordDao.unique(params.get("id"));
		String studentId = dakaRecord.getStudentId();
		
		Date dakaTime = dakaRecord.getDakaTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(dakaTime);
		
		Integer quXiaoDakaMessage = messageDao.quXiaoDakaMessage(studentId, format);
	}

	@Override
	public PageQuery<TDakaRecord> getCompanyDakaGourpByMonth(PageQuery<TDakaRecord> pageQuery) {
		dakaRecordDao.queryCompanyDakaGourpByMonth(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TDakaRecord> getDakaTeaByMonths(Map<String, Object> params) {
		List<TDakaRecord> teacher = dakaRecordDao.queryDakaTeaByMonths(params);
		return teacher;
	}

	@Override
	public List<TDakaRecord> getDakaCourseByTeaAndMonth(Map<String, Object> params) {
		List<TDakaRecord> courseList = dakaRecordDao.queryDakaCourseByTeaAndMonth(params);
		return courseList;
	}

	@Override
	public Integer getDakaDaysByTeaAndCourse(Map<String, Object> params) {
		Integer dayCount = dakaRecordDao.queryDakaDaysByTeaAndCourse(params);
		return dayCount;
	}
}