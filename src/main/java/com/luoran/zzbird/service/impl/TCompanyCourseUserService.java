package com.luoran.zzbird.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseUserDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TCompanyCourseUser;
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.entity.vo.CourseUserVo;
import com.luoran.zzbird.entity.vo.InviteVo;
import com.luoran.zzbird.service.ITCompanyCourseUserService;
import com.luoran.zzbird.service.ITMessageService;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.utils.ShortUuid;
import com.luoran.zzbird.utils.UUID;

/**
 * @author lifetime
 *
 */
@Service
public class TCompanyCourseUserService extends AbstractBaseService<TCompanyCourseUser>
		implements ITCompanyCourseUserService {
	@Autowired
	private ITCompanyCourseUserDao companyCourseUserDao;

	@Autowired
	ITXcxUserService xcxUserService;

	@Autowired
	ITXcxUserRoleService xcxUserRoleService;

	@Autowired
	ITMessageService messageService;

	@Override
	public BaseDao<TCompanyCourseUser> getDao() {
		return companyCourseUserDao;
	}

	@Override
	public String add(TCompanyCourseUser t) {
		return super.add(t);
	}
	
	@Override
	public PageQuery<TCompanyCourseUser> getComUserByBoosRole(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryComUserByBoosRole(pageQuery);
		return pageQuery;
	}

	@Override
	public List<TCompanyCourseUser> getCourseByUserRoleId(Map<String, String> map) {
		List<TCompanyCourseUser> list = companyCourseUserDao.queryCourseByUserRoleId(map);
		return list;
	}

	@Override
	public List<CourseUserVo> queryCourseUserByCourseId(String courseId, Integer roleVal) {
		return companyCourseUserDao.queryCourseUserByCourseId(courseId, roleVal);
	}

	@Override
	public PageQuery<TCompanyCourse> queryCourseList(String page) {
		UserContextInfo user = UserContext.get();
		PageQuery<TCompanyCourse> pageQuery = new PageQuery<TCompanyCourse>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("openId", user.getOpenid());
		params.put("companyId", user.getCompanyId());
		params.put("roleVal", user.getRoleVal().toString());
		// 存入分页参数
		pageQuery.setPageNumber(Integer.parseInt(page));
		pageQuery.setPageSize(10);
		pageQuery.setParas(params);
		companyCourseUserDao.queryCourseByUserList(pageQuery);
		return pageQuery;
	}

	@Override
	@Transactional
	public void addCourseUser(InviteVo inviteVo, String zzbird_XcxSessionKey) {
		UserContextInfo userContextInfo = UserContext.get();

		TXcxUser xcxUser = xcxUserService.queryXcxUserByOpenId(userContextInfo.getOpenid());
		String xcxUserId;
		// 如没有查询到用户 新增一条数据
		if (xcxUser == null) {
			TXcxUser tXcxUser = new TXcxUser(inviteVo);
			xcxUserId = xcxUserService.addUser(tXcxUser, zzbird_XcxSessionKey);
		} else {
			xcxUserId = xcxUser.getId();
		}
		// 修改正在使用为0
		xcxUserRoleService.updateCurrentActiveByZero(xcxUserId);

		Integer roleVal;// 邀请人的角色
		String content;// 消息内容
		if ("10".equals(inviteVo.getInviteRoleVal())) {
			// 邀请老师
			roleVal = 20;
			content = "你成为了(" + inviteVo.getCompanyName() + ")的老师";
		} else {
			// 邀请学生
			roleVal = 30;
			content = "你成为了(" + inviteVo.getCourseName() + ")的学员";
		}
		// 添加公司用户表
		TXcxUserRole tXcxUserRole = new TXcxUserRole();
		tXcxUserRole.setCompanyId(inviteVo.getCompanyId());
		tXcxUserRole.setRoleVal(roleVal);
		tXcxUserRole.setCurrentActive(1);
		tXcxUserRole.setRoleName(inviteVo.getNickName());
		tXcxUserRole.setRoleHeadimg(inviteVo.getAvatarUrl());
		tXcxUserRole.setXcxUserId(xcxUserId);
		tXcxUserRole.setIsdelete(0);
		tXcxUserRole.setSign(ShortUuid.generateShortUuid());
		Integer xcxUserRoleId = xcxUserRoleService.insert(tXcxUserRole);

		// 添加课程用户表
		TCompanyCourseUser tCompanyCourseUser = new TCompanyCourseUser();
		tCompanyCourseUser.setAddTime(new Date());
		tCompanyCourseUser.setCompanyCourseId(inviteVo.getCourseId());
		tCompanyCourseUser.setXcxUserRoleId(xcxUserRoleId.toString());
		add(tCompanyCourseUser);

		// 邀请人的角色id
		String inviteRoleId = xcxUserService.queryXcxUserRole(inviteVo.getInviteSessionKey(),
				inviteVo.getInviteRoleVal(), inviteVo.getCompanyId());
		// 添加消息表
		TMessage msg = new TMessage();
		msg.setId(UUID.get());
		msg.setContent(content);
		msg.setTitle("邀请");
		msg.setFromUser(inviteRoleId);
		msg.setIsdelete(0);
		msg.setRead(0);
		msg.setToUser(xcxUserRoleId.toString());
		// TODO  添加消息时间
		messageService.add(msg);
		
		
	}

	

}