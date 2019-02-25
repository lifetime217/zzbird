package com.luoran.zzbird.core.ext;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.core.BaseInfo;

/**
 * @author lifetime217
 *
 * @param <T>
 */
public  abstract class AbstractBaseService<T> implements IBaseService<T> {

	public abstract BaseDao<T> getDao();

	public String add(T t) {
		getDao().insert(t);
		return ((BaseInfo)t).getString("id");
	}

	@Override
	public String save(T t) {
		String id = ((BaseInfo)t).getString("id");
		if(StringUtils.isEmpty(id)){
			return add(t);
		}else{
			getDao().updateTemplateById(t);
			return id;
		}
	}

	@Override
	public void updatePart(T t) {
		getDao().updateTemplateById(t);
	}

	@Override
	public T get(String id) {
		return getDao().single(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().all();
	}

	@Override
	public void delete(String id) {
		getDao().deleteById(id);
	}

	@Override
	public PageQuery<T> getQueryList(PageQuery<T> pageQuery) {
		getDao().queryPage(pageQuery);
		return pageQuery;
	}

}
