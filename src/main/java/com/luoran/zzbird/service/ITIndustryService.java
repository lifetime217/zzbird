package com.luoran.zzbird.service;


import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TIndustry;

/**
 * @author lifetime
 *
 */
public interface ITIndustryService extends IBaseService<TIndustry> {
	JSONObject queryIndustry() throws Exception;
}
