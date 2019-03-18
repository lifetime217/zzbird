package com.luoran.zzbird.core.ext;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

public interface IBaseService<T> {

	public String add(T t);
	
	public Integer insert(T t);

	public String save(T t);

	public void updatePart(T t);

	public T get(String id);

	public List<T> findAll();

	public void delete(String id);

	public PageQuery<T> getQueryList(PageQuery<T> pageQuery);
}
