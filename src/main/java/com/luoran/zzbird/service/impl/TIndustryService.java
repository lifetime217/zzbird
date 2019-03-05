package com.luoran.zzbird.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITIndustryDao;
import com.luoran.zzbird.entity.biz.TIndustry;
import com.luoran.zzbird.entity.vo.IndustryVo;
import com.luoran.zzbird.service.ITIndustryService;

/**
 * @author lifetime
 *
 */
@Service
public class TIndustryService extends AbstractBaseService<TIndustry> implements ITIndustryService {
	@Autowired
	private ITIndustryDao industryDao;

	@Override
	public BaseDao<TIndustry> getDao() {
		return industryDao;
	}

	@Override
	public String add(TIndustry t) {
		return super.add(t);
	}

	/**
	 * 查询所有的标签 Title: queryIndustry Description:
	 * 
	 * @return
	 * @throws Exception 
	 * @see com.luoran.zzbird.service.ITIndustryService#queryIndustry()
	 */
	@Override
	public JSONObject queryIndustry() throws Exception {
		JSONObject obj = new JSONObject();
		// 查询
		List<IndustryVo> industryKids = industryDao.queryAllIndustry();
		Map<String, List<IndustryVo>> groupIndustry = groupIndustry(industryKids);
		obj.put("industry", groupIndustry);
		return obj;
	}

	/**
	 * 按照类型名字分组
	 * 
	 * @param industryList
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<IndustryVo>> groupIndustry(List<IndustryVo> industryList) throws Exception {
		Map<String, List<IndustryVo>> resultMap = new HashMap<String, List<IndustryVo>>();
		try {
			for (IndustryVo industry : industryList) {
				if (resultMap.containsKey(industry.getpName())) {
					resultMap.get(industry.getpName()).add(industry);
				} else {// map中不存在，新建key，用来存放数据
					List<IndustryVo> industries = new ArrayList<IndustryVo>();
					industries.add(industry);
					resultMap.put(industry.getpName(), industries);
				}
			}
		} catch (Exception e) {
			throw new Exception("分组出现异常", e);
		}
		return resultMap;
	}

}