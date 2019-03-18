package com.luoran.zzbird.action.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.service.ITXcxUserService;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("api/xcxuserrole")
public class TXcxUserRoleAction implements BaseAction<TXcxUserRole> {

	private static final Logger log = LoggerFactory.getLogger(TXcxUserRoleAction.class);

	@Autowired
	private ITXcxUserRoleService xcxUserRoleService;

	@Autowired
	private ITXcxUserService xcxUserService;

	@RequestMapping
	public String index() {
		return "txcxuserrole";
	}

	@Override
	public IBaseService<TXcxUserRole> getService() {
		return xcxUserRoleService;
	}

	/**
	 * 
	 * @Author wsl
	 * @Description: 查询用户所有的角色和公司
	 */
	@RequestMapping("/queryRole")
	@ResponseBody()
	public HttpResult queryRole(String zzbird_XcxSessionKey) {
		JSONObject res = new JSONObject();
		try {
			List<TXcxUserRole> allRoleUser = xcxUserService.queryAllRoleUser(zzbird_XcxSessionKey);
			JSONArray RoleUser = new JSONArray();
			for (TXcxUserRole tXcxUserRole : allRoleUser) {
				JSONObject json = new JSONObject();
				json.putAll(tXcxUserRole.values());
				json.remove("openid");
				RoleUser.add(json);
			}
			res.put("RoleUser", RoleUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功", res);
	}

	@RequestMapping("/updateActive")
	@ResponseBody()
	public HttpResult updateActive(@RequestParam(value = "id") Integer id, String zzbird_XcxSessionKey) {
		JSONObject res = new JSONObject();
		try {
			boolean updateActive = xcxUserRoleService.updateActive(id);
			if (updateActive) {
				xcxUserService.reloadSession(zzbird_XcxSessionKey);
				UserContextInfo userContextInfo = UserContext.get();
				System.out.println("================="+userContextInfo.toString());
				res.put("roleVal", userContextInfo.getRoleVal());
			} else {
				return HttpResult.fail("修改失败");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("修改失败");
		}
		return HttpResult.success("修改成功", res);
	}

}
