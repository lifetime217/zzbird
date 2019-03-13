package com.luoran.zzbird.action;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.utils.ShortUuid;

/**
 * @author wsl
 *
 */
@Controller
@RequestMapping("upload")
public class UploadPictureAction {
	private final static Logger log = LoggerFactory.getLogger(UploadPictureAction.class);

	@Autowired
	Environment env;

	
	/**
	 * 
	 * @Author wsl  
	 * @Description:   上传图片 （没有删除储存图片）
	 */
	@RequestMapping("/pic")
	@ResponseBody
	public HttpResult upload(HttpServletRequest req) {
		log.info(" 开始上传图片");
		JSONObject obj = new JSONObject();
		// String data = (String) req.getParameter("base64");// 接收前台的base64图片数据
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		if (fileNames.hasNext()) {
			try {
				MultipartFile file = multipartRequest.getFile(fileNames.next());
				String originalName = file.getOriginalFilename();// 源文件名称
				String suffix = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();// 原始后缀小写
				String path = env.getProperty("file.path");// 获取图片存储目录
				String newFileName = ShortUuid.generateShortUuid() + suffix;// 根据UUID生成新的文件名称带后缀
				String depositUrl = path + "/" + newFileName;// 储存路劲
				String url = env.getProperty("file.path.url") + req.getContextPath() + "/" + newFileName;// 根据配置的虚拟路径可以直接用url访问本地图片,正式上线可以通过nginx反向代理访问linux图片目录
				// String url = evn.getProperty("file.path.url") + "/kaka/" + newFileName;
				File dest = new File(depositUrl);// 上传目标路径
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();
					log.info("父及目录不存在，创建父及目录。");
				}
				file.transferTo(dest);// 图片上传
				obj.put("url", url);
				obj.put("name", newFileName);
				obj.put("flag", true);
				log.info("图片上传成功");
			} catch (Exception e) {
				log.error(e.getMessage(), e.getCause());
				obj.put("flag", false);
				return HttpResult.fail("上传失败", obj);
			}
		}
		return HttpResult.success("上传成功", obj);
	}
}
