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
import com.luoran.zzbird.service.ITCompanyCourseService;
import com.luoran.zzbird.service.ITCompanyCourseUserService;
import com.luoran.zzbird.service.ITCompanyService;
import com.luoran.zzbird.service.ITMessageService;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.service.IWechatUserRelationService;
import com.luoran.zzbird.utils.ShortUuid;
import com.luoran.zzbird.utils.UUID;

/**
 * @author tzx
 *
 */
@Service
public class TCompanyCourseUserService extends AbstractBaseService<TCompanyCourseUser>
		implements ITCompanyCourseUserService {
	@Autowired
	private ITCompanyCourseUserDao companyCourseUserDao;

	@Autowired
	ITCompanyCourseService companyCourseService;

	@Autowired
	ITCompanyService companyService;

	@Autowired
	ITXcxUserService xcxUserService;

	@Autowired
	ITXcxUserRoleService xcxUserRoleService;

	@Autowired
	ITMessageService messageService;

	@Autowired
	IWechatUserRelationService wechatUserRelationService;

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
		

		Integer roleVal;// 邀请人的角色
		String content;// 邀请人和被邀请人的接受消息内容
		if ("10".equals(inviteVo.getInviteRoleVal())) {
			// 邀请老师
			roleVal = 20;
			content = "您成为了(" + inviteVo.getCompanyName() + ")的老师";
			companyService.updateCompanyPersonNumber(inviteVo.getCompanyId(), 0);
		} else {
			// 邀请学生
			roleVal = 30;
			content = "您成为了(" + inviteVo.getCourseName() + ")的学员";
			companyService.updateCompanyPersonNumber(inviteVo.getCompanyId(), 1);
			companyCourseService.updatePerson(inviteVo.getCourseId());
		}
		TXcxUserRole xcxUserRole = xcxUserRoleService.queryUserRoleExist(xcxUserId, roleVal, inviteVo.getCompanyId());
		Integer xcxUserRoleId;
		// 公司用户表判断
		if (xcxUserRole == null) {
			// 修改正在使用为0
			xcxUserRoleService.updateCurrentActiveByZero(zzbird_XcxSessionKey);
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
			xcxUserRoleId = xcxUserRoleService.insert(tXcxUserRole);
		} else {
			xcxUserRoleId = xcxUserRole.getId();
		}

		// 添加课程用户表
		TCompanyCourseUser tCompanyCourseUser = new TCompanyCourseUser();
		tCompanyCourseUser.setAddTime(new Date());
		tCompanyCourseUser.setCompanyCourseId(inviteVo.getCourseId());
		tCompanyCourseUser.setXcxUserRoleId(xcxUserRoleId.toString());
		add(tCompanyCourseUser);

		// 邀请人的角色id
		TXcxUserRole inviteRole = xcxUserService.queryXcxUserRole(inviteVo.getInviteSessionKey(),
				inviteVo.getInviteRoleVal(), inviteVo.getCompanyId());
		// 添加受邀请人的接受消息表
		TMessage msg = new TMessage();
		msg.setId(UUID.get());
		msg.setContent(content);
		msg.setTitle("邀请信息");
		msg.setFromUser(inviteRole.getId().toString());
		msg.setIsdelete(0);
		msg.setRead(0);
		msg.setToUser(xcxUserRoleId.toString());
		msg.setAddTime(new Date());
		messageService.add(msg);

		// 添加发送者人的接受消息表
		msg.setId(UUID.get());
		msg.setContent(inviteVo.getNickName() + "接受了您的邀请");
		msg.setFromUser(xcxUserRoleId.toString());
		msg.setToUser(inviteRole.getId().toString());
		messageService.add(msg);

		// 添加课程所属的创建者的接受消息表
		if ("20".equals(inviteVo.getInviteRoleVal())) {
			// 找出课程所属的公司创建者
			List<TXcxUserRole> companyCreator = xcxUserRoleService.queryCompanyUser(10, inviteVo.getCompanyId());
			msg.setId(UUID.get());
			msg.setContent("您公司下的" + inviteRole.getRoleName() + "成功邀请了" + inviteVo.getNickName() + "成为了学员");
			msg.setFromUser(inviteRole.getId().toString());
			msg.setToUser(companyCreator.get(0).getId().toString());
			messageService.add(msg);
		}

		wechatUserRelationService.notifyAddXcxUser(userContextInfo.getOpenid());

	}

	@Override
	public Integer getUserCourseCount(Map<String, String> params) {
		Integer userCourseCount = companyCourseUserDao.queryUserCourseCount(params);
		return userCourseCount;
	}

	@Override
	public Integer getTeaCourseStuCount(Map<String, String> params) {
		Integer queryTeaCourseStuCount = companyCourseUserDao.queryTeaCourseStuCount(params);
		return queryTeaCourseStuCount;
	}

	@Override
	public PageQuery<TCompanyCourseUser> getTeaCourseStu(PageQuery<TCompanyCourseUser> pageQuery) {
		companyCourseUserDao.queryTeaCourseStu(pageQuery);
		return pageQuery;
	}

}