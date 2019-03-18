package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITDakaRecordDao;
import com.luoran.zzbird.entity.biz.TDakaRecord;
import com.luoran.zzbird.service.ITDakaRecordService;

/**
 * @author tzx
 *
 */
@Service
@Transactional
public class TDakaRecordService extends AbstractBaseService<TDakaRecord> implements ITDakaRecordService {
	@Autowired
	private ITDakaRecordDao iTDakaRecordDao;

	@Override
	public BaseDao<TDakaRecord> getDao() {
		return iTDakaRecordDao;
	}

	@Override
	public String add(TDakaRecord t) {
		return super.add(t);
	}

	@Override
	public List<TDakaRecord> getPunchMonth(Map<String, String> map) {
		List<TDakaRecord> queryPunchMonth = iTDakaRecordDao.queryPunchMonth(map);
		return queryPunchMonth;
	}

	@Override
	public List<TDakaRecord> getPunchList(Map<String, String> map) {
		List<TDakaRecord> queryPunchList = iTDakaRecordDao.queryPunchList(map);
		return queryPunchList;
	}

	@Override
	public List<TDakaRecord> getPunchCourseList(Map<String, String> map) {
		List<TDakaRecord> queryPunchCourseList = iTDakaRecordDao.queryPunchCourseList(map);
		return queryPunchCourseList;
	}

	@Override
	public List<TDakaRecord> getPunchListByCourse(Map<String, String> map) {
		List<TDakaRecord> queryPunchListByCourse = iTDakaRecordDao.queryPunchListByCourse(map);
		return queryPunchListByCourse;
	}

	@Override
	public TDakaRecord getTeaInfo(Map<String, Object> params) {
		TDakaRecord teaInfo = iTDakaRecordDao.queryTeaInfo(params);
		if (teaInfo == null) {
			return null;
		}

		return teaInfo;
	}

	@Override
	public TDakaRecord getTeaDays(Map<String, Object> params) {
		TDakaRecord queryTeaDakaTime = iTDakaRecordDao.queryTeaDakaTime(params);
		return queryTeaDakaTime;
	}

	@Override
	public List<TDakaRecord> getYidaka(Map<String, Object> params) {
		List<TDakaRecord> yiDakaList = iTDakaRecordDao.queryYidaka(params);
		return yiDakaList;
	}

	@Override
	public List<TDakaRecord> getWeidaka(Map<String, Object> params) {
		List<TDakaRecord> weiDakaList = iTDakaRecordDao.queryWeidaka(params);
		return weiDakaList;
	}

	@Override
	public boolean daka(List<Map> javaList, Map<String, Object> params) {
		for (int i = 0; i < javaList.size(); i++) {
			TDakaRecord tDakaRecord = new TDakaRecord();
			tDakaRecord.setCompanyCourseId(params.get("courseId").toString());
			tDakaRecord.setDakaTime(new Date());
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
		int updateById = iTDakaRecordDao.updateTemplateById(tDakaRecord);
		if (updateById > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer queryStuClassHourByCourseId(String courseId) {
		UserContextInfo user = UserContext.get();
		Integer classHour = iTDakaRecordDao.queryUserClassHour(courseId, user.getXcxUserRoleId(), 30);
		return classHour == null ? 0 : classHour;
	}

	@Override
	public Integer queryUserClassHour() {
		UserContextInfo user = UserContext.get();
		return iTDakaRecordDao.queryUserClassHour(user.getXcxUserRoleId(), user.getCompanyId(), user.getRoleVal());
	}

	@Override
	public Integer getDakaWeekCount(Map<String, String> params) {
		Integer dakaWeekCount = iTDakaRecordDao.queryDakaWeekCount(params);
		return dakaWeekCount;
	}

	@Override
	public Integer getClassHourthIsMonth(Map<String, String> params) {
		Integer queryClassHourthIsMonth = iTDakaRecordDao.queryClassHourthIsMonth(params);
		return queryClassHourthIsMonth;
	}


	@Override
	public Integer queryStuStudyWeek(String courseId) {
		UserContextInfo user = UserContext.get();
		Integer studyWeek = iTDakaRecordDao.queryStuStudyWeek(user.getXcxUserRoleId(), courseId);
		return studyWeek == null ? 0 : studyWeek;
	}
}