package com.luoran.zzbird.core;

import java.io.Serializable;

/**
 * @author lifetime
 *
 */
public class HttpResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private Object data;
	private String msg;
	private Long page;
	private Long pageSize;
	private Long totalRow;
	private Long totalPage;

	
	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Long totalRow) {
		this.totalRow = totalRow;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static final HttpResult missParams() {
		return fail("缺少必要参数");
	}

	public static final HttpResult success() {
		return success(null, null);
	}

	public static final HttpResult success(String msg) {
		return success(msg, null);
	}

	public static final HttpResult success(String msg, Object data) {
		HttpResult obj = new HttpResult();
		obj.setMsg(msg);
		obj.setStatusCode(200);
		obj.setData(data);
		return obj;
	}

	public static final HttpResult fail(String msg) {
		HttpResult obj = new HttpResult();
		obj.setMsg(msg);
		obj.setStatusCode(500);
		return obj;
	}

	public static final HttpResult fail(Integer code, String msg) {
		return fail(code, msg, null);
	}
	
	public static final HttpResult fail(String msg, Object data) {
		HttpResult obj = new HttpResult();
		obj.setMsg(msg);
		obj.setStatusCode(500);
		obj.setData(data);
		return obj;
	}

	public static final HttpResult fail(Integer code, String msg, Object data) {
		HttpResult obj = new HttpResult();
		obj.setMsg(msg);
		obj.setStatusCode(500);
		obj.setData(data);
		return obj;
	}
	
	
	public static final HttpResult success(String msg, Object data,Long page,Long pageSize,Long totalRow,Long totalPage) {
		HttpResult obj = new HttpResult();
		obj.setMsg(msg);
		obj.setStatusCode(200);
		obj.setData(data);
		obj.setPage(page);
		obj.setPageSize(pageSize);
		obj.setTotalRow(totalRow);
		obj.setTotalPage(totalPage);
		return obj;
	}

}
