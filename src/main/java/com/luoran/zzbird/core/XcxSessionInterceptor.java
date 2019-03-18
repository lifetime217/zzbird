package com.luoran.zzbird.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.luoran.zzbird.service.ITXcxUserService;

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
		if (StringUtils.isEmpty(sessionKey)) {
			try {
				log.info("身份不合法");
				resp.getWriter().append(HttpResult.fail(500, "身份不合法").toString());
				resp.getWriter().flush();
				return false;
			} catch (Exception e) {
			}
		} else {
			xcxUserService.reinitSession(sessionKey);
		}
		UserContext.init(SessionManager.get(sessionKey));
		return true;
	}
	
	
	

}
