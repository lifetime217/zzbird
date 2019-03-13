package com.luoran.zzbird.action.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.service.ITXcxUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("api/xcxuser")
public class TXcxUserAction implements BaseAction<TXcxUser> {
	
	private static final Logger log = LoggerFactory.getLogger(TXcxUserAction.class);


	@Autowired
	private ITXcxUserService xcxUserService;

	@RequestMapping
	public String index() {
		return "txcxuser";
	}

	@Override
	public IBaseService<TXcxUser> getService() {
		return xcxUserService;
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: 添加用户
	 */
	@RequestMapping("/addUser")
	@ResponseBody()
	public HttpResult addUser(TXcxUser xcxUser, String sessionKey) {
		try {
			UserContextInfo user = UserContext.get();
			xcxUser.setOpenId(user.getOpenid());
			xcxUser.setSessionKey(sessionKey);
			xcxUserService.add(xcxUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("新增失败！");
		}
		return HttpResult.success("新增成功！");
	}

}
