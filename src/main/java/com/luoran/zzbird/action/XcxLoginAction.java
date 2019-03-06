package com.luoran.zzbird.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.luoran.wechat.facade.XcxFacade;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.utils.MD5;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("xcx")
public class XcxLoginAction {
	
	private final static  Logger log = LoggerFactory.getLogger(XcxLoginAction.class);

	@Autowired
	XcxFacade facade;

	public HttpResult auth(@RequestParam String opid) {
		String sessionKey = MD5.get(opid);

		UserContextInfo info = new UserContextInfo();
		info.setCompanyId("");
		info.setCompanyName("");
		info.setRoleVal(10);
		info.setXcxUserRoleId("");
		SessionManager.put(sessionKey, info);

		return HttpResult.success("success", sessionKey);
	}

	/**
	 * 
	 * @Author wsl  
	 * @Description: TODO 获取小程序的openid
	 */
	@RequestMapping("/getXcxUserOpenid/{code}")
	@ResponseBody()
	public HttpResult getXcxUserOpenid(@PathVariable(value = "code")String  code) {
		JSONObject res = new JSONObject();
		try {
			String sessionKey = facade.code2Session(code);
			res.put("sessionKey",sessionKey);
			res.put("flag",true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.put("flag",false);
			return  HttpResult.fail("获取失败", res);
		}
		
		return  HttpResult.success("获取成功", res);
	}

}
