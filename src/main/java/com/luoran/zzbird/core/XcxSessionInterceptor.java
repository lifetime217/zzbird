package com.luoran.zzbird.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.entity.vo.UserRoleVo;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.utils.SessionManagerUtil;

/**
 * @author lifetime
 *
 */
@Component
public class XcxSessionInterceptor extends HandlerInterceptorAdapter {

	private final static Logger log = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

	public static final String XcxSessionKey = "zzbird_XcxSessionKey";

	@Autowired
	ITXcxUserService xcxUserService;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		UserContext.clear();
		String sessionKey = req.getParameter(XcxSessionKey);
		// 如果是请求code的就放行
		if ("loginCode".equals(sessionKey)) {
			return true;
		}
		if (StringUtils.isEmpty(sessionKey) || !SessionManager.isValid(sessionKey)) {
			try {
				log.info("身份不合法");
				resp.getWriter().append(HttpResult.fail(500, "身份不合法").toString());
				resp.getWriter().flush();
				return false;
			} catch (Exception e) {
			}
		} else {
			// sessionKey不为空
			UserContextInfo user = SessionManager.get(sessionKey);
			// 为空数据库查找
			if (user == null) {
				// 判断新老用户(根据用户角色表查询)
				List<UserRoleVo> userList = xcxUserService.queryNewOrOldUser(sessionKey);
				if (userList != null) {
					for (UserRoleVo userRoleVo : userList) {
						// 找出正在使用的角色和对应的公司等信息
						if (userRoleVo.getCurrentActive() == 1) {
							SessionManagerUtil.putSessionManager(sessionKey, userRoleVo.getCompanyId(),
									userRoleVo.getRoleVal(), userRoleVo.getXcxUserRoleId(), userRoleVo.getCompanyName(),
									userRoleVo.getRoleName(), userRoleVo.getHeadImg());
							break;
						}
					}

				}
			}
		}

		UserContext.init(SessionManager.get(sessionKey));
		return true;
	}

}
