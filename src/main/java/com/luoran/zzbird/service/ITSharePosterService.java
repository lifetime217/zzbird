package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TSharePoster;

/**
 * @author lifetime
 *
 */
public interface ITSharePosterService extends IBaseService<TSharePoster> {

	/**
	 * 
	 * @Author wsl
	 * @Description:根据海报模板id查询分享海报信息
	 */
	List<TSharePoster> querySharePoster(String posterId);
}
