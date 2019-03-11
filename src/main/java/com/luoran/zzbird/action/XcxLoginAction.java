package com.luoran.zzbird.action;

import java.util.List;

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
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.entity.vo.UserRoleVo;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.utils.SessionManagerUtil;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("login")
public class XcxLoginAction {

	private final static Logger log = LoggerFactory.getLogger(XcxLoginAction.class);

	@Autowired
	XcxFacade facade;

	@Autowired
	ITXcxUserService xcxUserService;

	/**
	 * 
	 * @Author wsl
	 * @Description: TODO 用户登录获取用户的信息
	 */
	@RequestMapping("/getXcxUserOpenid/{code}")
	@ResponseBody()
	public HttpResult getXcxUserOpenid(@PathVariable(value = "code") String code) {
		JSONObject res = new JSONObject();
		try {
			// 获取用户的openid和sesionkey
			JSONObject json = facade.code2Session(code);
			String sessionKey = json.getString("sessionKey");
			res.put("sessionKey", sessionKey);
			res.put("flag", true);
			res.put("isNew", true);
			// 查找缓存中用户的sessionkey所对应的用户信息
			UserContextInfo user = SessionManager.get(sessionKey);
			// 为空数据库查找
			if (user == null) {
				// 判断新老用户(根据用户角色表查询)
				List<UserRoleVo> userList = xcxUserService.queryNewOrOldUser(sessionKey);
				if (userList == null || userList.size() == 0) {
					// openid存入缓存中
					SessionManagerUtil.putSessionManager(sessionKey, json.getString("openid"));
				} else {
					res.put("isNew", false);
					for (UserRoleVo userRoleVo : userList) {
						// 找出正在使用的角色和对应的公司等信息
						if (userRoleVo.getCurrentActive() == 1) {
							SessionManagerUtil.putSessionManager(sessionKey, json.getString("openid"),
									userRoleVo.getCompanyId(), userRoleVo.getRoleVal(), userRoleVo.getXcxUserRoleId(),
									userRoleVo.getCompanyName(), userRoleVo.getRoleName(), userRoleVo.getHeadImg());
							break;
						}
					}

				}
			} else {
				log.info("session有值：{}", user.toString());
				if (user.getCompanyId() != null) {
					res.put("isNew", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.put("flag", false);
			return HttpResult.fail("获取失败", res);
		}

		return HttpResult.success("获取成功", res);
	}
	
	
	
	

}
