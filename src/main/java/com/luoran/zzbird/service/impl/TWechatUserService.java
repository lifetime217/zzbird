package com.luoran.zzbird.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.wechat.facade.GzhFacade;
import com.luoran.zzbird.core.UserContextInfo;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITCompanyCourseDao;
import com.luoran.zzbird.dao.ITWechatUserDao;
import com.luoran.zzbird.dao.ITXcxUserDao;
import com.luoran.zzbird.entity.biz.TCompanyCourse;
import com.luoran.zzbird.entity.biz.TWechatUser;
import com.luoran.zzbird.service.ITWechatUserService;
import com.luoran.zzbird.utils.JsonUtils;

/**
 * @author lifetime
 *
 */
@Service
public class TWechatUserService extends AbstractBaseService<TWechatUser> implements ITWechatUserService {
	@Autowired
	private ITWechatUserDao wechatUserDao;
	@Resource
	private GzhFacade gzhFacade;
	@Autowired
	private Environment env;
	@Autowired
	private ITCompanyCourseDao companyCourseDao;

	@Autowired
	@Override
	public BaseDao<TWechatUser> getDao() {
		return wechatUserDao;
	}

	@Autowired
	ITXcxUserDao xcxUserDao;

	@Override
	public String add(TWechatUser t) {
		return super.add(t);
	}

	@Override
	public boolean saveUserInfo(String openId) {
		// 获取微信用户基本信息
		String userInfo = gzhFacade.getUserInfo(openId);
		// 转换为json对象
		JSONObject jsObj = JSONObject.parseObject(userInfo);
		TWechatUser WechatUser = new TWechatUser();
		WechatUser.setOpenId(openId);
		// 查询表中是否有这个用户的数据
		List<TWechatUser> template = wechatUserDao.template(WechatUser);
		// 将获取到的数据存入对象
		WechatUser.set("nickName", jsObj.getString("nickname"));
		WechatUser.set("sex", jsObj.getInteger("sex"));
		WechatUser.set("city", jsObj.getString("city"));
		WechatUser.set("country", jsObj.getString("country"));
		WechatUser.set("province", jsObj.getString("province"));
		WechatUser.set("language", jsObj.getString("language"));
		WechatUser.set("headimgUrl", jsObj.getString("headimgurl"));
		WechatUser.set("headimgMd5", "");
		WechatUser.set("subscribeTime", jsObj.getLong("subscribe_time"));
		WechatUser.set("remark", jsObj.getString("remark"));
		WechatUser.set("groupid", jsObj.getInteger("groupid"));
		JSONArray jsonArray = jsObj.getJSONArray("tagid_list");
		WechatUser.set("tagidList", jsonArray.toString());
		WechatUser.set("subscribeScene", jsObj.getString("subscribe_scene"));
		WechatUser.set("subscribe", jsObj.getInteger("subscribe"));
		WechatUser.set("qrScene", jsObj.getInteger("qr_scene"));
		WechatUser.set("qrSceneStr", jsObj.getString("qr_scene_str"));
		// 判断如果有的话就跟新数据
		if (template.size() > 0 && template.get(0).getOpenId().equals(openId)) {
			WechatUser.setId(template.get(0).getId());
			wechatUserDao.updateTemplateById(WechatUser);
		} else {
			wechatUserDao.insert(WechatUser, true);
		}
		return true;
	}

	@Override
	public String sendSubscribeMsg(Map<String, String> params, Map<String, String> parseXml) {
		String fromUserName = parseXml.get("ToUserName");
		String openId = params.get("openid");
		StringBuffer str = new StringBuffer();
		str.append("<xml>");
		str.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		str.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
		str.append("<CreateTime>" + new Date().getTime() + "</CreateTime>");
		str.append("<MsgType><![CDATA[" + "text" + "]]></MsgType>");
		str.append("<Content><![CDATA[" + "欢迎来到青团趣学" + "]]></Content>");
		str.append("</xml>");
		System.out.println(str);
		return str.toString();
	}

	@Override
	public TWechatUser queryGzhUserByOpenId(String openId) {
		return wechatUserDao.queryGzhUserByOpenId(openId);
	}

	@Override
	public List<TWechatUser> queryGzhUserByNickName(String nickName) {
		return wechatUserDao.queryGzhUserByNickName(nickName);
	}

	@Override
	public void sendGZHMessage(UserContextInfo userContextInfo, Map<String, Object> params, List<Map> studentList) {
		try {
			// 获取项目下json文件夹中的json文件（消息模版json）
			JSONObject obj = JsonUtils.readJsonFromClassPath("json/tempateJson.json", JSONObject.class);
			JSONObject message = null;
			TCompanyCourse courseInfo = companyCourseDao.unique(params.get("courseId").toString());
			for (int i = 0; i < studentList.size(); i++) {
				message = obj;
				String gzhOpenid = xcxUserDao.queryUserByRoleId(studentList.get(i).get("id").toString());
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if (!StringUtils.isEmpty(gzhOpenid)) {
					message.put("touser", gzhOpenid);
					//message.put("touser", "okEJJ6L6OWuZeNEogKNki6EBGmwk");
					message.put("template_id", env.getProperty("weixin.templateid"));
					JSONObject data = (JSONObject) message.get("data");

					JSONObject title = (JSONObject) data.get("title");
					title.put("value", "恭喜打卡成功");
					//课程名
					JSONObject course = (JSONObject) data.get("course");
					course.put("value", courseInfo.getCourseName());
					//打卡时间
					JSONObject dakaTime = (JSONObject) data.get("dakaTime");
					dakaTime.put("value", formatter.format(date));
					//打卡老师
					JSONObject teacher = (JSONObject) data.get("teacher");
					teacher.put("value", userContextInfo.getRoleName());
					//打卡公司
					JSONObject company = (JSONObject) data.get("company");
					company.put("value", userContextInfo.getCompanyName());
					
					String sendTemplateMsg = gzhFacade.sendTemplateMsg("", message.toString());
					System.out.println(sendTemplateMsg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}