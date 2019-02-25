package com.luoran.zzbird.core.ext;

import java.util.ArrayList;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luoran.zzbird.core.BaseInfo;
import com.luoran.zzbird.core.HttpResult;
import com.luoran.zzbird.core.validator.AddValidator;

/**
 * 
 * @author lifetime
 *
 */
public interface BaseAction<T> {

	public abstract IBaseService<T> getService();

	@RequestMapping("/add")
	@ResponseBody
	public default HttpResult add(T info) {
		if (info.getClass().isAssignableFrom(AddValidator.class)) {
			HttpResult res = ((AddValidator) info).addValidator();
			if (res != null) {
				return res;
			}
		}
		try {
			getService().add(info);
			return HttpResult.success("添加成功 ");
		} catch (Exception e) {
			return HttpResult.fail(e.getMessage());
		}
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public default HttpResult delete(@PathVariable String id) {
		try {
			getService().delete(id);
			return HttpResult.success();
		} catch (Exception e) {
			return HttpResult.fail(e.getMessage());
		}
	}

	@RequestMapping("/listQuery")
	@ResponseBody
	public default JSONObject listQuery(@RequestParam Map<String, String> queryParams) {
		PageQuery<T> pageQuery = new PageQuery<T>();
		String sortField = queryParams.get("sortField");
		String sortType = queryParams.get("sortType");
		String pageStr = queryParams.get("page");
		pageQuery.setPageNumber(StringUtils.isEmpty(pageStr) ? 1 : Integer.parseInt(pageStr));
		pageQuery.setPageSize(10);
		if (!StringUtils.isEmpty(sortField)) {
			pageQuery.setOrderBy(sortField + " " + sortType);
		}
		pageQuery.setParas(queryParams);
		return pager(getQueryList(pageQuery));
	}

	public default PageQuery<T> getQueryList(PageQuery<T> pageQuery) {
		return getService().getQueryList(pageQuery);
	}

	/**
	 * 提供前端分页组件所需要的数据结构
	 * 
	 * @param pq
	 * @return
	 */
	public default JSONObject pager(PageQuery<T> pq) {
		JSONObject res = new JSONObject();
		if (isQueryAll()) {
			ArrayList<Map<String, Object>> infos = new ArrayList<>();
			pq.getList().forEach(item -> {
				infos.add(((BaseInfo) item).values());
			});
			res.put("list", infos);
		} else {
			res.put("list", pq.getList());
		}
		JSONObject pageInfo = new JSONObject();
		pageInfo.put("current", pq.getPageNumber());
		getPageInfo(pq.getTotalPage(), pq.getPageNumber(), pageInfo);
		res.put("pageInfo", pageInfo);
		return res;
	}

	default boolean isQueryAll() {
		return false;
	}

	/**
	 * @param allPageSize
	 *            页面数的总数
	 * @param pageNum
	 *            当前是页码
	 * @param pageInfo
	 *            分页对象
	 */
	static void getPageInfo(long allPageSize, long pageNum, JSONObject pageInfo) {
		JSONArray arr = new JSONArray();
		if (allPageSize > 6) {
			if (pageNum >= 5) {
				// << , ... , 3 , 4 , 5 , ... , >>
				JSONObject index = new JSONObject();
				index.put("text", "«");
				if (pageNum == 1) {
					index.put("class", "disabled");
				}
				arr.add(index);

				index = new JSONObject();
				index.put("text", 1);
				arr.add(index);

				index = new JSONObject();
				index.put("text", "...");
				index.put("class", "disabled");
				arr.add(index);

				index = new JSONObject();
				index.put("text", pageNum - 1);
				arr.add(index);

				index = new JSONObject();
				index.put("text", pageNum);
				index.put("class", "active");
				arr.add(index);

				if (pageNum + 1 <= allPageSize) {
					index = new JSONObject();
					index.put("text", pageNum + 1);
					arr.add(index);

					if (pageNum + 3 <= allPageSize) {
						index = new JSONObject();
						index.put("text", "...");
						index.put("class", "disabled");
						arr.add(index);
					}
					if (pageNum + 1 != allPageSize) {
						index = new JSONObject();
						index.put("text", allPageSize);
						arr.add(index);
					}
				}

				index = new JSONObject();
				index.put("text", "»");
				if (pageNum == allPageSize) {
					index.put("class", "disabled");
				}
				arr.add(index);
				pageInfo.put("pageIndexs", arr);
			} else {
				JSONObject index = new JSONObject();
				index.put("text", "«");
				if (pageNum == 1) {
					index.put("class", "disabled");
				}
				arr.add(index);
				for (int i = 1; i <= 5; i++) {
					index = new JSONObject();
					index.put("text", i);
					if (pageNum == i) {
						index.put("class", "active");
					}
					arr.add(index);
				}
				index = new JSONObject();
				index.put("text", "...");
				index.put("class", "disabled");
				arr.add(index);

				index = new JSONObject();
				index.put("text", allPageSize);
				arr.add(index);

				index = new JSONObject();
				index.put("text", "»");
				if (pageNum == allPageSize) {
					index.put("class", "disabled");
				}
				arr.add(index);
				pageInfo.put("pageIndexs", arr);
			}
		} else {
			JSONObject index = new JSONObject();
			index.put("text", "«");
			if (pageNum == 1) {
				index.put("class", "disabled");
			}
			arr.add(index);
			for (int i = 1; i <= allPageSize; i++) {
				index = new JSONObject();
				index.put("text", i);
				if (pageNum == i) {
					index.put("class", "active");
				}
				arr.add(index);
			}
			index = new JSONObject();
			index.put("text", "»");
			if (pageNum == allPageSize) {
				index.put("class", "disabled");
			}
			arr.add(index);
			pageInfo.put("pageIndexs", arr);
		}
	}
}
