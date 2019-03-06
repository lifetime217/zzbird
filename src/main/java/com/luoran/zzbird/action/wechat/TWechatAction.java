package com.luoran.zzbird.action.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luoran.zzbird.service.ITWechatUserService;
import com.luoran.zzbird.wxfwhUtils.GzhUtils;
/**
 * 
 * @author tzx
 *
 */
@RestController
@RequestMapping("wechat")
public class TWechatAction {
	
	@Autowired
	private ITWechatUserService iTWechatUserService;
	private String TOKEN = "luoran";
	/**
	 *  用于注册微信公众号服务器
	 * @param params
	 * @return echostr 成功返回
	 */
	@GetMapping("auth")
	public Long auth(@RequestParam Map<String, String> params) {
		String signature = params.get("signature");
		String timestamp = params.get("timestamp");
		String nonce = params.get("nonce");
		String echostr = params.get("echostr");
		// 排序
		String sortString = GzhUtils.sort(TOKEN, timestamp, nonce); // 加密
		String myString = GzhUtils.sha1(sortString); // 校验
		if (myString != null && myString != "" && myString.equals(signature)) {
			System.out.println("签名校验通过" + echostr);
			// 如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
			return Long.parseLong(echostr);
		} else {
			System.out.println("签名校验失败");
			return 0L;
		}
	}
	/**
	 * 用于接收微信公众号发来的消息和注册于取消关注
	 * @param params
	 * @param req
	 * @param rpe
	 */
	@PostMapping("auth")
	public void auth2(@RequestParam Map<String, String> params,HttpServletRequest req,HttpServletResponse rpe) {
		try {
			//解析xml
			Map<String, String> parseXml = GzhUtils.parseXml(req);
			//关注后进入
			if("subscribe".equals(parseXml.get("Event"))) {
				iTWechatUserService.saveUserInfo(params.get("openid"));
				System.out.println("点击了关注");
				String sendSubscribeMsg = iTWechatUserService.sendSubscribeMsg(params,parseXml);
				rpe.getWriter().write(sendSubscribeMsg);
				//取消关注
			}else if("unsubscribe".equals(parseXml.get("Event"))){
				System.out.println("取消关注");
				//发送了消息
			}else if(parseXml.get("Event") == null){
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
