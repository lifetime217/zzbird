package com.luoran.zzbird.core;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.luoran.zzbird.utils.Constant;

/**
 * @author lifetime
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	public static final String SysUserInfo_Key = "Sys_UserInfo";

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		UserContext.clear();
		String uri = getSimpleUri(req);
		String ctxPath = req.getContextPath();
		String userNumber = getCookieValue(req, Constant.UserNumber);
		String accessToken = getCookieValue(req, Constant.AccessToken);
		if (SessionManager.isValid(userNumber, accessToken)) {
			if ("/".equals(uri)) {
				resp.sendRedirect(ctxPath + "/workbench");
				return false;
			}
		} else {
			String url = req.getRequestURL().toString();
			String durl = URLEncoder.encode(url, "UTF-8");
			resp.sendRedirect(ctxPath + "/user/login?redirectURL=" + durl);
			return false;
		}
		UserContext.init(SessionManager.get(userNumber));
		req.setAttribute(SysUserInfo_Key, UserContext.get());
		return true;
	}
	

	String getSimpleUri(HttpServletRequest req) {
		String uri = req.getRequestURI().trim();
		int c = uri.indexOf("?");
		if (c != -1) {
			return uri.substring(0, c);
		}
		return uri;
	}

	String getCookieValue(HttpServletRequest req, String name) {
		Cookie[] cos = req.getCookies();
		if (cos != null) {
			for (Cookie cookie : cos) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
