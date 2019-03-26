package com.luoran.zzbird.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 转换util
 * 
 * @author wsl
 */
public class Convert {
	/**
	 * @Author wsl 
	 * @Description: 拼接url返回String 
	 */
	public static String convertImgString(String companyImg, String url) {
		String[] imgs = companyImg.split(",");
		StringBuffer imgBuffer = new StringBuffer();
		for (int j = 0; j < imgs.length; j++) {
			imgs[j] = url + "/" + imgs[j];  
//			imgs[j] = url + "/kaka/" + imgs[j];//线上版本的图片访问
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
			imgs.set(j, url + "/" + img);
//			imgs.set(j, url + "/kaka/" + img);//线上版本的图片访问
		}
		return imgs;
	}

}
