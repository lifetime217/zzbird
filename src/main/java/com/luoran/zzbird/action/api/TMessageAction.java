package com.luoran.zzbird.action.api;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.luoran.zzbird.entity.biz.TMessage;
import com.luoran.zzbird.service.ITMessageService;

/**
 * @author lifetime
 *
 */
@Controller
@RequestMapping("api/message")
public class TMessageAction implements BaseAction<TMessage> {
	private final static Logger log = LoggerFactory.getLogger(TCompanyCourseUserAction.class);
	@Autowired
	private ITMessageService messageService;

	@RequestMapping
	public String index() {
		return "tmessage";
	}

	@Override
	public IBaseService<TMessage> getService() {
		return messageService;
	}
	/**
	 * @author tzx 查询消息列表
	 * @param #roleId:接收人的角色Id 
	 * @param #page:需要查询的页号
	 */
	@RequestMapping("getMessageList")
	@ResponseBody
	public HttpResult getMessageList(@RequestParam Map<String, String> params) {
		JSONArray data = new JSONArray();
		UserContextInfo userContextInfo = UserContext.get();
		Integer xcxUserRoleId = userContextInfo.getXcxUserRoleId();
		params.put("roleId", xcxUserRoleId.toString());
		if (StringUtils.isEmpty(params.get("roleId"))) {
			return HttpResult.fail("后台roleId未传入");
		}
		if (StringUtils.isEmpty(params.get("page"))) {
			return HttpResult.fail("page未传入");
		}
		PageQuery<TMessage> pageQuery = new PageQuery<TMessage>();
		pageQuery.setPageNumber(Integer.parseInt(params.get("page")));
		pageQuery.setPageSize(10);
		pageQuery.setParas(params);
		PageQuery<TMessage> messagePageList= null;
		try {
			messagePageList = messageService.getPageMessageList(pageQuery);
			List<TMessage> messageList = messagePageList.getList();
			for (TMessage message : messageList) {
				JSONObject obj = new JSONObject();
				obj.putAll(message.values());
				data.add(obj);
			}
			System.out.println("-----------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		//返回数据和分页参数
		return HttpResult.success("查询成功", data,messagePageList.getPageNumber(),messagePageList.getPageSize(),messagePageList.getTotalRow(),messagePageList.getTotalPage());
	}
	/**
	 * @author tzx 根据消息的id改变已查看的值
	 * @param #id : 消息的id 
	 * @return
	 */
	@RequestMapping("updateRead")
	@ResponseBody
	public HttpResult updateRead(@RequestParam Map<String, String> params) {
		if(StringUtils.isEmpty(params.get("id"))) {
			return HttpResult.fail("id不能为空");
		}
		try {
			TMessage msg = new TMessage();
			msg.setId(params.get("id"));
			msg.setRead(1);
			//此方法为baseMapper的接口
			messageService.updatePart(msg);
			System.out.println("---------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("update失败");
		}
		return HttpResult.success("update成功");
	}
	
	/**
	 * @author tzx 根据Id获取消息的详情
	 * @param #id : 消息的id 
	 * @return
	 */
	@RequestMapping("selectMessageDetails")
	@ResponseBody
	public HttpResult selectMessageDetails(@RequestParam Map<String, String> params) {
		if(StringUtils.isEmpty(params.get("id"))) {
			return HttpResult.fail("id不能为空");
		}
		TMessage msg = null;
		try {
			msg = messageService.getMsgById(params.get("id"));
			System.out.println("---------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			return HttpResult.fail("查询失败");
		}
		return HttpResult.success("查询成功",msg.values());
	}
	
}
