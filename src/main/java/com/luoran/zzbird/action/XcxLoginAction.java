package com.luoran.zzbird.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.luoran.wechat.facade.XcxFacade;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.service.ITXcxUserService;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("login")
public class XcxLoginAction {

	final static Logger log = LoggerFactory.getLogger(XcxLoginAction.class);

	@Autowired
	XcxFacade facade;

	@Autowired
	ITXcxUserService xcxUserService;

	/**
	 * 
	 * @Author wsl
	 * @Description: 用户登录获取用户的信息
	 */
	@RequestMapping("/getXcxUserOpenid/{code}")
	@ResponseBody()
	public HttpResult getXcxUserOpenid(@PathVariable(value = "code") String code) {
		JSONObject res = new JSONObject();
		try {
			JSONObject json = facade.code2Session(code);
			String sessionKey = json.getString("sessionKey");
			String openId = json.getString("openid");
			res.put("sessionKey", sessionKey);
			xcxUserService.initSession(sessionKey, openId);
			if (SessionManager.get(sessionKey).getRoleVal() != null) {
				res.put("roleVal", SessionManager.get(sessionKey).getRoleVal());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("获取失败", res);
		}

		return HttpResult.success("获取成功", res);
	}

}
