package com.luoran.zzbird.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * 转换util
 * 
 * @author wsl
 */
public class Convert {
	
	@Autowired
	Environment env;

	/**
	 * 
	 * @Author wsl
	 * @Description: 集中转换图片url
	 */
	public static String imgToUrl(String img, String url) {
		String newImg = url + "/" + img;
//		String newImg =  url + "/kaka/" + img; 
		return newImg;
	}

	/**
	 * @Author wsl
	 * @Description: 接受多图片字符串，拼接url返回String
	 */
	public static String convertImgString(String companyImg, String url) {
		String[] imgs = companyImg.split(",");
		StringBuffer imgBuffer = new StringBuffer();
		for (int j = 0; j < imgs.length; j++) {
			imgs[j] = imgToUrl(imgs[j], url);
			imgBuffer.append(imgs[j] + ",");
		}
		return imgBuffer.replace(imgBuffer.length() - 1, imgBuffer.length(), "").toString();
	}

	/**
	 * @Author wsl
	 * @Description: 拼接url 返回List
	 */
	public static List<String> convertImgList(String companyImg, String url) {
		List<String> imgs = Arrays.asList(companyImg.split(","));
		for (int j = 0; j < imgs.size(); j++) {
			String img = imgs.get(j);
			imgs.set(j, imgToUrl(img, url));
		}
		return imgs;
	}

}
