package com.luoran.zzbird.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lifetime
 *
 */
public class XcxSessionInterceptor extends HandlerInterceptorAdapter {
	
	public static final String XcxSessionKey = "zzbird_XcxSessionKey";

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		UserContext.clear();
		String sessionKey = req.getParameter(XcxSessionKey);
		if(StringUtils.isEmpty(sessionKey) || !SessionManager.isValid(sessionKey)) {
			try {
				resp.getWriter().append(JSONObject.toJSONString(HttpResult.fail(500, "身份不合法")));
				resp.getWriter().flush();
			} catch (Exception e) {
			}
		}
		UserContext.init(SessionManager.get(sessionKey));
		return true;
	}

}
