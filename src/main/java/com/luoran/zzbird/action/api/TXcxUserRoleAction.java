package com.luoran.zzbird.action.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.UserContext;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.BaseAction;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TXcxUserRole;
import com.luoran.zzbird.service.ITXcxUserRoleService;
import com.luoran.zzbird.service.ITXcxUserService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("api/xcxuserrole")
public class TXcxUserRoleAction  implements BaseAction<TXcxUserRole> {
	private final static Logger log = LoggerFactory.getLogger(TCompanyCourseAction.class);
	@Autowired
	private ITXcxUserRoleService xcxUserRoleService;
	
	@Autowired
	ITXcxUserService xcxUserService;
	
	@RequestMapping
	public String index() {
		return "txcxuserrole";
	}

	@Override
	public IBaseService<TXcxUserRole> getService() {
		return xcxUserRoleService;
	}
	@RequestMapping("/selectUserRole")
	@ResponseBody
	public HttpResult selectUserRole() {
		UserContextInfo userContextInfo = null;
		try {
			userContextInfo = UserContext.get();
			System.out.println("-------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功",userContextInfo);
	}
	
	
	@RequestMapping("/updataUserRole")
	@ResponseBody
	public HttpResult updataUserRole(@RequestParam Map<String, String> params) {
		if(StringUtils.isEmpty(params.get("imgUrl"))) {
			return HttpResult.fail("头像链接不能为空");
		}
		if(StringUtils.isEmpty(params.get("nickName"))) {
			return HttpResult.fail("名称不能为空");
		}
		if(params.get("nickName").length() > 15) {
			return HttpResult.fail("名称不能大于15位");
		}
		UserContextInfo userContextInfo = null;
		userContextInfo = UserContext.get();
		params.put("roleId", userContextInfo.getXcxUserRoleId().toString());
		try {
			TXcxUserRole user = xcxUserRoleService.updataUserRole(params);
			if (user == null) {
				return HttpResult.fail("修改失败");
			}
			xcxUserService.reloadSession(params.get("zzbird_XcxSessionKey"));
			System.out.println("-------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("修改失败");
		}
		return HttpResult.success("修改成功");
	}
}
