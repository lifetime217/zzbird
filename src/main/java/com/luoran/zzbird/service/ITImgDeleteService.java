package com.luoran.zzbird.service;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TImgDelete;

/**
 * @author wsl
 *
 */
public interface ITImgDeleteService extends IBaseService<TImgDelete> {

	/**
	 * 
	 * @Author wsl
	 * @Description: 保存删除的图片
	 */
	void addImg(String imgs);
}
