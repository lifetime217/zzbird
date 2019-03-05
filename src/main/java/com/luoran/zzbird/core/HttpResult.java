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

}
