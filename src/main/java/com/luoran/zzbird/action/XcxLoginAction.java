package com.luoran.zzbird.action;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.SessionManager;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.utils.MD5;

public class XcxLoginAction {
	
	
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
	
}
