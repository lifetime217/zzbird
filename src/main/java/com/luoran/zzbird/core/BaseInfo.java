package com.luoran.zzbird.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.beetl.sql.core.Tail;

/**
 * @author lifetime
 *
 */
public class BaseInfo implements Tail {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> datas = new HashMap<>();

	/*
	 * 此处与beetlsql采用不同的处理方式，不存在多级lazy取数
	 * 
	 * @see org.beetl.sql.core.Tail#get(java.lang.String)
	 */
	public Object get(String key) {
		return datas.get(key);
	}

	public String getString(String key) {
		Object val = get(key);
		if (val != null) {
			if (val instanceof String) {
				return (String) val;
			} else {
				return val.toString();
			}
		}
		return null;
	}

	/**
	 * 为null或者值无效则返回-1
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		Object val = get(key);
		if (val != null) {
			if (val instanceof Integer) {
				return ((Integer) val).intValue();
			} else {
				return Integer.valueOf(val.toString()).intValue();
			}
		}
		return -1;
	}

	public Integer getInteger(String key) {
		Object val = get(key);
		if (val != null) {
			if (val instanceof Integer) {
				return (Integer) val;
			} else {
				return Integer.valueOf(val.toString());
			}
		}
		return null;
	}

	public BigDecimal getBigDecimal(String key) {
		Object val = get(key);
		if (val != null) {
			if (val instanceof BigDecimal) {
				return (BigDecimal) val;
			} else {
				return new BigDecimal(val.toString());
			}
		}
		return null;
	}

	/**
	 * 支持java.sql.Date日期转化为java.util.Date<br>
	 * 支持将Long数据转化为日期<br>
	 * int数据将在乘以1000后进行日期转化<br>
	 * 
	 * @param key
	 * @return
	 */
	public Date getDate(String key) {
		Object val = get(key);
		if (val != null) {
			if (val instanceof Date) {
				return (Date) val;
			} else if (val instanceof java.sql.Date) {
				java.sql.Date sdate = (java.sql.Date) val;
				return new Date(sdate.getTime());
			} else if (val instanceof java.sql.Timestamp) {
				java.sql.Timestamp stime = (java.sql.Timestamp) val;
				return new Date(stime.getTime());
			} else if (val instanceof Long) {
				return new Date((Long) val);
			} else if (val instanceof Integer) {
				return new Date(((Integer) val) * 1000);
			}
		}
		return null;
	}

	public void set(String key, Object value) {
		datas.put(key, value);
	}
	
	public void putAll(Map<String, Object> o){
		datas.putAll(o);
	}
	
	public Set<String> keys(){
		return datas.keySet();
	}
	
	public Map<String, Object> values() {
		return datas;
	}
	
}
